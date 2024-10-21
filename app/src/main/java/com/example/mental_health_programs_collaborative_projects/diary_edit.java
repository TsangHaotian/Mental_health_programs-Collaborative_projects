package com.example.mental_health_programs_collaborative_projects;

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

public class diary_edit extends AppCompatActivity {

    private EditText titleEditText;
    private EditText contentEditText;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);//状态栏透明
        setContentView(R.layout.activity_diary_edit);


        titleEditText = findViewById(R.id.title_editText);
        contentEditText = findViewById(R.id.content_editText);


        findViewById(R.id.save_button).setOnClickListener(view -> saveDiary());

        if (!hasStoragePermission()) {
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    private boolean hasStoragePermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "存储权限已授予", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "存储权限被拒绝，无法保存日记", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveDiary() {
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "标题和内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "diary_" + timeStamp + ".txt";

        File file;
        try {
            if (hasStoragePermission()) {
                file = new File(getExternalFilesDir(null), fileName);
            } else {
                // Fallback to internal storage if permission is not granted
                file = new File(getFilesDir(), fileName);
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(title + "\n" + content);
            writer.close();

            Toast.makeText(this, "日记保存成功", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "保存失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}