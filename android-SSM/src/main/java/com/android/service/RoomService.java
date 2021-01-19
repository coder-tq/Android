package com.android.service;

import com.android.dao.RoomMapper;
import com.android.pojo.Paper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class RoomService {

    @Autowired
    RoomMapper roomMapper;

    public Map<String,Object> createRoom(int user_id)
    {
        Paper paper = new Paper();
        paper.setUserA(user_id);
        roomMapper.insertRoom(paper);
//        int room_id = roomMapper.insertRoom(user_id, new java.sql.Date(new Date().getTime()));
        Random random = new Random(new Date().getTime());
        Set<Integer> problem = new TreeSet<>();
        while(problem.size() < 5)
        {
            problem.add(Math.abs(random.nextInt())%10+1);
        }
        for(int i : problem)
        {
            roomMapper.insertProblem(i,(int)paper.getId());
        }
        Map<String, Object> mp = new TreeMap<>();
        mp.put("room_id",paper.getId());
        return mp;
    }

    public Map<String, Object> enterRoom(int user_id,int room_id)
    {
        Paper paper = new Paper();
        paper.setId(room_id);
        paper.setUserB(user_id);
        paper.setStartDate(new Timestamp(new Date().getTime()+10000));

        Map<String, Object> mp = new TreeMap<>();
        try {
            roomMapper.enterRoom(paper);
            mp.put("state",1);
        }
        catch (Exception e)
        {
            mp.put("state",0);
            mp.put("error",e.toString());
        }
        return mp;
    }

    public Map<String,Object> queryRoom(int room_id)
    {
        Map<String, Object> mp = new TreeMap<>();
        try {
            Paper paper = roomMapper.queryRoom(room_id);
            if(paper.getUserB()!=0)
            {
                mp.put("state",1);
                mp.put("room_info",paper);
                mp.put("problems",roomMapper.queryProblemByRoomId(room_id));
            }
            else{
                mp.put("state",0);
            }
        }
        catch (Exception e)
        {
            mp.put("state",0);
            mp.put("error",e.toString());
        }

        return mp;
    }

    public Map<String, Object> queryProblem(int problem_id)
    {
        Map<String, Object> mp = new TreeMap<>();
        try {
            mp.put("state",1);
            mp.put("problem",roomMapper.queryProblem(problem_id));
        } catch (Exception e)
        {
            mp.put("state",0);
            mp.put("error",e.toString());
        }
        return mp;
    }

    public Map<String, Object> submitScore(int room_id,int user_id,int score)
    {
        Map<String, Object> mp = new TreeMap<>();
        try {
            Paper room = roomMapper.queryRoom(room_id);
            if(room.getUserA() == user_id)
            {
                roomMapper.updateRoomA(room_id,score);
                mp.put("state",1);
            }
            else if(room.getUserB() == user_id) {
                roomMapper.updateRoomB(room_id,score);
                mp.put("state",1);
            }
            else {
                mp.put("state",0);
            }
        }
        catch (Exception e)
        {
            mp.put("state",0);
            mp.put("error",e.toString());
        }
        return mp;
    }

    public Map<String, Object> getTotalScore(int user_id)
    {
        int sum = 0;
        Map<String, Object> mp = new TreeMap<>();
        List<Integer> listA = roomMapper.queryTotalScoreA(user_id);
        List<Integer> listB = roomMapper.queryTotalScoreB(user_id);
        for(Integer i:listA)
        {
            if(i < 0) {
            }
            else sum+=i;
        }
        for(Integer i:listB)
        {
            if(i < 0) {
            }
            else sum+=i;
        }
        mp.put("score",sum);
        return mp;
    }
}
