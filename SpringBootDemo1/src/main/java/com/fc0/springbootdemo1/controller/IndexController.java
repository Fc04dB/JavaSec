package com.fc0.springbootdemo1.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class IndexController {
    //指定Get请求的访问路由
    @RequestMapping(value = "/Fc0Get",method = RequestMethod.GET )
    //@GetMapping("value = /Fc0Get")
    public String getindex() {
        return "Get Fc0";
    }

    //指定Post请求的访问路由
    @RequestMapping(value = "/Fc0Post",method = RequestMethod.POST )
    //@PostMapping("value = /Fc0Get")
    public String getPOST() {
        return "Post Fc0";
    }

    // 指定GET请求的访问路由，带参数名name
    @RequestMapping(value = "/Fc0Get_g", method = RequestMethod.GET)
    //@GetMapping(value = "/xiaodiget")
    public String get_g(@RequestParam String name) {
        return "get name: " + name;
    }

    // 指定POST请求的访问路由，带参数名name
    @RequestMapping(value = "/Fc0Post_p", method = RequestMethod.POST)
    //@GetMapping(value = "/xiaodiget_g")
    public String get_p(@RequestParam String name) {
        return "post name: " + name;
    }
}
