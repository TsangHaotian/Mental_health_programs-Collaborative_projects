package com.example.mental_health_programs_collaborative_projects.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.mental_health_programs_collaborative_projects.R;
import com.example.mental_health_programs_collaborative_projects.ChatActivity;

public class online_consultationFragment extends Fragment {
    private Button btn_startnewchat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_online_consultation, container, false);
        init(view);

        // 跳转建立新聊天
        btn_startnewchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    /**
     * 进行初始化操作
     */
    private void init(View view) {
        // 与xml的控件绑定
        btn_startnewchat = view.findViewById(R.id.btn_startnewchat);
    }
}