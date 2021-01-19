package com.android.dao;

import com.android.pojo.Reply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReplyMapper {
    @Select("SELECT * FROM reply WHERE discuss_id = #{discuss_id}")
    List<Reply> queryReplyByDiscussId(Integer discuss_id);

    @Insert("INSERT reply(discuss_id,author,content,date) values (#{discussId},#{author},#{content},#{date})")
    int insertReply(Reply reply);
}
