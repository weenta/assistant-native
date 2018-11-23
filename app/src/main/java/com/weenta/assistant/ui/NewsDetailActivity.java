package com.weenta.assistant.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.weenta.assistant.R;
import com.weenta.assistant.bean.NewsDetail;
import com.weenta.assistant.biz.NewsBiz;
import com.weenta.assistant.network.RetrofitClientInstance;
import com.weenta.assistant.utils.Config;
import com.weenta.assistant.utils.L;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 新闻详情
 * 使用webView加载
 */
public class NewsDetailActivity extends BaseActivity{
    private TextView tv_title,tv_source,tv_publish_time;
    private String title;
    private String source;
    private String publishTime;
    private String newsId;
    private String appKey = Config.NEWS_APP_KEY;
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        initData();
        initView();
        initEvent();
    }

    private void initEvent() {
        // 监听webview加载
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initView() {
        tv_title = findViewById(R.id.title);
        tv_source = findViewById(R.id.source);
        tv_publish_time = findViewById(R.id.publish_time);
        webView = findViewById(R.id.webview_news_detail);
        webView.setVisibility(View.GONE);
        progressBar = findViewById(R.id.progress_bar);

        tv_title.setText(title);
        tv_source.setText(source);
        tv_publish_time.setText(publishTime);
    }

    private void initData() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        source = intent.getStringExtra("source");
        publishTime = intent.getStringExtra("publishTime");
        newsId = intent.getStringExtra("newsId");

        L.i(newsId);
        fetchNewsDetail();
    }

    // 获取新闻详情
    public void fetchNewsDetail() {
        NewsBiz newsBiz = RetrofitClientInstance.getRetrofitShujuzhihui().create(NewsBiz.class);
        newsBiz.newsDetail(appKey,newsId).enqueue(new Callback<NewsDetail>() {
            @Override
            public void onResponse(Call<NewsDetail> call, Response<NewsDetail> response) {
                NewsDetail newsDetail = response.body();
                String htmlStr = resetImg(newsDetail.getRESULT().getContent());
                webView.loadData(htmlStr,"text/html; charset=UTF-8",null);
            }

            @Override
            public void onFailure(Call<NewsDetail> call, Throwable t) {
                toast("加载失败！");
            }
        });
    }

    // 重置img标签
    private String resetImg(String content) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width:100% !important; width:auto; height:auto;}</style>" +
                "</head>";
        String html = "<html>" + head + "<body style:'height:auto;max-width: 100%; width:auto;'>" + content + "</body></html>";
        return html;
    }
}
