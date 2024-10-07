package com.example.serializedome;

import java.io.*;

public class UnserializeDome {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //传输ser.txt 反序列化
        Object obj = UnserializeTeat("ser.txt");
        System.out.println(obj);
    }

    public static Object UnserializeTeat(String Filename) throws IOException, ClassNotFoundException {
        //将Filename反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Filename));
        Object o = ois.readObject();
        return o;
    }
}
