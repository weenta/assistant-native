package com.weenta.assistant.biz;

import com.weenta.assistant.bean.JokeList;
import com.weenta.assistant.utils.Config;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 笑话
 */
public interface JokeBiz {
    // 笑话列表
    @GET("joke/content/list.php")
    Call<JokeList> jokeList(@Query("key") String key, @Query("sort") String sort,@Query("time") String time, @Query("page") int page);

//    @GET("joke/content/list.php?key=" + Config.JOKE_KEY + "")
}
