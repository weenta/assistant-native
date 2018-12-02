package com.weenta.assistant.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    // 数据智汇api-新闻、快递
    private static final String BASE_URL_SHUJUZHIHUI = "http://api.shujuzhihui.cn/api/";
    private static Retrofit retrofitShujuzhihui;

    // 聚合数据-笑话大全
    private static final String BASE_URL_JUHESHUJU = "http://v.juhe.cn/";
    private static Retrofit retrofitJuheshuju;

    // 干货集中营-美女福利
    private static final String BASE_URL_GANHUO = "https://gank.io/api/";
    private static Retrofit retrofitGanhuo;

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

    /**
     * 聚合数据-笑话大全
     */
    public static Retrofit getRetrofitJuheshuju() {
        if(retrofitJuheshuju == null) {
            retrofitJuheshuju = new Retrofit.Builder()
                    .baseUrl(BASE_URL_JUHESHUJU)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitJuheshuju;
    }

    /**
     * 干货集中营-美女福利
     *
     */
    public static Retrofit getRetrofitGanhuo() {
        if(retrofitGanhuo == null) {
            retrofitGanhuo = new Retrofit.Builder()
                    .baseUrl(BASE_URL_GANHUO)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitGanhuo;
    }
}
