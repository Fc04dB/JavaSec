package com.fc0.springbootmybatis.controller;

import com.fc0.springbootmybatis.entity.User;
import com.fc0.springbootmybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Getdemo01Controller {

    @Autowired
    private UserMapper userMapper;

    // 根据用户id检索管理员数据的端点
    @GetMapping("/getadmin")
    public List<User> getadmindata(){
        List<User> all = UserMapper.findAll();
        return all;
    }

    @GetMapping("/getid")
    public List<User> getadminid(){
        List<User> all = UserMapper.findID();
        return all;
    }

}
