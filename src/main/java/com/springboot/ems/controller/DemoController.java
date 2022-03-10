package com.springboot.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DemoController {

    @RequestMapping("demo")
    public String demo(Model model){
        //log.debug("demo ok");
        model.addAttribute("msg","Hello Thymeleaf");
        return "demo";
    }

//    @RequestMapping("login")
//    public String login(){
//        return "login";
//    }
}
