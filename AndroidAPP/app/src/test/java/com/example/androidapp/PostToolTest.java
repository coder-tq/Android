package com.example.androidapp;

import junit.framework.TestCase;

public class PostToolTest extends TestCase {

    public void testSendPost() {
        try {
            System.out.println(PostTool.sendPost("http://101.201.100.218/android/checkUser","phone=110&pass=123"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}