package com.example.springbootshirodemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @RequestMapping("/login")
    public String loginout(Model model){
        model.addAttribute("msg","请登录，认证失败");
        return "login";
    }
    @RequestMapping("/")
    public String defaultPage(HttpServletResponse response){
        return "forward:/login";
    }
    @RequestMapping("/unauthorized")
    public String unauthorized(HttpServletResponse response){
        return "unauthorized";
    }
}
