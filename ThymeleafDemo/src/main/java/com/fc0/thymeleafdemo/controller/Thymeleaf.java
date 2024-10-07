package com.fc0.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RestController包含了 @ResponseBody 和 @Controller 的功能。@ResponseBody会把index当做字符串显示操作
public class Thymeleaf {
    @GetMapping("index")
    public String show1(Model model){
        model.addAttribute("data", "Hello, Fc04dB!");
        return "index";
    }
    @RequestMapping(value = "/test")
    public String index() {
        return "test";
    }
}
