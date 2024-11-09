package com.example.mental_health_programs_collaborative_projects.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mental_health_programs_collaborative_projects.R;
import com.example.mental_health_programs_collaborative_projects.health_resource_fragment.TabNewsFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class health_resourcesFragment extends Fragment {
    private String[] titles = {"儿童", "学生", "成人", "老人"};
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

            // 初始化控件
            tab_layout = view.findViewById(R.id.tab_layout);
            viewPager = view.findViewById(R.id.viewPager);


//            viewPager 需要设置一个adapter

            viewPager.setAdapter(new FragmentStateAdapter(this) {
                @NonNull
                @Override
                public Fragment createFragment(int position) {
                    String title = titles[position];
                    TabNewsFragment tabNewsFragment = TabNewsFragment.newInstance(title);


                    return tabNewsFragment;
                }

                @Override
                public int getItemCount() {
                    return titles.length;
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
                    tab.setText(titles[position]);
                }
            });
            tabLayoutMediator.attach();
        }


}