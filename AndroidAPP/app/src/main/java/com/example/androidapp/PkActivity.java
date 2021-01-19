package com.example.androidapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.androidapp.pojo.Paper;
import com.example.androidapp.pojo.Problem;
import com.example.androidapp.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class PkActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvA;
    private TextView tvB;
    private TextView tvC;
    private TextView tvD;
    private Handler handler = new Handler();
    private Handler _handler = new Handler();
    private Paper paper = new Paper();
    List<Integer> problems = new ArrayList<>();
    Problem[] parsedProblem = new Problem[5];
    int current = 0;
    int correct = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pk);

        tvTitle = findViewById(R.id.exam_tv_title);
        tvA = findViewById(R.id.exam_tv_A);
        tvB = findViewById(R.id.exam_tv_B);
        tvC = findViewById(R.id.exam_tv_C);
        tvD = findViewById(R.id.exam_tv_D);

        new Thread(getProblemTask).start();

        setListeners();
    }

    Runnable getProblemTask = new Runnable() {
        @Override
        public void run() {
            getProblems();
            for(int i=0; i<5; i++){
                parsedProblem[i] = getProblem(problems.get(i));
            }
            handler.post(new Runnable() {  //主线程才能进行UI操作
                @Override
                public void run() {
                    setProblem();
                }
            });
        }
    };

    private void getProblems(){
        SharedPreferences preferences = getSharedPreferences("room", Context.MODE_PRIVATE);
        String room_id = preferences.getString("room_id", "");
        try {
            String string = PostTool.sendPost("http://101.201.100.218/android/queryRoom", "room_id=" + room_id);
            problems = praseProblems(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Problem getProblem(int problem_id){
        try {
            String string = PostTool.sendPost("http://101.201.100.218/android/queryProblem", "problem_id=" + problem_id);
            return praseProblem(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Integer> praseProblems(String string){
        JSONObject jsonObject = JSON.parseObject(string);
        return jsonObject.getJSONArray("problems").toJavaList(Integer.class);
    }

    private Problem praseProblem(String string){
        JSONObject jsonObject = JSON.parseObject(string);
        return jsonObject.getObject("problem", Problem.class);
    }

    private Paper prasePaper(String string){
        JSONObject jsonObject = JSON.parseObject(string);
        return jsonObject.getObject("room_info", Paper.class);
    }

    private void setProblem(){
        if(current > 4) {
            tvTitle.setText("等待对手答题完成");
            tvA.setText("");
            tvB.setText("");
            tvC.setText("");
            tvD.setText("");
            return;
        }
        String[] string = parsedProblem[current].getContent().split("#");
        tvTitle.setText(parsedProblem[current].getTitle());
        tvA.setText(string[0]);
        tvB.setText(string[1]);
        tvC.setText(string[2]);
        tvD.setText(string[3]);
        tvA.setTextColor(Color.BLACK);
        tvB.setTextColor(Color.BLACK);
        tvC.setTextColor(Color.BLACK);
        tvD.setTextColor(Color.BLACK);
        current++;
    }

    private void setListeners(){
        PkActivity.OnClickShowAns onClickShowAns = new PkActivity.OnClickShowAns();
        tvA.setOnClickListener(onClickShowAns);
        tvB.setOnClickListener(onClickShowAns);
        tvC.setOnClickListener(onClickShowAns);
        tvD.setOnClickListener(onClickShowAns);
    }

    private class OnClickShowAns implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            System.out.println(v.getId());
            System.out.println(tvA.getText());
            System.out.println(tvB.getText());
            System.out.println(tvC.getText());
            System.out.println(tvD.getText());
            System.out.println(parsedProblem[current-1].getAns());
            TextView textView = null;
            if(v.getId() == tvA.getId()){
                tvA.setTextColor(Color.RED);
                textView = tvA;
            }
            if(v.getId() == tvB.getId()){
                tvB.setTextColor(Color.RED);
                textView = tvB;
            }
            if(v.getId() == tvC.getId()){
                tvC.setTextColor(Color.RED);
                textView = tvC;
            }
            if(v.getId() == tvD.getId()){
                tvD.setTextColor(Color.RED);
                textView = tvD;
            }
            if(textView.getText().equals(parsedProblem[current-1].getAns())){
                correct++;
            }
            if(tvA.getText().equals(parsedProblem[current-1].getAns())){
                tvA.setTextColor(Color.parseColor("#00FA9A"));
            }
            if(tvB.getText().equals(parsedProblem[current-1].getAns())){
                tvB.setTextColor(Color.parseColor("#00FA9A"));
            }
            if(tvC.getText().equals(parsedProblem[current-1].getAns())){
                tvC.setTextColor(Color.parseColor("#00FA9A"));
            }
            if(tvD.getText().equals(parsedProblem[current-1].getAns())){
                tvD.setTextColor(Color.parseColor("#00FA9A"));
            }
            if(current > 4){
                SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                final String user_id = preferences.getString("user_id", "1");
                SharedPreferences _preferences = getSharedPreferences("room", Context.MODE_PRIVATE);
                final String room_id = _preferences.getString("room_id", "");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            PostTool.sendPost("http://101.201.100.218/android/submitScore",
                                    "user_id=" + user_id + "&room_id=" + room_id + "&score=" + correct);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                ).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            String string = PostTool.sendPost("http://101.201.100.218/android/queryRoom",
                                    "room_id=" + room_id);
                            paper = prasePaper(string);
                            while(paper.getUserAScore() == -1 || paper.getUserBScore() == -1){
                                Thread.sleep(1000);
                                string = PostTool.sendPost("http://101.201.100.218/android/queryRoom",
                                        "room_id=" + room_id);
                                paper = prasePaper(string);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        _handler.post(new Runnable() {  //主线程才能进行UI操作
                            @Override
                            public void run() {
                                Intent intent;
                                System.out.println(user_id);
                                System.out.println(paper.getUserA());
                                if(Integer.parseInt(user_id) == paper.getUserA()){
                                    if(paper.getUserAScore() >= paper.getUserBScore()){
                                        intent = new Intent(PkActivity.this, PkResultWinActivity.class);
                                    }
                                    else intent = new Intent(PkActivity.this, PkResultFailActivity.class);
                                }
                                else{
                                    if(paper.getUserAScore() >= paper.getUserBScore()){
                                        intent = new Intent(PkActivity.this, PkResultFailActivity.class);
                                    }
                                    else intent = new Intent(PkActivity.this, PkResultWinActivity.class);
                                }
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                }
                ).start();

            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {  //主线程才能进行UI操作
                        @Override
                        public void run() {
                            setProblem();
                        }
                    });
                }
            }
            ).start();
        }
    }
}