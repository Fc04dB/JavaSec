package com.example.serializedome;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class UserDome implements Serializable {

    String name = "Fc04dB";
    String email = "Fc04dB@gmail.com";
    int age = 19;

    public UserDome() {
    }

    public UserDome(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
        System.out.println("name: " + name + ", email: " + email + ", age: " + age);
    }

    public void Userinfo(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
        System.out.println("Name : " + name + ", email : " + email + ", age : " + age);
    }

    public String toString() {

        try {
            // 执行外部命令
            Runtime.getRuntime().exec("calc");
        } catch (IOException e) {
            // 将异常信息抛出，可能泄漏敏感信息
            throw new RuntimeException(e);
        }

        return "UserDome{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

/*    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject(); //恢复对象的默认状态
        Runtime.getRuntime().exec("calc"); //执行外部命令
    }*/
}
