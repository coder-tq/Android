package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class hg_profile extends AppCompatActivity {

    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hg_profile);
        btn_back = findViewById(R.id.hg_profile_btn_back);
        setClickListener();
    }

    private void setClickListener(){
        Onclick onclick = new Onclick();
        btn_back.setOnClickListener(onclick);
    }

    private class Onclick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.hg_profile_btn_back:
                    intent = new Intent(hg_profile.this, Homepage.class);
                    break;
            }
            startActivity(intent);
        }
    }
}