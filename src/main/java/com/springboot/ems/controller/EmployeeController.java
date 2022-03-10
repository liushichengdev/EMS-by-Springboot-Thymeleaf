package com.springboot.ems.controller;

import com.springboot.ems.entity.Employee;
import com.springboot.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Value("${photo.file.dir}")
    private String realpath;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("lists")
    public String lists(Model model){
        List<Employee> employeeList=employeeService.lists();
        model.addAttribute("employeeList", employeeList);
        return "emplist";
    }

    @RequestMapping("save")
    public String save(Employee employee,
                       MultipartFile img) throws IOException {
        //1. 处理头像的上传 & 修改文件名
        String originalFilename = img.getOriginalFilename();
        String newFileName=uploadPhoto(img, originalFilename);

        //2.保存员工信息
        employee.setPhoto(newFileName);
        employeeService.save(employee);
        //保存成功跳转到列表页面
        return "redirect:/employee/lists";
    }

    /**
     * 根据id查询员工的信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("detail")
    public String detail(Integer id, Model model){
        //1.根据id查询一个员工
        Employee employee=employeeService.findById(id);
        model.addAttribute("employee", employee);
        //跳转到更新页面
        return "updateEmp";
    }

    @RequestMapping("update")
    public String update(Employee employee,
                         MultipartFile img) throws IOException {
        //1. 判断是否更新头像
        boolean notEmpty=!img.isEmpty();

        if(notEmpty){
            //头像不为空
            //1. 删除老的头像
            String oldPhoto=employeeService.findById(employee.getId()).getPhoto();
            File file =new File(realpath, oldPhoto);
            if(file.exists()) file.delete();
            //2. 处理新头像上传
            String originalFilename = img.getOriginalFilename();
            String newFileName=uploadPhoto(img, originalFilename);
            //3. 修改员工新的头像名称
            employee.setPhoto(newFileName);
        }

        //2. 没有更新头像直接更新基本信息
        employeeService.update(employee);

        //更新成功，跳转到员工列表
        return "redirect:/employee/lists";
    }

    //抽取方法 refactor -> extracted method
    private String uploadPhoto(MultipartFile img, String originalFilename) throws IOException {
        String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String fileNameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName=fileNamePrefix+fileNameSuffix;
        img.transferTo(new File(realpath,newFileName));
        return newFileName;
    }

    /**
     * 删除员工信息
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public String delete(Integer id){
        //1. 删除数据
        String photo=employeeService.findById(id).getPhoto();
        employeeService.delete(id);
        //2. 删除头像
        File file=new File(realpath,photo);
        if(file.exists()) file.delete();
        return "redirect:/employee/lists";
    }
}
