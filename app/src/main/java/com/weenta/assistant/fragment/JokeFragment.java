package com.weenta.assistant.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.weenta.assistant.R;
import com.weenta.assistant.adapter.JokeAdapter;
import com.weenta.assistant.bean.JokeItem;
import com.weenta.assistant.bean.JokeList;
import com.weenta.assistant.biz.JokeBiz;
import com.weenta.assistant.network.RetrofitClientInstance;
import com.weenta.assistant.utils.Config;
import com.weenta.assistant.utils.L;
import com.weenta.assistant.utils.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 轻松一刻
 */
public class JokeFragment extends Fragment{
    private ListView lv_joke;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;

    private String key = Config.JOKE_KEY;
    private String sort = "desc";
    private int page = 1;
    private JokeAdapter adapter;
    private boolean isMore = true;

    List<JokeItem> list = new ArrayList<>();
    // 10位时间戳
    private String time = (new Date().getTime() / 1000) + "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke,null);

        initView(view);
        initEvent();
        initData();
        return view;
    }

    private void initData() {
        fetchJokeList();
    }

    private void initEvent() {
        // 滑动底部 加载下一页
        lv_joke.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem + visibleItemCount >= totalItemCount && totalItemCount > 0) {
                    if(isMore)  loadNextPage();
                }
            }
        });

        // 下拉刷新
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.removeAll(list);
                adapter.notifyDataSetChanged();
                page = 1;
                isMore = true;
                fetchJokeList();
            }
        });

    }

    private void loadNextPage() {
        isMore = false;
        page ++;
        fetchJokeList();
    }

    // 获取笑话列表
    private void fetchJokeList() {
        if(page > 1) progressBar.setVisibility(View.VISIBLE);
        JokeBiz jokeBiz = RetrofitClientInstance.getRetrofitJuheshuju().create(JokeBiz.class);
        jokeBiz.jokeList(key,sort,time,page).enqueue(new Callback<JokeList>() {
            @Override
            public void onResponse(Call<JokeList> call, Response<JokeList> response) {
                L.i(response.toString());

                JokeList.JokeResult result = response.body().getResult();
                if(result != null) {
                    List<JokeItem> jokeItems = result.getData();
                    list.addAll(jokeItems);
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    isMore = true;
                    if(page == 1) refreshLayout.setRefreshing(false);
                }else {
                    Util.toast(getActivity(),response.body().getReason());
                }
            }

            @Override
            public void onFailure(Call<JokeList> call, Throwable t) {
                Util.toast(getActivity(),"加载失败");
                progressBar.setVisibility(View.GONE);
                if(page == 1) refreshLayout.setRefreshing(false);
            }
        });
    }

    private void initView(View v) {
        lv_joke = v.findViewById(R.id.lv_joke);
        progressBar = v.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        adapter = new JokeAdapter(getActivity(),list);
        lv_joke.setAdapter(adapter);

        refreshLayout = v.findViewById(R.id.refresh_layout);


    }


}
