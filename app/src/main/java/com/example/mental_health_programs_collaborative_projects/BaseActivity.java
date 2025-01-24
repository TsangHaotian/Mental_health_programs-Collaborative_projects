package com.example.mental_health_programs_collaborative_projects;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {

    /**
     * 当Activity创建时调用。
     * 将当前Activity添加到ActivityCollector管理的集合中。
     *保存的实例状态
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 将当前Activity添加到ActivityCollector中进行管理
        ActivityCollector.addActivity(this);
    }

    /**
     * 当Activity销毁时调用。
     * 从ActivityCollector管理的集合中移除当前Activity。
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 从ActivityCollector中移除当前Activity
        ActivityCollector.removeActivity(this);
    }

    public void exitAppAndGoToLogin() {
        // 结束ActivityCollector中管理的所有Activity
        ActivityCollector.finishAll();
        // 创建一个Intent用于启动登录Activity
        Intent intent = new Intent(this, loginActivity.class);
        // 设置Intent的标志，确保登录Activity在新的任务栈中打开，并清除旧的任务栈
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        // 启动登录Activity
        startActivity(intent);
        // 杀死当前进程，确保应用程序完全退出
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
