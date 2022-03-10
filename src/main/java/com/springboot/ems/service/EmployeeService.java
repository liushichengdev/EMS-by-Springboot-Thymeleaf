package com.springboot.ems.service;

import com.springboot.ems.entity.Employee;

import java.util.List;

public interface EmployeeService {
    //员工列表方法
    List<Employee> lists();

    //保存员工信息
    void save(Employee employee);

    //根据id查询员工
    Employee findById(Integer id);

    //更新员工信息
    void update(Employee employee);

    //删除员工信息
    void delete(Integer id);
}
