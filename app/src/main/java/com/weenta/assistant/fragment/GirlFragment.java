package com.weenta.assistant.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.weenta.assistant.R;
import com.weenta.assistant.adapter.GirlAdapter;
import com.weenta.assistant.bean.GirlItem;
import com.weenta.assistant.bean.GirlList;
import com.weenta.assistant.biz.GirlBiz;
import com.weenta.assistant.network.RetrofitClientInstance;
import com.weenta.assistant.utils.L;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 美女社区
 */
public class GirlFragment extends Fragment {
    private RecyclerView recyclerView;
    private GirlAdapter adapter;
    private boolean isLoading = false;
    private int page = 1;
    private List<GirlItem> list = new ArrayList<>();
    private StaggeredGridLayoutManager layoutManager;
    private int lastVisibleItem;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girl, null);

        initView(view);
        initEvent();
        initData();
        return view;
    }

    private void initEvent() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE  && lastVisibleItem +1 == adapter.getItemCount()) {
                    L.i("------>到底了");
                    if(!isLoading) {
                        page ++;
                        fetchGirlList();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] into = new int[layoutManager.getSpanCount()];
                layoutManager.findLastVisibleItemPositions(into);
                lastVisibleItem = findMax(into);
            }
        });
    }

    /**
     * 获取数组中的最大值
     *
     * @param lastPositions 需要找到最大值的数组
     * @return 数组中的最大值
     */
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private void initView(View v) {
        recyclerView = v.findViewById(R.id.recycler_view);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        // 防止滑动中item换位
//        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GirlAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);

    }

    private void initData() {
        page = 1;
        list.removeAll(list);
        fetchGirlList();
    }

    // 获取列表
    private void fetchGirlList() {
        isLoading = true;
        GirlBiz girlBiz = RetrofitClientInstance.getRetrofitGanhuo().create(GirlBiz.class);
        girlBiz.prettyGirl(page).enqueue(new Callback<GirlList>() {
            @Override
            public void onResponse(Call<GirlList> call, Response<GirlList> response) {
                isLoading = false;
                L.i(response.toString());
                if(!response.body().isError()) {
                    List<GirlItem> results = response.body().getResults();
                    list.addAll(results);
                    L.i("list.size:" +list.size());
                    L.i("page:"+page);

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<GirlList> call, Throwable t) {
                isLoading = false;
                L.i(call.toString());
            }
        });
    }
}
