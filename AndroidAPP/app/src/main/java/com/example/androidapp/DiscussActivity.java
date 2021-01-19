package com.example.androidapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.androidapp.pojo.Discuss;
import com.example.androidapp.pojo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiscussActivity extends AppCompatActivity {

    private ListView lv;
    private Handler handler = new Handler();
    JSONArray jsonArray = new JSONArray();
    ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
    private TextView mTvBackToMain;
    private TextView mTvCreateDiscuss;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);

        mTvBackToMain = findViewById(R.id.backToMain);
        mTvCreateDiscuss = findViewById(R.id.createDiscuss);
        lv = (ListView) findViewById(R.id.listView);
        // 定义一个以HashMap为内容的动态数组 在数组中存放数据
        new Thread(checkDiscussTask).start();
        setListeners();
    }

    Runnable checkDiscussTask = new Runnable() {
        @Override
        public void run() {
            getDiscuss();
            for(int i = 0; i < jsonArray.size(); i++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                JSONObject obj = jsonArray.getJSONObject(i);
//                        Discuss discuss = obj.toJavaObject(Discuss.class);
//                        System.out.println(discuss.toString());
                map.put("discussTitle", (String) obj.get("title"));
                map.put("discussContent", (String) obj.get("content"));
                map.put("discussAuthor", getAuthorName(obj.get("author")));
                listItem.add(map);
            }
            handler.post(new Runnable() {  //主线程才能进行UI操作
                @Override
                public void run() {
                    MyAdapter adapter = new MyAdapter(DiscussActivity.this, listItem);
                    lv.setAdapter(adapter); // 为ListView绑定适配器
                }
            });

        }
    };

    private String getAuthorName(Object author) {
        String name = null;
        try {
            String string = PostTool.sendPost("http://101.201.100.218/android/selectUserById", "id=" + author);
            User user = praseUser(string);
            name = user.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    private void getDiscuss(){
        try {
            String string = PostTool.sendPost("http://101.201.100.218/android/queryAllDiscuss", "");
            jsonArray = praseJSONArray(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private User praseUser(String string){
        JSONObject jsonObject = JSON.parseObject(string);
        return jsonObject.getObject("user", User.class);
    }

    private JSONArray praseJSONArray(String string){
        JSONObject jsonObject = JSON.parseObject(string);
//        System.out.println(jsonObject.toString());
        return jsonObject.getJSONArray("list");
    }

    private void setListeners(){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                saveDiscussId(arg2);
                Intent intent = new Intent(DiscussActivity.this, ReplyActivity.class);
                startActivity(intent);
            }
        });
        OnClickBackToMain onClickBackToMain = new OnClickBackToMain();
        mTvBackToMain.setOnClickListener(onClickBackToMain);
        OnClickCreateDiscuss onClickCreateDiscuss = new OnClickCreateDiscuss();
        mTvCreateDiscuss.setOnClickListener(onClickCreateDiscuss);
    }

    private class OnClickBackToMain implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(DiscussActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private class OnClickCreateDiscuss implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(DiscussActivity.this, ForumEditActivity.class);
            startActivity(intent);
        }
    }

    private void saveDiscussId(Integer discuss_id){
        SharedPreferences sharedPreferences = getSharedPreferences("discuss", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putInt("discuss_id", discuss_id+1);
        editor.commit();//提交修改
    }

}