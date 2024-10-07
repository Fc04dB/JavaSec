package org.example.fastjsonjndi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.example.fastjsonjndi.HelloServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 定义一个带有 "/json" 映射的 Servlet
@WebServlet("/json")
public class Fsweb extends HelloServlet {

    // 重写 doPost 方法以处理 HTTP POST 请求
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 从名为 "str" 的请求参数中检索 JSON 数据
        String jsondata = req.getParameter("str");

        // 将接收到的 JSON 数据打印到控制台
        System.out.println(jsondata);

        // 使用 fastjson 解析 JSON 数据为 JSONObject
        JSONObject jsonObject = JSON.parseObject(jsondata);

        // 打印解析后的 JSONObject 到控制台
        System.out.println(jsonObject);
    }
}
