package com.example.androidapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class TaskActivity extends AppCompatActivity {

    private Button btnTestself;
    private Button btnTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        btnTestself = findViewById(R.id.task_btn_testself);
        btnTask = findViewById(R.id.task_btn_task);

        OnClick onClick = new OnClick();
        btnTestself.setOnClickListener(onClick);
        btnTask.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(TaskActivity.this, GetPlanActivity.class);
            startActivity(intent);
        }
    }
}