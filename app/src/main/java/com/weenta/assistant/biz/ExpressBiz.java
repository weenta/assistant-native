package com.weenta.assistant.biz;

import com.weenta.assistant.bean.Express;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 物流
 */
public interface ExpressBiz  {
    // 物流查询
    @FormUrlEncoded
    @POST("sjzhApi/searchExpress")
    Call<Express> express(@Field("appKey") String appKey, @Field("expressNo") String expressNo);
}
