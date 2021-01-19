package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Homepage extends AppCompatActivity {

    private Button btn_back, btn_profile, btn_subscribe, btn_help, btn_about;
    int state_subs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        btn_back = findViewById(R.id.hg_btn_back);
        btn_profile = findViewById(R.id.hg_btn_profile);
        btn_subscribe = findViewById(R.id.hg_btn_subscribe);
        btn_help = findViewById(R.id.hg_btn_help);
        btn_about = findViewById(R.id.hg_btn_about);
        state_subs = 0;
        setLinsences();
    }

    private void setLinsences() {
        Onclick onclick = new Onclick();
        btn_back.setOnClickListener(onclick);
        btn_profile.setOnClickListener(onclick);
        btn_subscribe.setOnClickListener(onclick);
        btn_help.setOnClickListener(onclick);
        btn_about.setOnClickListener(onclick);
    }

    private class Onclick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.hg_btn_back:
                    intent = new Intent(Homepage.this, MainActivity.class);
                    break;
                case R.id.hg_btn_profile:
                    intent = new Intent(Homepage.this, hg_profile.class);
                    break;
                case R.id.hg_btn_subscribe:
                    if(state_subs == 0){
                        //未订阅 → 已订阅
                        btn_subscribe.setText("已订阅 √");
                        state_subs = 1;
                    }
                    else{
                        //已订阅 → 未订阅
                        btn_subscribe.setText("订 阅");
                        state_subs = 0;
                    }
                    break;
                case R.id.hg_btn_help:
                    Toast.makeText(Homepage.this, "请联系开发人员", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.hg_btn_about:
                    Toast.makeText(Homepage.this, "请联系开发人员", Toast.LENGTH_SHORT).show();
                    break;
            }
            if(intent != null)
                startActivity(intent);
        }
    }
}