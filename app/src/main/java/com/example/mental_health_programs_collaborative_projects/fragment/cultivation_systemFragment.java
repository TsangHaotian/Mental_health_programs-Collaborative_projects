package com.example.mental_health_programs_collaborative_projects.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mental_health_programs_collaborative_projects.R;

public class cultivation_systemFragment extends Fragment {

    private int growthPercentage = 0;  // 初始成长为0%
    private ImageView flowerImage;
    private TextView growthStatus;
    private Button waterButton;
    private Button fertilizeButton;

    public cultivation_systemFragment() {
        // 默认构造函数
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 使用 Fragment 的视图来加载布局文件
        View view = inflater.inflate(R.layout.fragment_cultivation_system, container, false);

        // 初始化视图控件
        flowerImage = view.findViewById(R.id.flowerImage);
        growthStatus = view.findViewById(R.id.growthStatus);
        waterButton = view.findViewById(R.id.waterButton);
        fertilizeButton = view.findViewById(R.id.fertilizeButton);

        // 设置浇水按钮点击事件
        waterButton.setOnClickListener(v -> increaseGrowth());

        // 设置施肥按钮点击事件
        fertilizeButton.setOnClickListener(v -> increaseGrowth());

        // 初始化显示花的成长状态
        updateFlowerImage();

        return view;
    }

    private void increaseGrowth() {
        if (growthPercentage + 5 <= 100) {
            growthPercentage += 5; // 每次增加5%
        } else {
            growthPercentage = 100; // 达到最大100%
        }
        updateFlowerImage(); // 更新花的图片和成长状态
    }

    private void updateFlowerImage() {
        growthStatus.setText("成长状态: " + growthPercentage + "%");

        int imageResource = R.drawable.flower_img1; // 初始图片
        if (growthPercentage >= 20) imageResource = R.drawable.flower_img2;
        if (growthPercentage >= 40) imageResource = R.drawable.flower_img3;
        if (growthPercentage >= 60) imageResource = R.drawable.flower_img4;
        if (growthPercentage >= 80) imageResource = R.drawable.flower_img5;

        flowerImage.setImageResource(imageResource); // 更新图片
    }
}
