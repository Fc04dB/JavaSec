package com.Fc0;

//给fastjson数据转换测试用的
public class User {
    private String name;
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(Integer age) {
        this.age = age;
        System.out.println(age);
    }

    public void setName(String name) {
        this.name = name;
        System.out.println(name);
    }
}
