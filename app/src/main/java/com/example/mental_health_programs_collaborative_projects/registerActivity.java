package com.example.mental_health_programs_collaborative_projects;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class registerActivity extends AppCompatActivity {

    private Button reg;
    private EditText musername;
    private EditText mpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg = findViewById(R.id.btn_register);
        musername = findViewById(R.id.uer_name);
        mpassword = findViewById(R.id.uer_password);



                // 检查用户名和密码是否为空
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = musername.getText().toString();
                String password = mpassword.getText().toString();
                Intent A = null;
                String success = "注册成功";
                String empty = "用户名或密码不能为空，请重新输入";

                // 检查用户名和密码是否为空
                if (username.isEmpty() || password.isEmpty()) {
                    // 如果有空的字段，显示提示信息
                    toastuti.showmsg(getApplicationContext(), empty);
                } else {
                    // 正确 跳转页面并且弹出注册成功 toast
                    A = new Intent(registerActivity.this, loginActivity.class);
                    startActivity(A);
                    toastuti.showmsg(getApplicationContext(), success);
                }
            }
        });



    }


}



