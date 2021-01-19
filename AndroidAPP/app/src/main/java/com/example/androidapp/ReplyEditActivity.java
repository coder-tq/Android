package com.example.androidapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ReplyEditActivity extends AppCompatActivity {

    private EditText etReplyContent;
    private TextView mtvConfirm;
    private TextView mtvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_edit);

        etReplyContent = (EditText) findViewById(R.id.reply_et_context);
        mtvConfirm = (TextView) findViewById(R.id.confirmReply);
        mtvBack = (TextView) findViewById(R.id.backToReply);

        setListeners();
    }

    private void setListeners(){
        ReplyEditActivity.OnClickBackToReply onClickBackToReply = new ReplyEditActivity.OnClickBackToReply();
        mtvBack.setOnClickListener(onClickBackToReply);
        ReplyEditActivity.OnClickConfirmReply onClickConfirmReply = new ReplyEditActivity.OnClickConfirmReply();
        mtvConfirm.setOnClickListener(onClickConfirmReply);
    }

    private class OnClickBackToReply implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ReplyEditActivity.this, ReplyActivity.class);
            startActivity(intent);
        }
    }

    private class OnClickConfirmReply implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            new Thread(confirmReplyTask).start();
            Intent intent = new Intent(ReplyEditActivity.this, ReplyActivity.class);
            startActivity(intent);
        }
    }

    Runnable confirmReplyTask = new Runnable() {
        @Override
        public void run() {
            confirmReply();
        }
    };

    private void confirmReply(){
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = preferences.getString("user_id", "1");
        SharedPreferences _preferences = getSharedPreferences("discuss", Context.MODE_PRIVATE);
        String discuss_id = String.valueOf(_preferences.getInt("discuss_id", 1));
        try {
            String string = PostTool.sendPost("http://101.201.100.218/android/insertReply",
                     "content=" + etReplyContent.getText() + "&author=" + id + "&discussId=" + discuss_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}