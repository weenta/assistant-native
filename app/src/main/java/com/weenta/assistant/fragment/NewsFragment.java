package com.weenta.assistant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.weenta.assistant.R;
import com.weenta.assistant.adapter.NewsListAdapter;
import com.weenta.assistant.bean.NewsList;
import com.weenta.assistant.bean.NewsListItem;
import com.weenta.assistant.biz.NewsBiz;
import com.weenta.assistant.network.RetrofitClientInstance;
import com.weenta.assistant.ui.NewsDetailActivity;
import com.weenta.assistant.utils.Config;
import com.weenta.assistant.utils.L;
import com.weenta.assistant.utils.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 今日要闻
 */
public class NewsFragment extends Fragment implements View.OnClickListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private ProgressBar progressBar;
    private List<NewsListItem> list = new ArrayList<>();
    private int page = 1;
    private String appKey = Config.NEWS_APP_KEY;
    private String category = Config.NEWS_CATEGORY;
    private NewsListAdapter adapter;
    private boolean isMore = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,null);

        initView(view);
        initEvent();
        initData();
        return view;
    }

    private void initData() {
        fetchNewsList();
    }

    // 初始化视图
    private void initView(View v) {
        progressBar = v.findViewById(R.id.loading);
        progressBar.setVisibility(View.GONE);

        listView = v.findViewById(R.id.news_list);
        adapter = new NewsListAdapter(getActivity(),list);
        listView.setAdapter(adapter);

        swipeRefreshLayout = v.findViewById(R.id.swipeRefreshLayout);
    }

    // 初始化事件
    private void initEvent() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem + visibleItemCount >= totalItemCount && totalItemCount > 0) {
                    // 加载下一页
                    loadNextPage();
                }
            }
        });

        // 下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                list.removeAll(list);
                adapter.notifyDataSetChanged();
                isMore = true;
                fetchNewsList();
            }
        });

        // 点击查看新闻详情
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsListItem newsListItem = list.get(position);
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("title",newsListItem.getTitle());
                intent.putExtra("source",newsListItem.getSource());
                intent.putExtra("publishTime", newsListItem.getPublishTime());
                intent.putExtra("newsId",newsListItem.getNewsId());
                startActivity(intent);
            }
        });
    }

    private void stopRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }


    // 加载下一页
    private void loadNextPage() {
        if(isMore) {
            isMore = false;
            page ++;
            fetchNewsList();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    // 获取新闻列表
    private void fetchNewsList() {
        if(page > 1) progressBar.setVisibility(View.VISIBLE);
        NewsBiz newsBiz = RetrofitClientInstance.getRetrofitShujuzhihui().create(NewsBiz.class);
        newsBiz.newsList(appKey,category,page).enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                List<NewsListItem> newsList = response.body().getRESULT().getNewsList();
                list.addAll(newsList);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                isMore = true;
                if(page == 1) stopRefresh();
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                L.i(t.toString());
                Util.toast(getActivity(),"到底了");
                isMore = false;
                progressBar.setVisibility(View.GONE);
            }
        });
    }


}
