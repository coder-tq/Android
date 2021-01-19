package com.example.androidapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.bumptech.glide.Glide;

public class StatisticsActivity extends AppCompatActivity {

    private ImageView ivStatistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ivStatistics = (ImageView) findViewById(R.id.iv_statistics);

        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String user_id = preferences.getString("user_id", "1");

        Glide.with(StatisticsActivity.this).load("http://101.201.100.218/android/chart/get?user_id=" + user_id).into(ivStatistics);
    }

}