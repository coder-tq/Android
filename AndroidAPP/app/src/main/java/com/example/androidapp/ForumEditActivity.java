package com.example.androidapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.alibaba.fastjson.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class ForumEditActivity extends AppCompatActivity {

    private EditText etForumTitle;
    private EditText etForumContent;
    private TextView mtvConfirm;
    private TextView mtvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_edit);

        etForumTitle = (EditText) findViewById(R.id.forum_et_title);
        etForumContent = (EditText) findViewById(R.id.forum_et_context);
        mtvConfirm = (TextView) findViewById(R.id.confirmDiscuss);
        mtvBack = (TextView) findViewById(R.id.backToDiscuss);

        setListeners();
    }

    private void setListeners(){
        ForumEditActivity.OnClickBackToDiscuss onClickBackToDiscuss = new ForumEditActivity.OnClickBackToDiscuss();
        mtvBack.setOnClickListener(onClickBackToDiscuss);
        ForumEditActivity.OnClickConfirmDiscuss onClickConfirmDiscuss = new ForumEditActivity.OnClickConfirmDiscuss();
        mtvConfirm.setOnClickListener(onClickConfirmDiscuss);
    }

    private class OnClickBackToDiscuss implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ForumEditActivity.this, DiscussActivity.class);
            startActivity(intent);
        }
    }

    private class OnClickConfirmDiscuss implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            new Thread(confirmDiscussTask).start();
            Intent intent = new Intent(ForumEditActivity.this, DiscussActivity.class);
            startActivity(intent);
        }
    }

    Runnable confirmDiscussTask = new Runnable() {
        @Override
        public void run() {
            confirmDiscuss();
        }
    };

    private void confirmDiscuss(){
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = preferences.getString("user_id", "1");
        try {
            String string = PostTool.sendPost("http://101.201.100.218/android/insertDiscuss",
                    "title=" + new String((etForumTitle.getText().toString()).getBytes("UTF-8"), StandardCharsets.UTF_8) + "&content=" + etForumContent.getText().toString() + "&author=" + id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}