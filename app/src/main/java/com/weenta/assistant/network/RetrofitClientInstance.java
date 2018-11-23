package com.weenta.assistant.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    // 数据智汇api-新闻 快递
    private static final String BASE_URL_SHUJUZHIHUI = "http://api.shujuzhihui.cn/api/";
    private static Retrofit retrofitShujuzhihui;

    /**
     * 数据智汇-新闻 快递
     * @return
     */
    public static Retrofit getRetrofitShujuzhihui(){
        if(retrofitShujuzhihui == null) {
            retrofitShujuzhihui = new Retrofit.Builder()
                    .baseUrl(BASE_URL_SHUJUZHIHUI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitShujuzhihui;
    }
}
