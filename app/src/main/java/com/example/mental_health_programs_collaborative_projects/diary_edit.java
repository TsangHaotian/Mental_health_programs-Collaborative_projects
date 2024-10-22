package com.example.mental_health_programs_collaborative_projects;

// 导入所需的Android类库
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

// 声明日记编辑活动（Activity）
public class diary_edit extends AppCompatActivity {

    // 定义用于输入标题和内容的EditText控件
    private EditText titleEditText;
    private EditText contentEditText;

    // 请求存储权限的请求码
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    // 需要请求的存储权限数组
    private static final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    // onCreate方法：当活动被创建时调用
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 启用状态栏透明效果
        EdgeToEdge.enable(this);
        // 设置活动的布局文件
        setContentView(R.layout.activity_diary_edit);

        // 初始化EditText控件
        titleEditText = findViewById(R.id.title_editText);
        contentEditText = findViewById(R.id.content_editText);

        // 为保存按钮设置点击事件监听器，点击时调用saveDiary方法
        findViewById(R.id.save_button).setOnClickListener(view -> saveDiary());

        // 如果没有存储权限，则请求存储权限
        if (!hasStoragePermission()) {
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    // 检查是否具有存储权限的方法
    private boolean hasStoragePermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    // onRequestPermissionsResult方法：处理权限请求结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 如果请求码是REQUEST_EXTERNAL_STORAGE，处理存储权限请求结果
        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            // 如果权限请求被授权
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "存储权限已授予", Toast.LENGTH_SHORT).show();
            } else {
                // 如果权限请求被拒绝
                Toast.makeText(this, "存储权限被拒绝，无法保存日记", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 保存日记的方法
    private void saveDiary() {
        // 获取标题和内容文本
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();

        // 如果标题或内容为空，则显示提示信息并返回
        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "标题和内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // 使用SimpleDateFormat生成时间戳作为文件名的一部分
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        // 构造文件名
        String fileName = "diary_" + timeStamp + ".txt";

        // 创建File对象，用于表示将要创建的文件
        File file;
        try {
            // 如果有存储权限，则使用外部存储，否则使用内部存储
            if (hasStoragePermission()) {
                file = new File(getExternalFilesDir(null), fileName);
            } else {
                // 如果没有存储权限，使用内部存储作为备选方案
                file = new File(getFilesDir(), fileName);
            }
            // 使用BufferedWriter创建并写入文件
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(title + "\n" + content);
            writer.close();

            // 显示保存成功的提示信息
            Toast.makeText(this, "日记保存成功", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // 如果发生IO异常，显示错误信息
            Toast.makeText(this, "保存失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}