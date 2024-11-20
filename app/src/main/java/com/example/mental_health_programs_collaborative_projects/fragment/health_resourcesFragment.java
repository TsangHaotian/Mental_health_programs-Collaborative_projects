package com.example.mental_health_programs_collaborative_projects.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mental_health_programs_collaborative_projects.R;
import com.example.mental_health_programs_collaborative_projects.health_resource_fragment.TabNewsFragment;
import com.example.mental_health_programs_collaborative_projects.health_resource_fragment.Titleinfo;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class health_resourcesFragment extends Fragment {
    //    private String[] titles = {"娱乐", "军事", "教育", "文化", "将康", "财经", "体育", "汽车", "科技"};
    private List<Titleinfo> titles=new ArrayList<>();
    private TabLayout tab_layout;
    private ViewPager2 viewPager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health_resources, container, false);

    }

    //        初始化控件
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//            初始化title数据
        titles.add(new Titleinfo("推荐","top"));
        titles.add(new Titleinfo("国内","guonei"));
        titles.add(new Titleinfo("国际","guoji"));
        titles.add(new Titleinfo("娱乐","yule"));
        titles.add(new Titleinfo("体育","tiyu"));
        titles.add(new Titleinfo("军事","junshi"));
        titles.add(new Titleinfo("科技","keji"));
        titles.add(new Titleinfo("财经","caijing"));
        titles.add(new Titleinfo("游戏","youxi"));
        titles.add(new Titleinfo("汽车","qiche"));
        titles.add(new Titleinfo("健康","jiankang"));

        // 初始化控件
        tab_layout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.viewPager);


//            viewPager 需要设置一个adapter

        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                String title = titles.get(position).getPy_title();
                TabNewsFragment tabNewsFragment = TabNewsFragment.newInstance(title);


                return tabNewsFragment;
            }

            @Override
            public int getItemCount() {
                return titles.size();
            }
        });

//            tab_layout点击事件
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

//                    设置viewPageer选中当前页
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


//            tab_layout和viewPager关联在一起
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tab_layout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position).getTitle());
            }
        });
        tabLayoutMediator.attach();
    }














}