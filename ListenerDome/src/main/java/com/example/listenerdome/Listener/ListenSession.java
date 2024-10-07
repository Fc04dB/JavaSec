package com.example.listenerdome.Listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class ListenSession implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //检测到Session创建
        System.out.println("Listener:Session created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //检测到Sessionx销毁
        System.out.println("Listener:Session destroyed");
    }
}
