package com.weenta.assistant.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.weenta.assistant.R;
import com.weenta.assistant.adapter.ExpressAdapter;
import com.weenta.assistant.bean.Express;
import com.weenta.assistant.bean.ExpressItem;
import com.weenta.assistant.biz.ExpressBiz;
import com.weenta.assistant.network.RetrofitClientInstance;
import com.weenta.assistant.utils.Config;
import com.weenta.assistant.utils.L;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 物流查询
 */
public class ExpressActivity extends BaseActivity {
    private EditText et_express_no;
    private Button btn_query;
    private ListView lv_express;
    private SwipeRefreshLayout refresh_layout;
    private ProgressBar progressBar;

    private String expressNo;
    private List<ExpressItem> list = new ArrayList<>();
    private ExpressAdapter adapter;
    private boolean is_refreshing = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express);
        initView();
        initEvent();
    }

    private void initEvent() {
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchExpress();
            }
        });

        // 下拉刷新
        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.removeAll(list);
                is_refreshing = true;
                fetchExpress();
            }
        });
    }

    // 查询物流
    private void fetchExpress() {
        if(!is_refreshing) progressBar.setVisibility(View.VISIBLE);
        expressNo = et_express_no.getText().toString();

        ExpressBiz expressBiz = RetrofitClientInstance.getRetrofitShujuzhihui().create(ExpressBiz.class);
        expressBiz.express( Config.EXPRESS_APP_KEY,expressNo).enqueue(new Callback<Express>() {
            @Override
            public void onResponse(Call<Express> call, Response<Express> response) {
                L.i(response.toString());
                list = response.body().getRESULT().getContext();
                adapter = new ExpressAdapter(ExpressActivity.this,list);
                lv_express.setAdapter(adapter);
                refresh_layout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Express> call, Throwable t) {
                refresh_layout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void initView() {
        et_express_no = findViewById(R.id.et_express_no);
        btn_query = findViewById(R.id.btn_query);
        lv_express = findViewById(R.id.lv_express);
        refresh_layout = findViewById(R.id.refresh_layout);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
    }
}
