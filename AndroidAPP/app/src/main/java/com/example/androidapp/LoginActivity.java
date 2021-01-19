package com.example.androidapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.androidapp.pojo.User;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private Button mBtnLogin;
    private Button mBtnRegister;
    private EditText mEtAcnt;
    private EditText mEtPwd;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEtAcnt = (EditText)findViewById(R.id.EtAcnt);
        mEtPwd = (EditText)findViewById(R.id.EtPwd);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnRegister = (Button) findViewById(R.id.btn_Sign);
        setListeners();
    }

    private void setListeners(){
        LoginActivity.OnClickLogin onClickLogin = new LoginActivity.OnClickLogin();
        LoginActivity.OnClickRegister onClickRegister = new LoginActivity.OnClickRegister();
        mBtnLogin.setOnClickListener(onClickLogin);
        mBtnRegister.setOnClickListener(onClickRegister);
    }

    private class OnClickLogin implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            new Thread(checkUserTask).start();
        }
    }

    Runnable checkUserTask = new Runnable() {
        @Override
        public void run() {
            Login();
            handler.post(new Runnable() {  //主线程才能进行UI操作
                @Override
                public void run() {
                    SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                    String msg = preferences.getString("user_msg", "");
                    int state = preferences.getInt("user_state", 0);
                    if(state == 0){
                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                        mEtPwd.setText("");
                    }
                    else{
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    };

    private void Login(){
        User user = new User();
        user.setPhone(mEtAcnt.getText().toString());
        user.setPass(mEtPwd.getText().toString());
        try {
            String string = PostTool.sendPost("http://101.201.100.218/android/checkUser",
                    "phone=" + user.getPhone() + "&pass=" + user.getPass());
            int state = praseState(string);
            saveState(state);
            if(state == 0){
                String msg = praseMsg(string);
                saveMsg(msg);
            }
            else{
                user = praseUser(string);
                saveUser(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 从Json中解析出user
    private User praseUser(String string){
        JSONObject jsonObject = JSON.parseObject(string);
        return jsonObject.getObject("user", User.class);
    }

    // 从Json中解析出state
    private int praseState(String string){
        JSONObject jsonObject = JSON.parseObject(string);
        return jsonObject.getObject("state", Integer.class);
    }

    // 从Json中解析出msg
    private String praseMsg(String string){
        JSONObject jsonObject = JSON.parseObject(string);
        return jsonObject.getObject("msg", String.class);
    }

    // 保存user信息至xml中，可在应用其他地方读取
    private void saveUser(User user){
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("user_phone", user.getPhone());
        editor.putString("user_pwd", user.getPass());
        editor.putString("user_name", user.getName());
        editor.putString("user_email", user.getPhone());
        editor.putString("user_id", Objects.requireNonNull(user.getId()).toString());
        editor.commit();//提交修改
    }

    private void saveMsg(String msg){
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("user_msg", msg);
        editor.commit();//提交修改
    }

    private void saveState(Integer state){
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putInt("user_state", state);
        editor.commit();//提交修改
    }

    private class OnClickRegister implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}