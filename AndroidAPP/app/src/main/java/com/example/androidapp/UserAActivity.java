package com.example.androidapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class UserAActivity extends AppCompatActivity {

    private ImageView ivChallenge;
    Handler handler = new Handler();
    String roomId = null;
    String roomInfo = null;
    private Bitmap qrCodeBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_a);

        ivChallenge = (ImageView) findViewById(R.id.iv_challenge);

        new Thread(createRoomTask).start();

        new Thread(waitB).start();
    }

    Runnable createRoomTask = new Runnable() {
        @Override
        public void run() {
            createRoom();
            roomInfo = roomId + " 正在等待玩家B";
            handler.post(new Runnable() {  //主线程才能进行UI操作
                @Override
                public void run() {
                    Bitmap portrait = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
                    //两个方法，一个不传大小，使用默认
                    qrCodeBitmap = CreateQRBitmp.createQRCodeBitmap(roomInfo, portrait);
                    ivChallenge.setImageBitmap(qrCodeBitmap);
                }
            });
        }
    };

    private void createRoom(){
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String user_id = preferences.getString("user_id", "1");
        try {
            String string = PostTool.sendPost("http://101.201.100.218/android/createRoom", "user_id=" + user_id);
            roomId = praseRoomId(string);
            System.out.println(roomId.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String praseRoomId(String string){
        JSONObject jsonObject = JSON.parseObject(string);
        return jsonObject.getObject("room_id", String.class);
    }

    Runnable waitB = new Runnable() {
        @Override
        public void run() {
            try {
                int state = 0;
                while(roomId == null){
                    Thread.sleep(100);
                }

                while(state == 0){
                    String string = PostTool.sendPost("http://101.201.100.218/android/queryRoom", "room_id=" + roomId);
                    state = praseState(string);
//                    Thread.sleep(10);
                    System.out.println("wait");
                }
                saveRoomId();
                System.out.println(roomId);
                System.out.println("success");
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {  //主线程才能进行UI操作
                @Override
                public void run() {
                    Intent intent = new Intent(UserAActivity.this, PkActivity.class);
                    startActivity(intent);
                }
            });
        }
    };

    private int praseState(String string){
        JSONObject jsonObject = JSON.parseObject(string);
        return jsonObject.getObject("state", Integer.class);
    }

    private void saveRoomId(){
        SharedPreferences sharedPreferences = getSharedPreferences("room", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("room_id", roomId);
        editor.commit();//提交修改
    }
}