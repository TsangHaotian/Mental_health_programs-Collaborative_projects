package com.example.mental_health_programs_collaborative_projects;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mental_health_programs_collaborative_projects.adapter.Newsinfo;

public class NewsDetailsActivity extends AppCompatActivity {
    private Newsinfo.ResultDTO.DataDTO dataDTO;
    private TextView title;
    private Toolbar toolbar;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news_details);

//        初始化控件
        title = findViewById(R.id.title);
        mWebView = findViewById(R.id.webView);
        toolbar = findViewById(R.id.toolbar);


//      获取传递的数据
        dataDTO = (Newsinfo.ResultDTO.DataDTO) getIntent().getSerializableExtra("dataDTO");

//        设置数据
        if (null!=dataDTO){
            title.setText(dataDTO.getTitle());
            mWebView.loadUrl(dataDTO.getUrl());
        }
//        返回
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}