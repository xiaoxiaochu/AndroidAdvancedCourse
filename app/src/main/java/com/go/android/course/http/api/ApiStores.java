package com.go.android.course.http.api;

import com.go.android.course.myaidl.Game;
import com.go.android.course.util.StringUtils;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by go on 2018/2/6.
 */

public interface ApiStores {




    @GET("img")
    Observable<String> getImages(@Query("type") int type);


    @POST("token")
    Observable<String> checkToken(@Body Game game);

    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App",
    })
    @GET("users/{username}")
    Call<Game> getUser(@Path("username") String username);





}
