package com.android.dao;

import com.android.pojo.Plan;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface PlanMapper {

//    @Select("SELECT name,content,type,user_id,plan_id,start_date,end_date,last_date,submit_content from plan INNER JOIN userPlan ON plan.id = userPlan.plan_Id WHERE user_id = #{id}")
//    @Results(id="planMap", value={
//            @Result(column="start_date", property="startDate"),
//            @Result(column="end_date", property="endDate"),
//            @Result(column="last_date", property="lastDate"),
//            @Result(column="submit_content", property="submitContent"),
//    })
//    public List<Map<String,Object>> queryPlanByUserId(Integer id);
    @Select("SELECT name,content,type,start_date,last_date,end_date,submit_content,plan_id from plan INNER JOIN userPlan ON plan.id = userPlan.plan_Id WHERE user_id = #{id}")
    //@Select("SELECT * from plan INNER JOIN userPlan ON plan.id = userPlan.plan_Id WHERE user_id = #{id}")
    List<Map<String,Object>> queryPlanByUserId(Integer id);

    @Update("UPDATE userPlan SET end_date = #{param1}, submit_content = #{param2} WHERE user_id = #{param3} and plan_id = #{param4}")
    int submitPlan(Date end_date,String submit_content,Integer user_id,Integer plan_id);

    @Select("SELECT * FROM plan WHERE id = #{id}")
    Plan queryPlanByPlanId(Integer id);
}
