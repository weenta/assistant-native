package com.weenta.assistant.ui;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.weenta.assistant.R;
import com.weenta.assistant.fragment.AssistantFragment;
import com.weenta.assistant.fragment.JokeFragment;
import com.weenta.assistant.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<Fragment> viewPagerList;
    private List<String> tabbarList;
    private int[] tabIcons = {
            R.drawable.tab_icon_news,
            R.drawable.tab_icon_joke,
            R.drawable.tab_icon_assistant,
    };

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
        // 自定义tab view
        View tab0 = getTabView(0);
        View tab1 = getTabView(1);
        View tab2 = getTabView(2);
        mTabLayout.getTabAt(0).setCustomView(tab0);
        mTabLayout.getTabAt(1).setCustomView(tab1);
        mTabLayout.getTabAt(2).setCustomView(tab2);
    }

    // 自定义tabView
    private View getTabView(int position) {
        String s = tabbarList.get(position);
        LayoutInflater inflater = LayoutInflater.from(this);
        View tabView = inflater.inflate(R.layout.tab_item, null);
        TextView title = tabView.findViewById(R.id.tab_text);
        ImageView icon = tabView.findViewById(R.id.tab_icon);

        title.setText(s);
        icon.setImageResource(tabIcons[position]);
        return tabView;
    }



}
