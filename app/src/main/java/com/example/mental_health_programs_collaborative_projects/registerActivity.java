package com.example.mental_health_programs_collaborative_projects;

import static android.graphics.drawable.GradientDrawable.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class registerActivity extends AppCompatActivity {

    private Button reg;
    private EditText musername;
    private EditText mpassword;

    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private ImageView togglePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        setFunction();
//找到控件
        reg = findViewById(R.id.btn_register);
        musername = findViewById(R.id.uer_name);
        mpassword = findViewById(R.id.uer_password);
        togglePassword = findViewById(R.id.view_password);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = musername.getText().toString();
                String password = mpassword.getText().toString();
                    // 先定义一个变量来标记是否有错误
                    boolean hasError = false;
                    if (username.isEmpty()) {
                        musername.setError("用户名不能为空");
                        hasError = true;
                    }

                    if (password.isEmpty()) {
                        mpassword.setError("密码不能为空");
                        hasError = true;
                    }
                    if (hasError) {
                        // 如果有错误，就不执行登录操作
                        return;
                    }
                    // 注册成功
                    Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                    // 保存用户名和密码到SharedPreferences
                    saveCredentials(username, password);
                    // 跳转到登录页面
                    Intent intent = new Intent(registerActivity.this, loginActivity.class);
                    startActivity(intent);
                    finish();
                }
        });

        // 密码可见性
        togglePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((mpassword.getInputType() & InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    // 前提检测如果密码可见，就强制转化为不可见的密码
                    mpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    togglePassword.setImageResource(R.drawable.baseline_key_off_24);
                } else {
                    // 如果当前是隐藏状态，切换为可见状态
                    mpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    togglePassword.setImageResource(R.drawable.baseline_key_24);
                }
            }
        });

    }

    private void setFunction() {
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // 结束当前的页面，也就是相当于跳回到之前的页面
            }
        });
    }

    private void saveCredentials(String username, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME, username);
        editor.putString(PASSWORD, password);
        editor.apply();
    }




}