package com.example.reflectdome;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GetRunExec {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        //原生调用
        //Runtime.getRuntime().exec("calc"); //弹出计算器

        //第三方jar包

        Class aClass = Class.forName("java.lang.Runtime");
        //获取getRuntime方法
        Method getRuntime = aClass.getMethod("getRuntime");
        System.out.println(getRuntime);
        //获取exec方法
        Method exec = aClass.getMethod("exec", String.class);
        //使用反射调用 aClass 对象的 getRuntime 方法，获取 Runtime 对象
        Object runtime = getRuntime.invoke(aClass);
        // 使用反射调用 Runtime 对象的 exec 方法，执行系统命令 "calc.exe"
        exec.invoke(runtime, "calc.exe");

    }
}
