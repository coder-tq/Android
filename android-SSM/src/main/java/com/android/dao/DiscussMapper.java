package com.android.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.android.pojo.*;

import java.util.List;

public interface DiscussMapper {

    @Select("SELECT * FROM android.discuss")
    List<Discuss> queryAllDiscuss();
    @Select("SELECT * FROM android.discuss WHERE id = #{Discuss_id}")
    List<Discuss> queryDiscussByDiscuss_id(int Discuss_id);
    @Select("SELECT * FROM android.discuss WHERE title LIKE CONCAT('%',#{title},'%')")
    List<Discuss> queryDiscussByTitle(String title);


    int updateDiscuss(Discuss discuss);

    @Insert("INSERT INTO discuss (title,content,author,date) values(#{title}, #{content}, #{author}, #{date})")
    int insertDiscuss(Discuss discuss);//返回

    int deleteDiscussByDiscuss_id(String discuss);



}
