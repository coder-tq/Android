package com.android.dao;

import com.android.pojo.Paper;
import com.android.pojo.Problem;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

public interface RoomMapper {

    @Insert("INSERT into paper(user_a) values (#{userA})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public int insertRoom(Paper paper);

    @Insert("INSERT into roomProblem(problem_id,room_id) values (#{param1},#{param2})")
    public int insertProblem(int problem_id,int room_id);

    @Update("UPDATE paper set start_date = #{startDate}, user_b = #{userB} WHERE id = #{id}")
    public int enterRoom(Paper paper);

    @Select("SELECT * FROM paper WHERE id = #{param1}")
    @Results(id = "roleResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "userA", column = "user_a"),
            @Result(property = "userB", column = "user_b"),
            @Result(property = "userAScore", column = "user_a_score"),
            @Result(property = "userBScore", column = "user_b_score")
    })
    public Paper queryRoom(int room_id);

    @Select("SELECT problem_id FROM roomProblem WHERE room_id = #{param1}")
    public List<Integer> queryProblemByRoomId(int room_id);

    @Select("SELECT * FROM problem WHERE id = #{param1}")
    public Problem queryProblem(int problem_id);

    @Update("UPDATE paper set user_a_score = #{param2} WHERE id = #{param1}")
    void updateRoomA(int room_id,int score);
    @Update("UPDATE paper set user_b_score = #{param2} WHERE id = #{param1}")
    void updateRoomB(int room_id,int score);

    @Select("SELECT user_a_score from paper where user_a = #{param1}")
    List<Integer> queryTotalScoreA(int user_id);
    @Select("SELECT user_b_score from paper where user_b = #{param1}")
    List<Integer> queryTotalScoreB(int user_id);
}
