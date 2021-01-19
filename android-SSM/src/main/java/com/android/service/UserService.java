package com.android.service;

import com.android.dao.UserMapper;
import com.android.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Service
public interface UserService {

    public Map<String, Object> insertUser(User user);
    public Map<String, Object> checkUser(User user);
    public Map<String,Object> selectUserById(Integer id);
}
