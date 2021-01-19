package com.android.controller;

import com.android.dao.UserMapper;
import com.android.pojo.User;
import com.android.service.UserService;
import com.android.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/android/insertUser")
    public Map<String, Object> insertUser(User user) {
        return userService.insertUser(user);
    }

    @RequestMapping("/android/checkUser")
    public Map<String, Object> checkUser(User user) {
        return userService.checkUser(user);
    }

    @RequestMapping("/android/selectUserById")
    public Map<String,Object> selectUserById(Integer id)
    {
        return userService.selectUserById(id);
    }
}
