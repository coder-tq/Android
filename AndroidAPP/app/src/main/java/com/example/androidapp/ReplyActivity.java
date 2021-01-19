package com.example.androidapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.androidapp.pojo.User;

import java.util.ArrayList;
import java.util.HashMap;

public class ReplyActivity extends AppCompatActivity {

    private ListView lv;
    private Handler handler = new Handler();
    JSONArray jsonArray = new JSONArray();
    ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
    private TextView mTvBackToDiscuss;
    private TextView mTvCreateReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        mTvBackToDiscuss = findViewById(R.id.backToDiscuss);
        mTvCreateReply = findViewById(R.id.createReply);
        lv = (ListView) findViewById(R.id.replyListView);
        // 定义一个以HashMap为内容的动态数组 在数组中存放数据
        new Thread(checkReplyTask).start();
        setListeners();
    }

    Runnable checkReplyTask = new Runnable() {
        @Override
        public void run() {
            getReply();
            for(int i = 0; i < jsonArray.size(); i++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                JSONObject obj = jsonArray.getJSONObject(i);
                map.put("replyContent", (String) obj.get("content"));
                map.put("replyAuthor", getAuthorName(obj.get("author")));
                listItem.add(map);
            }
            handler.post(new Runnable() {  //主线程才能进行UI操作
                @Override
                public void run() {
                    ReplyAdapter adapter = new ReplyAdapter(ReplyActivity.this, listItem);
                    lv.setAdapter(adapter); // 为ListView绑定适配器
                }
            });

        }
    };

    private void getReply(){
        SharedPreferences preferences = getSharedPreferences("discuss", Context.MODE_PRIVATE);
        int discuss_id = preferences.getInt("discuss_id", 1);
        try {
            String string = PostTool.sendPost("http://101.201.100.218/android/queryReplyByDiscussId",
                    "discuss_id=" + discuss_id);
            jsonArray = praseJSONArray(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    private User praseUser(String string){
        JSONObject jsonObject = JSON.parseObject(string);
        return jsonObject.getObject("user", User.class);
    }

    private JSONArray praseJSONArray(String string){
        JSONObject jsonObject = JSON.parseObject(string);
//        System.out.println(jsonObject.toString());
        return jsonObject.getJSONArray("reply");
    }

    private void setListeners(){
        ReplyActivity.OnClickBackToDiscuss onClickBackToDiscuss = new ReplyActivity.OnClickBackToDiscuss();
        mTvBackToDiscuss.setOnClickListener(onClickBackToDiscuss);
        ReplyActivity.OnClickCreateReply onClickCreateReply = new ReplyActivity.OnClickCreateReply();
        mTvCreateReply.setOnClickListener(onClickCreateReply);
    }

    private class OnClickBackToDiscuss implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ReplyActivity.this, DiscussActivity.class);
            startActivity(intent);
        }
    }

    private class OnClickCreateReply implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ReplyActivity.this, ReplyEditActivity.class);
            startActivity(intent);
        }
    }

}