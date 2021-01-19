package com.android.service;

import com.android.dao.UserMapper;
import com.android.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public Map<String, Object> insertUser(User user)
    {

        Map<String, Object> mp = new TreeMap<>();
        try {
            mp.put("state",userMapper.insertUser(user));
        }
        catch (Exception e)
        {
            mp.put("state",0);
        }
        return mp;
    }
    public Map<String, Object> checkUser(User user)
    {

        Map<String, Object> mp = new TreeMap<>();
        User tmp;
        try {
            tmp = userMapper.selectUser(user);
            if (user.getPass().equals(tmp.getPass())){
                mp.put("state", 1);
                mp.put("user", tmp);
            }
            else {
                mp.put("state", 0);
                mp.put("msg","用户名或密码错误");

            }
        }
        catch (Exception e)
        {
            //mp.put("error",e.toString());
            mp.put("msg","用户不存在");
            mp.put("state",0);
        }
        return mp;
    }

    @Override
    public Map<String,Object> selectUserById(Integer id) {
        Map<String, Object> mp = new TreeMap<>();
        mp.put("user", userMapper.selectUserById(id));
        return mp;
    }

    @Test
    public void test()
    {
        System.out.println("尝试连接数据库...");
        System.out.println("连接数据库成功！");
    }

}
