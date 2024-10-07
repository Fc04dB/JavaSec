package com.example.reflectdome;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class GetConstructor {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class aClass = Class.forName("com.example.reflectdome.User");
/*        //获取公共的构造方法
        Constructor[] constructors = aClass.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        System.out.println("-----------------------------------------------------------------");
        //获取所有构造方法
        Constructor[] constructors2 = aClass.getDeclaredConstructors();
        for (Constructor constructor : constructors2) {
            System.out.println(constructor);
        }
        System.out.println("-----------------------------------------------------------------");
        //获取单个公共的构造方法
        Constructor constructor1 = aClass.getConstructor(String.class);
        System.out.println(constructor1);*/

        //对构造方法进行操作
        Constructor cons = aClass.getDeclaredConstructor(String.class,int.class);
        cons.setAccessible(true); //设置为true,表示取消访问检查，临时开启对私有的访问
        User U1 = (User) cons.newInstance("QB",88); //根据指定的构造方法创建对象
        System.out.println(U1);
    }
}
