package com.example.reflectdome;

import java.lang.reflect.Field;

public class GetField {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class aClass = Class.forName("com.example.reflectdome.User");
/*        Field[] fields = aClass.getFields(); //获取公共成员变量
        for (Field f : fields) {
            System.out.println(f);
        }
        System.out.println("-------------------------------------------------");

        //获取所有成员变量
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field f : declaredFields) {
            System.out.println(f);
        }
        System.out.println("-------------------------------------------------");

        //获取单个成员变量
        Field name = aClass.getDeclaredField("name");
        System.out.println(name);*/

        //获取公共成员变量age
        User u = new User();
        Field field = aClass.getField("age");

        //取值
        Object ageBefore = field.get(u);
        System.out.println(ageBefore);

        //赋值
        field.set(u,20);
        Object ageAfter = field.get(u);
        System.out.println(ageAfter);
    }
}
