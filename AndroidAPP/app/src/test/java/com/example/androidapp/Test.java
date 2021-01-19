package com.example.androidapp;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.androidapp.pojo.User;
import junit.framework.TestCase;

public class Test extends TestCase {

    public void test() {
        JSONObject jsonObject = null;
        try {
            jsonObject = JSON.parseObject(PostTool.sendPost("http://101.201.100.218/android/checkUser","phone=110&pass=123"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(jsonObject.getObject("user", User.class).toString());
    }

}
