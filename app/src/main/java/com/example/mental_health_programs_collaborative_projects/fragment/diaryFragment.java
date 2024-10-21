package com.example.mental_health_programs_collaborative_projects.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mental_health_programs_collaborative_projects.R;
import com.example.mental_health_programs_collaborative_projects.diary_edit; // 确保导入正确的类
import com.example.mental_health_programs_collaborative_projects.diary_list;

public class diaryFragment extends Fragment {

    private Button createButton;
    private Button browseButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary, container, false);

        createButton = view.findViewById(R.id.create_button);
        browseButton = view.findViewById(R.id.browse_button);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建Intent对象，指向diary_edit活动
                Intent intent = new Intent(getActivity(), diary_edit.class);
                // 启动diary_edit活动
                startActivity(intent);
            }
        });

        browseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), diary_list.class);
                startActivity(intent);
            }
        });



        return view;

    }
}