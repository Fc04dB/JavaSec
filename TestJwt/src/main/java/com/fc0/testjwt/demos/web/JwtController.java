package com.fc0.testjwt.demos.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JwtController {
    @PostMapping("/jwtcreate")
    @ResponseBody
    public static String create(Integer id, String user, String pass) {
        String jwt = JWT.create() //创建JWT
                //设置header
                //.withHeader()

                //设置payload部分
                .withClaim("userid", id)
                .withClaim("username", user)
                .withClaim("password", pass)
                //.withExpiresAt() 设置过期时间

                //创建signature
                .sign(Algorithm.HMAC256("fcqb")); //算法和密钥

        System.out.println(jwt);

        return jwt;
    }

    //解密JWT
    @PostMapping("/jwtcheck")
    @ResponseBody
    public static String jwtcheck(String jwtdata){
        //String jwtdata = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6ImFkbWluIiwidXNlcmlkIjoxLCJ1c2VybmFtZSI6ImFkbWluIn0.dwnU1P_KAmhuTY4qqBAB2wZBOLlkvSQyU9FM8KhNAgE";

        //构建解密注册
        JWTVerifier jwtde = JWT.require(Algorithm.HMAC256("fcqb")).build();
        //解密注册数据
        DecodedJWT verify = jwtde.verify(jwtdata);
        //提取注册解密数据
        Integer userid = verify.getClaim("userid").asInt();
        String username = verify.getClaim("username").asString();
        String password = verify.getClaim("password").asString();

        System.out.println(userid +" "+ username +" "+ password);
        return userid +" "+ username +" "+ password;
    }
}
