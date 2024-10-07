package com.example.reflectdome;

public class GetClass {
    public static void main(String[] args) throws ClassNotFoundException {
        //根据全限定类名：Class.forName("全路径类名")
        Class aClass = Class.forName("com.example.reflectdome.User");
        System.out.println(aClass);

        //根据: obj.class
        Class bClass = User.class;
        System.out.println(bClass);

        //根据: obj.getClass()
        User user = new User();
        Class dClass = User.class;
        System.out.println(dClass);

        //根据类加载器
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class fClass = classLoader.loadClass("com.example.reflectdome.User");
        System.out.println(fClass);
    }
}
