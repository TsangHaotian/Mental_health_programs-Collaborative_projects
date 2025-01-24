package com.example.mental_health_programs_collaborative_projects;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 启用EdgeToEdge布局，使内容可以延伸到屏幕边缘
        EdgeToEdge.enable(this);
        // 设置当前Activity的布局文件
        setContentView(R.layout.activity_setting);
        // 设置窗口内边距监听器，以适应系统栏（如状态栏和导航栏）
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // 初始化退出登录按钮，并设置其点击事件监听器
        Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 结束ActivityCollector中管理的所有Activity
                ActivityCollector.finishAll();
                // 杀死当前进程，确保应用程序完全退出
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }
}