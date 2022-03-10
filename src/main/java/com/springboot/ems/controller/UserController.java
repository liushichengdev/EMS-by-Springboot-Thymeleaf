package com.springboot.ems.controller;

import com.mysql.cj.util.StringUtils;
import com.springboot.ems.entity.User;
import com.springboot.ems.service.UserService;
import com.springboot.ems.utils.VerifyCodeUtils ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 生成验证码
     */
    @RequestMapping("generateImageCode")
    public void generateImageCode(HttpSession session,
                                  HttpServletResponse response) throws IOException {
        //1. 生成4位随机数
        String code=VerifyCodeUtils.generateVerifyCode(4);
        //2. 保存到session作用域
        session.setAttribute("code",code);
        //3. 根据随机数生成图片 && 4. 通过response响应图片 && 5.设置响应类型
        response.setContentType("image/png");
        ServletOutputStream os=response.getOutputStream();
        VerifyCodeUtils.outputImage(220,60, os, code);

    }

    /**
     * 用户注册
     * @Return
     */
    @RequestMapping("register")
    public String register(User user, String code,HttpSession session){
        try {
            //1. 判断用户输入的验证码和session中验证码会否一致
            String sessionCode = session.getAttribute("code").toString();
            if (!sessionCode.equalsIgnoreCase(code)) throw new RuntimeException("验证码输入错误");
            //2.用户注册
            userService.register(user);
        } catch (RuntimeException e){
            //注册失败回到注册
            return "redirect:/register";
        }
        //注册成功跳转到登录
        return "redirect:/login";
    }

    /**
     * 用户登录
     * @return
     */
    @RequestMapping("login")
    public String login(String username,
                        String password,
                        HttpSession session){
        try {//1. 调用业务层进行登录
            User user = userService.login(username, password);
            //2. 保存用户信息
            session.setAttribute("user", user);
        } catch (Exception e){
            //登录失败，回到登录页面
            return "redirect:/login";
        }
        //登录成功之后，跳转到查询所有员工信息控制器路径
        return "redirect:/employee/lists";
    }

    /**
     * 安全退出
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();

        return "redirect:/login";
    }
}
