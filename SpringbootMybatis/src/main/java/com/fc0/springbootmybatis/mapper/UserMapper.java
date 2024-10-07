package com.fc0.springbootmybatis.mapper;

import com.fc0.springbootmybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from demo01")
    public List<User> findAll() ;

    @Select("select * from demo01 where id =1")
    public List<User> findID() ;
}
