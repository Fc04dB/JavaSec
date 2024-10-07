package com.Fc0;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

//使用Fastjson处理User类数据
public class FastjsonTest {
    public static void main(String[] args) {
        //Integer age ,String name 都是字符串数据
        User user = new User();
/*        user.setAge(19);
        user.setName("Fc04dB");
        System.out.println(user);

        //使用Fastjson把数据转化为Json数据
        String jsonString = JSONObject.toJSONString(user);
        System.out.println(jsonString);

        //输出 转换数据的类型（类）
        String jsonString2 = JSONObject.toJSONString(user, SerializerFeature.WriteClassName);
        System.out.println(jsonString2);*/

        //JSON --> 对象
        String test = "{\"@type\":\"com.Fc0.Run\",\"age\":19,\"name\":\"Fc04dB\"}";
        JSONObject jsonObject = JSON.parseObject(test);
        System.out.println(jsonObject);
    }
}
