package com.weenta.assistant.biz;

import com.weenta.assistant.bean.GirlList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 美女福利
 */
public interface GirlBiz {
    @GET("data/福利/20/{page}")
    Call<GirlList> prettyGirl(@Path("page") int page);
}
