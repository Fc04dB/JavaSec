package com.example.reflectdome;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GetMethod {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class aClass = Class.forName("com.example.reflectdome.User");
/*        //获取所有成员方法
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("-----------------------------------------------------------------------");
        //获取不包括继承的成员方法
        Method[] methods2 = aClass.getDeclaredMethods();
        for (Method method : methods2) {
            System.out.println(method);
        }
        System.out.println("-----------------------------------------------------------------------");*/
        //获取单个成员方法
        Method Userinfo = aClass.getMethod("Userinfo", String.class, int.class, String.class, String.class);
        System.out.println(Userinfo);

        System.out.println("-----------------------------------------------------------------------");
        //执行成员方法
        User user = new User();
        Method Users = aClass.getDeclaredMethod("Users",String.class,int.class);
        System.out.println(Users);
        Users.setAccessible(true);
        Users.invoke(user,"QB",99);
    }
}
