package com.example.serializedome;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class UrlDns implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        HashMap<URL,Integer> hash = new HashMap<>();
        URL url = new URL("http://a6znek.dnslog.cn");
        hash.put(url,1); //序列化对象hash，来源于自带类HashMap

        SerializeTest(hash);
        UnserializeTeat("dns.txt");
    }

    public static void SerializeTest(Object obj) throws IOException {
        // FileOutputStream()输出文件
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dns.txt"));
        oos.writeObject(obj);
    }

    public static Object UnserializeTeat(String Filename) throws IOException, ClassNotFoundException {
        //将Filename反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Filename));
        Object o = ois.readObject();
        return o;
    }
}
