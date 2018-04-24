package com.go.android.course.http;

import android.text.TextUtils;

import com.go.android.course.http.api.ApiStores;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by go on 2018/2/6.
 *
 * 全局使用token retrofit + OkHttp3
 */

public class RetrofitServiceInstance {


   private static ApiStores instance = null;

   public static ApiStores getInStance(){
      Authenticator authenticator = new Authenticator() {//当服务器返回的状态码为401时，会自动执行里面的代码，也就实现了自动刷新token
         @Override
         public Request authenticate(Route route, Response response) throws IOException {
            //  L.d("==========>   重新刷新了token");//这里可以进行刷新 token 的操作
//                instance.getUploadToken()
            return response.request().newBuilder()
                    .addHeader("token", "")
                    .build();
         }
      };
      Interceptor tokenInterceptor = new Interceptor() {//全局拦截器，往请求头部添加 token 字段，实现了全局添加 token
         @Override
         public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();//获取请求
            Request tokenRequest = null;
            if (TextUtils.isEmpty("fgegegtyi539405get3")) {//对 token 进行判空，如果为空，则不进行修改
               return chain.proceed(originalRequest);
            }
            tokenRequest = originalRequest.newBuilder()//往请求头中添加 token 字段
                    .header("token", "refgeg953543v4t4v464r")
                    .build();
            return chain.proceed(tokenRequest);
         }
      };
      HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {//log拦截器，打印所有的log
         @Override
         public void log(String message) {
            //  L.d(message);
         }
      });



      OkHttpClient client = new OkHttpClient.Builder()
              .connectTimeout(15, TimeUnit.SECONDS)
              .retryOnConnectionFailure(true)
              .addNetworkInterceptor(tokenInterceptor)
              .addInterceptor(loggingInterceptor)//使用上面的拦截器
              .authenticator(authenticator)
              .build();



         if (instance == null) {
            synchronized (ApiStores.class) {
               if (instance == null) {
                  Retrofit retrofit = new Retrofit.Builder()                              //生成实例
                          .baseUrl("http://www.baidu.com")                                  //基础url，会拼接NetService中的参数
                          .client(client)                                                 //使用 okhttp
                          .addConverterFactory(GsonConverterFactory.create())            //使用Gson解析
                          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())      //加入RxJava的适配器
                          .build();

                  instance = retrofit.create(ApiStores.class);
               }
            }
         }
         return instance;


   }


}
