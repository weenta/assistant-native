package com.weenta.assistant.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.weenta.assistant.R;
import com.weenta.assistant.fragment.AssistantFragment;
import com.weenta.assistant.fragment.JokeFragment;
import com.weenta.assistant.fragment.NewsFragment;
import com.weenta.assistant.ui.BaseActivity;
import com.weenta.assistant.utils.L;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<Fragment> viewPagerList;
    private List<String> tabbarList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        initEvent();
    }

    // 初始化数据
    private void initData() {
        viewPagerList = new ArrayList<>();
        viewPagerList.add(new NewsFragment());
        viewPagerList.add(new JokeFragment());
        viewPagerList.add(new AssistantFragment());

        tabbarList = new ArrayList<>();
        tabbarList.add("今日要闻");
        tabbarList.add("轻松一刻");
        tabbarList.add("生活助手");

    }

    private void initEvent() {

    }


    private void initView() {
        mViewPager = findViewById(R.id.mViewPager);
        mTabLayout = findViewById(R.id.mTabLayout);

        // viewPager 设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return viewPagerList.get(position);
            }

            @Override
            public int getCount() {
                return viewPagerList.size();
            }

            // 设置标题
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabbarList.get(position);
            }
        });

        // TabLayout 绑定ViewPager
        mTabLayout.setupWithViewPager(mViewPager);


    }

}
