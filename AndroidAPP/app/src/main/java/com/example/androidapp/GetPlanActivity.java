package com.example.androidapp;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.androidapp.CreateQRBitmp;
import com.example.androidapp.dialog.ImageOptDialog;
import com.example.androidapp.pojo.Plan;
import com.example.androidapp.pojo.User;
import com.example.androidapp.utils.BitmapUtil;
import com.example.androidapp.utils.ImageUtil;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.sql.Date;
import java.util.HashMap;

public class GetPlanActivity extends AppCompatActivity {

    private int SCAN_REQUEST_CODE=200;
    private int SELECT_IMAGE_REQUEST_CODE=201;
    protected final int PERMS_REQUEST_CODE = 202;

    private Handler handler = new Handler();
    private Plan plan = new Plan();
    private String planInfo = "";

    private Bitmap qrCodeBitmap; // 识别
    private ImageView ivQrImage; // 生成
    private Button mBtnLongPress;
    private EditText etSubmitContent;
    String plan_id = "1";

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_plan);

        ivQrImage = (ImageView) findViewById(R.id.iv_qr_image);
        mBtnLongPress = (Button) findViewById(R.id.btn_long_press);
        etSubmitContent = (EditText) findViewById(R.id.ETSubmitContent);
        new Thread(getPlanTask).start();
        setListeners();
    }

    private void setListeners(){
        GetPlanActivity.OnLongClick onLongClick = new OnLongClick();
        ivQrImage.setOnLongClickListener(onLongClick);
        GetPlanActivity.OnClick onClick = new OnClick();
        mBtnLongPress.setOnClickListener(onClick);
    }

    private class OnLongClick implements View.OnLongClickListener{
        @Override
        public boolean onLongClick(View v) {
            longPress();
            return false;
        }
    }

    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            longPress();
        }
    }

    Runnable getPlanTask = new Runnable() {
        @Override
        public void run() {
            getPlan();
            planInfo = plan.getName() + "   " + plan.getContent();
            handler.post(new Runnable() {  //主线程才能进行UI操作
                @Override
                public void run() {
                    Bitmap portrait = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
                    //两个方法，一个不传大小，使用默认
                    qrCodeBitmap = CreateQRBitmp.createQRCodeBitmap(planInfo, portrait);
                    ivQrImage.setImageBitmap(qrCodeBitmap);
                }
            });
        }
    };

    private void getPlan(){
        try {
            String string = PostTool.sendPost("http://101.201.100.218/android/queryPlanByPlanId", "id=" + plan_id);
            plan = prasePlan(string);
            System.out.println(plan.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Plan prasePlan(String string){
        JSONObject jsonObject = JSON.parseObject(string);
        return jsonObject.toJavaObject(Plan.class);
    }

    private void longPress(){
        ImageOptDialog imageOptDialog=new ImageOptDialog(this);
        imageOptDialog.setCallback(new ImageOptDialog.ImageOptCallback() {
            //识别二维码
            @Override
            public void onIdentifyQrClick() {
                View view = getWindow().getDecorView().getRootView();//找到当前页面的根布局
                view.setDrawingCacheEnabled(true);//禁用绘图缓存
                view.buildDrawingCache();

                Bitmap temBitmap = view.getDrawingCache();
                String result= BitmapUtil.parseQRcode(temBitmap);
                showToast("扫描结果:" + result);

                //禁用DrawingCahce否则会影响性能 ,而且不禁止会导致每次截图到保存的是缓存的位图
                view.setDrawingCacheEnabled(false);//识别完成之后开启绘图缓存

                new Thread(submitContentTask).start();
            }

            //保存图片到本地
            @Override
            public void onSaveImageClick() {
                View view = getWindow().getDecorView().getRootView();//找到当前页面的根布局
                view.setDrawingCacheEnabled(true);//禁用绘图缓存
                view.buildDrawingCache();

                Bitmap temBitmap = view.getDrawingCache();
                ImageUtil.savePicToLocal(temBitmap,GetPlanActivity.this);

                //禁用DrawingCahce否则会影响性能 ,而且不禁止会导致每次截图到保存的是缓存的位图
                view.setDrawingCacheEnabled(false);//识别完成之后开启绘图缓存

                showToast("保存图片到本地成功");
            }
        });
        imageOptDialog.show();
    }

    private void showToast(String str){
        Toast.makeText(GetPlanActivity.this,str,Toast.LENGTH_LONG).show();
    }

    Runnable submitContentTask = new Runnable() {
        @Override
        public void run() {
            SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
            String user_id = preferences.getString("user_id", "1");
            String submit_content = etSubmitContent.getText().toString();
            submitContent(user_id, submit_content);
        }
    };

    private void submitContent(String user_id, String submit_content){
        try {
            PostTool.sendPost("http://101.201.100.218//android/submitPlan",
                    "user_id=" + user_id + "&plan_id=" + plan_id + "&submit_content=" + submit_content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}