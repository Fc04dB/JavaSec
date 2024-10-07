package com.example.serializedome;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializeDome {
    public static void main(String[] args) throws IOException {
        //创建一个对象，引用UserDome
        UserDome u = new UserDome("QB","fc04db@gmail.com",20);
        //调用方法SerializeTest
        SerializeTest(u);
        //ser.txt就是对象u序列化的字节流
    }

    //将对象obj序列化后输出文件ser.txt
    public static void SerializeTest(Object obj) throws IOException {
        // FileOutputStream()输出文件
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ser.txt"));
        oos.writeObject(obj);
    }
}
