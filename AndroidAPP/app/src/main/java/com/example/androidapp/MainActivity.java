package com.example.androidapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

//    private TextView mtv_welcome;
    private int SCAN_REQUEST_CODE=200;
    protected final int PERMS_REQUEST_CODE = 202;
    private Button btnChampion;
    private Button btnChallenge;
    private Button btnTestself;
    private Button btnTask;
    private Button btnStatistic;
    private Button btnTalk;
    private ImageView photoUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] permissions=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            requestPermissions(permissions,PERMS_REQUEST_CODE);
        }

        btnChampion = findViewById(R.id.main_btn_champion);
        btnChallenge = findViewById(R.id.main_btn_challenge);
        btnTestself = findViewById(R.id.main_btn_testself);
        btnTask = findViewById(R.id.main_btn_task);
        btnStatistic = findViewById(R.id.main_btn_statistic);
        btnTalk = findViewById(R.id.main_btn_talk);
        photoUser = findViewById(R.id.imageView);

//        mtv_welcome = (TextView) findViewById(R.id.tv_welcome);
//        changeWelcome();
        setListeners();
    }

//    private void changeWelcome(){
//        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
//        String name = preferences.getString("user_name", "");
//        mtv_welcome.setText("Hi!" + name);
//    }

    private void setListeners(){
        OnClickUserA onClickUserA = new OnClickUserA();
        btnChampion.setOnClickListener(onClickUserA);
        OnClickUserB onClickUserB = new OnClickUserB();
        btnChallenge.setOnClickListener(onClickUserB);
        OnClickGetPlan onClickGetPlan = new OnClickGetPlan();
        btnTask.setOnClickListener(onClickGetPlan);
        OnClickStatistics onClickStatistics = new OnClickStatistics();
        btnStatistic.setOnClickListener(onClickStatistics);
        OnClickDiscuss onClickDiscuss = new OnClickDiscuss();
        btnTalk.setOnClickListener(onClickDiscuss);
        OnClickTestself onClickTestself = new OnClickTestself();
        btnTestself.setOnClickListener(onClickTestself);
        OnClickPhoto onClickPhoto = new OnClickPhoto();
        photoUser.setOnClickListener(onClickPhoto);
//        mtv_welcome.setOnClickListener(onClick);
    }


    private class OnClickUserA implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, UserAActivity.class);
            startActivity(intent);
        }
    }

    private class OnClickUserB implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, UserBActivity.class);
            startActivityForResult(intent, SCAN_REQUEST_CODE);
        }
    }

    private class OnClickGetPlan implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, GetPlanActivity.class);
            startActivity(intent);
        }
    }

    private class OnClickStatistics implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
            startActivity(intent);
        }
    }

    private class OnClickDiscuss implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, DiscussActivity.class);
            startActivity(intent);
        }
    }
    private class OnClickTestself implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, CreditActivity.class);
            startActivity(intent);
        }
    }
    private class OnClickPhoto implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Homepage.class);
            startActivity(intent);
        }
    }
}
