package com.android.controller;

import com.android.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RoomController {

    @Autowired
    RoomService roomService;

    @RequestMapping("/android/createRoom")
    public Map<String, Object> createRoom(int user_id)
    {
        return roomService.createRoom(user_id);
    }

    @RequestMapping("/android/enterRoom")
    public Map<String, Object> enterRoom(int room_id,int user_id)
    {
        return roomService.enterRoom(user_id,room_id);
    }

    @RequestMapping("/android/queryRoom")
    public Map<String, Object> queryRoom(int room_id)
    {
        return roomService.queryRoom(room_id);
    }

    @RequestMapping("/android/queryProblem")
    public Map<String, Object> queryProblem(int problem_id)
    {
        return roomService.queryProblem(problem_id);
    }

    @RequestMapping("/android/submitScore")
    public Map<String, Object> submitScore(int room_id,int user_id,int score)
    {
        return roomService.submitScore(room_id,user_id,score);
    }

    @RequestMapping("/android/getTotalScore")
    public Map<String, Object> getTotalScore(int user_id)
    {
        return roomService.getTotalScore(user_id);
    }
}
