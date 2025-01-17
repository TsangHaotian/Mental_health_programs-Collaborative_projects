package com.example.mental_health_programs_collaborative_projects.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mental_health_programs_collaborative_projects.R;

public class cultivation_systemFragment extends Fragment {

    private int flowerWaterLevel = 0; // 水分等级 0-100
    private int flowerGrowth = 0; // 花的生长情况，百分比
    private TextView flowerStatusText;
    private TextView flowerGrowthText;

    public cultivation_systemFragment() {
        // 设置布局
        super(R.layout.fragment_cultivation_system);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cultivation_system, container, false);

        // 获取UI控件
        flowerStatusText = view.findViewById(R.id.flowerStatusText);
        flowerGrowthText = view.findViewById(R.id.flowerGrowthText);
        Button waterButton = view.findViewById(R.id.waterButton);

        // 设置浇水按钮的点击事件
        waterButton.setOnClickListener(v -> waterFlower());

        return view;
    }

    // 浇水方法，增加水分，更新生长情况
    private void waterFlower() {
        if (flowerWaterLevel < 100) {
            flowerWaterLevel += 10; // 每次浇水增加10%的水分
            flowerGrowth += 5; // 每次浇水，生长5%
        }

        // 限制最大生长百分比和水分
        if (flowerGrowth > 100) {
            flowerGrowth = 100;
        }
        if (flowerWaterLevel > 100) {
            flowerWaterLevel = 100;
        }

        // 更新UI
        flowerStatusText.setText("水分：" + flowerWaterLevel + "%");
        flowerGrowthText.setText("花的生长：" + flowerGrowth + "%");

        // 判断是否完成生长
        if (flowerGrowth == 100) {
            flowerStatusText.setText("花已完全生长！");
        }
    }
}
