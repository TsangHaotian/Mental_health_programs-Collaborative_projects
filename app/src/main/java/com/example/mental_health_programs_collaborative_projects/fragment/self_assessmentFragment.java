package com.example.mental_health_programs_collaborative_projects.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.mental_health_programs_collaborative_projects.R;
import com.example.mental_health_programs_collaborative_projects.ShoppingActivity;

public class self_assessmentFragment extends Fragment {

    private DrawerLayout drawerLayout;
    private ImageButton profileIcon;
    private ImageButton shoppingIcon;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_self_assessment, container, false);

        // 获取用户头像的ImageButton
        profileIcon = view.findViewById(R.id.profile_icon);

        // 获取DrawerLayout
        drawerLayout = requireActivity().findViewById(R.id.drawer_layout);

        // 设置用户头像的点击事件监听器
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击用户头像时打开抽屉
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // 获取购物图标的ImageButton
        shoppingIcon = view.findViewById(R.id.shopping_icon);

        // 设置购物图标的点击事件监听器
        shoppingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "Shopping icon clicked");
                Intent A = new Intent(requireActivity(), ShoppingActivity.class);
                startActivity(A);
            }
        });

        return view;
    }
}