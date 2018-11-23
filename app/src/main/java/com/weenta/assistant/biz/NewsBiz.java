package com.weenta.assistant.biz;

import com.weenta.assistant.bean.NewsDetail;
import com.weenta.assistant.bean.NewsList;
import com.weenta.assistant.bean.NewsListItem;
import com.weenta.assistant.utils.Config;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * 新闻
 */
public interface NewsBiz {

    // 新闻列表
    @FormUrlEncoded
    @POST("news/list")
    Call<NewsList> newsList(@Field("appKey") String appKey, @Field("category") String category, @Field("page") int page);

    // 新闻详情
    @FormUrlEncoded
    @POST("news/detail")
    Call<NewsDetail> newsDetail(@Field("appKey") String appKey, @Field("newsId") String newsId);

}
