package com.example.reflectdome;

public class User {
    //成员变量
    public String name = "Fc04dB";
    public int age = 19;
    private String gender = "Male";
    protected String job = "Student";

    //构造方法
    public User() {
        //System.out.println("User无参数被初始化");
    }
    public User(String name) {
        this.name = name;
        System.out.println("我的名字"+name);
    }
    private User(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("我的名字:"+name+" 年龄："+age);
    }

    //成员方法
    public void Userinfo(String name,int age,String gender,String job) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.job = job;
    }

    private void Users(String name,int age) {
        this.name = name;
        this.age = age;
        System.out.println("name:"+name+" age:"+age);
    }
}
