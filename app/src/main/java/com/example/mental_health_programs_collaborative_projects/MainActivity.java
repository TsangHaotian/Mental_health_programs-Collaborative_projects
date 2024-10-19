package com.example.mental_health_programs_collaborative_projects;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mental_health_programs_collaborative_projects.fragment.cultivation_systemFragment;
import com.example.mental_health_programs_collaborative_projects.fragment.diaryFragment;
import com.example.mental_health_programs_collaborative_projects.fragment.health_resourcesFragment;
import com.example.mental_health_programs_collaborative_projects.fragment.online_consultationFragment;
import com.example.mental_health_programs_collaborative_projects.fragment.self_assessmentFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private self_assessmentFragment mself_assessmentFragment;
    private health_resourcesFragment mhealth_resourcesFragment;
    private diaryFragment mdiary_fragment;
    private online_consultationFragment monline_consultationFragment;
    private cultivation_systemFragment mclutivation_systemFragment;

    private NavigationView nav_view;

    //底部导航栏
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //初始化控件
        BottomNavigationView mbottomNavigationView = findViewById(R.id.bt_bottomnavigation);
        nav_view = findViewById(R.id.nav_view);

        //设置点击事件，点击底部导航栏
        mbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            //底部导航栏跳转函数
            //实现跳转，当位置为0则为界面1，依次类推
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.self_assessment) {
                    selectedFragment(0);

                } else if (item.getItemId() == R.id.health_resources) {
                    selectedFragment(1);

                } else if (item.getItemId() == R.id.diary) {
                    selectedFragment(2);

                } else if (item.getItemId() == R.id.online_consultation) {
                    selectedFragment(3);

                } else {
                    selectedFragment(4);

                }

                return true;
            }
        });
        //默认首页选中
        selectedFragment(0);


        //点击事件，点击侧边栏
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.nav_side_one) {
                    //跳转到个人主页
                    Intent intent = new Intent(MainActivity.this, Personal_homepageActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.nav_side_two) {
                    //跳转到使用帮助
                    Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.nav_side_three) {
                    //跳转到使用帮助
                    Intent intent = new Intent(MainActivity.this, Contact_usActivity.class);
                    startActivity(intent);
                } else {
                    //跳转到使用帮助
                    Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });

    }

    //当点击第一个界面，则隐藏其他界面，并且把选择界面添加到content的位置，实现跳转
    private void selectedFragment(int position) {
        androidx.fragment.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        hideFragment(fragmentTransaction);
        if (position == 0) {
            if (mself_assessmentFragment == null) {
                mself_assessmentFragment = new self_assessmentFragment();
                fragmentTransaction.add(R.id.content, mself_assessmentFragment);
            } else {
                fragmentTransaction.show(mself_assessmentFragment);
            }
        } else if (position == 1) {
            if (mhealth_resourcesFragment == null) {
                mhealth_resourcesFragment = new health_resourcesFragment();
                fragmentTransaction.add(R.id.content, mhealth_resourcesFragment);
            } else {
                fragmentTransaction.show(mhealth_resourcesFragment);
            }
        } else if (position == 2) {
            if (mdiary_fragment == null) {
                mdiary_fragment = new diaryFragment();
                fragmentTransaction.add(R.id.content, mdiary_fragment);
            } else {
                fragmentTransaction.show(mdiary_fragment);
            }
        } else if (position == 3) {
            if (monline_consultationFragment == null) {
                monline_consultationFragment = new online_consultationFragment();
                fragmentTransaction.add(R.id.content, monline_consultationFragment);
            } else {
                fragmentTransaction.show(monline_consultationFragment);
            }
        } else {
            if (mclutivation_systemFragment == null) {
                mclutivation_systemFragment = new cultivation_systemFragment();
                fragmentTransaction.add(R.id.content, mclutivation_systemFragment);
            } else {
                fragmentTransaction.show(mclutivation_systemFragment);
            }
        }
        //一定要提交
        fragmentTransaction.commit();
    }

    //实现当点击当前页面，隐藏其他页面
    private void hideFragment(androidx.fragment.app.FragmentTransaction fragmentTransaction) {
        if (mself_assessmentFragment != null) {
            fragmentTransaction.hide(mself_assessmentFragment);
        }

        if (mhealth_resourcesFragment != null) {
            fragmentTransaction.hide(mhealth_resourcesFragment);
        }

        if (mdiary_fragment != null) {
            fragmentTransaction.hide(mdiary_fragment);
        }
        if (monline_consultationFragment != null) {
            fragmentTransaction.hide(monline_consultationFragment);
        }
        if (mclutivation_systemFragment != null) {
            fragmentTransaction.hide(mclutivation_systemFragment);
        }
    }
    //********************************************************************************************************//


}
