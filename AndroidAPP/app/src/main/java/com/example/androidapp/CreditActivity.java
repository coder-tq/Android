package com.example.androidapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.androidapp.pojo.User;

public class CreditActivity extends AppCompatActivity {

    private TextView creditTV;
    private int score;
    private String user_id;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        creditTV = findViewById(R.id.credit_num);

        new Thread(getCreditTask).start();
    }

    Runnable getCreditTask = new Runnable() {
        @Override
        public void run() {
            getCredit();
            handler.post(new Runnable() {  //主线程才能进行UI操作
                @Override
                public void run() {
                    creditTV.setText(String.valueOf(score));
                }
            });
        }
    };

    private void getCredit(){
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        user_id = preferences.getString("user_id", "1");
        try {
            String string = PostTool.sendPost("http://101.201.100.218/android/getTotalScore",
                    "user_id=" + user_id);
            score = praseScore(string);
            saveScore(score);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int praseScore(String string){
        JSONObject jsonObject = JSON.parseObject(string);
        return jsonObject.getObject("score", Integer.class);
    }

    private void saveScore(Integer score){
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putInt("user_score", score);
        editor.commit();//提交修改
    }
}