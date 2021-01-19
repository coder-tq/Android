package com.example.androidapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.androidapp.pojo.User;
import com.example.androidapp.pojo.Userplan;

public class RegisterActivity extends AppCompatActivity {

    private Button mBtnBackLogin;
    private Button mBtnSubmitRegister;
    private EditText mEtReAcnt;
    private EditText mEtRePwd;
    private EditText mEtReName;
    private EditText mEtReEmail;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mBtnBackLogin = (Button) findViewById(R.id.btn_backLogin);
        mBtnSubmitRegister = (Button) findViewById(R.id.btn_submitRegister);
        mEtReAcnt = (EditText) findViewById(R.id.EtReAcnt);
        mEtRePwd = (EditText) findViewById(R.id.EtRePwd);
        mEtReName = (EditText) findViewById(R.id.EtReName);
        mEtReEmail = (EditText) findViewById(R.id.EtReEmail);
        setListeners();
    }

    private void setListeners(){
        RegisterActivity.OnClickBackLogin onClickBackLogin = new RegisterActivity.OnClickBackLogin();
        mBtnBackLogin.setOnClickListener(onClickBackLogin);
        RegisterActivity.OnClickSubmitRegister onClickSubmitRegister = new RegisterActivity.OnClickSubmitRegister();
        mBtnSubmitRegister.setOnClickListener(onClickSubmitRegister);
    }

    private class OnClickBackLogin implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private class OnClickSubmitRegister implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            new Thread(RegisterTask).start();
        }
    }

    Runnable RegisterTask = new Runnable() {
        @Override
        public void run() {
            Register();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                    int state = preferences.getInt("user_Restate", 0);
                    if(state == 0){
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        mEtReAcnt.setText("");
                        mEtRePwd.setText("");
                        mEtReName.setText("");
                        mEtReEmail.setText("");
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "注册成功，返回登陆", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    };

    private void Register(){
        User user = new User();
        user.setPhone(mEtReAcnt.getText().toString());
        user.setPass(mEtRePwd.getText().toString());
        user.setName(mEtReName.getText().toString());
        user.setEmail(mEtReEmail.getText().toString());
        try {
            String string = PostTool.sendPost("http://101.201.100.218/android/insertUser",
                    "phone=" + user.getPhone() + "&pass=" + user.getPass() + "&name=" + user.getName() + "&email=" + user.getEmail());
            int state = praseState(string);
            saveState(state);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int praseState(String string){
        JSONObject jsonObject = JSON.parseObject(string);
        return jsonObject.getObject("state", Integer.class);
    }

    private void saveState(Integer state){
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putInt("user_Restate", state);
        editor.commit();//提交修改
    }
}