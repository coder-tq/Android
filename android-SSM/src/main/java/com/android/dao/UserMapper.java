package com.android.dao;

import com.android.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Insert("INSERT into user(email,name,pass,phone,note) values(#{email},#{name},#{pass},#{phone},#{note})")
    int insertUser(User user);

    @Select("SELECT * FROM user where phone = #{phone}")
    User selectUser(User user);

    @Select("SELECT id,note,name,phone,email FROM user WHERE id = #{id}")
    User selectUserById(Integer id);
}
