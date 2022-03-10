package com.springboot.ems.service.impl;

import com.springboot.ems.entity.Employee;
import com.springboot.ems.mapper.EmployeeMapper;
import com.springboot.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    /**
     * 员工列表
     * @return
     */
    @Override
    public List<Employee> lists() {
        List<Employee> lists = employeeMapper.lists();
        return lists;
    }

    /**
     * 保存员工信息
     * @param employee
     */
    @Override
    public void save(Employee employee) {
        employeeMapper.save(employee);
    }

    @Override
    public Employee findById(Integer id) {
        Employee employee=employeeMapper.findById(id);
        return employee;
    }

    /**
     * 更新员工信息
     * @param employee
     */
    @Override
    public void update(Employee employee) {
        employeeMapper.update(employee);
    }

    /**
     * 删除员工
     * @param id
     */
    @Override
    public void delete(Integer id) {
        employeeMapper.delete(id);
    }
}
