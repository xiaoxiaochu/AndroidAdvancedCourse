package com.go.android.course.http;









import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.xml.transform.OutputKeys;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableOperator;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by go on 2018/1/24.
 */

public class OkHttp3Utils {


    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    private OkHttpClient mOkHttpClient = new OkHttpClient();

    private static OkHttp3Utils mOkHttp3Utils;

    Map<String, String> map = new HashMap<>();
    List<File> files = new ArrayList<>();


    public static OkHttp3Utils getInstance(){
        if (mOkHttp3Utils  == null){
             synchronized (OkHttp3Utils.class){
                     mOkHttp3Utils = new OkHttp3Utils();
             }

        }
        return mOkHttp3Utils;
    }

    /**
     * 上传多张图片及参数
     * @param reqUrl URL地址
     * @param params 参数
     * @param pic_key 上传图片的关键字
     * @param files  图片路径
     */

    private Observable<String> sendMultipart(final String reqUrl, final Map<String, String> params, final String pic_key, final List<File> files){
        return  Observable.create(new ObservableOnSubscribe<String>() {
         @Override
         public void subscribe(final ObservableEmitter<String> emitter) throws Exception {

             MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
             multipartBodyBuilder.setType(MultipartBody.FORM);
             //遍历map中所有参数到builder
             if (params != null){
                 for (String key : params.keySet()) {
                     multipartBodyBuilder.addFormDataPart(key, params.get(key));
                 }
             }
             //遍历paths中所有图片绝对路径到builder，并约定key如“upload”作为后台接受多张图片的key
             if (files != null){
                 for (File file : files) {
                     multipartBodyBuilder.addFormDataPart(pic_key, file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
                 }
             }
             //构建请求体
             RequestBody requestBody = multipartBodyBuilder.build();

             Request.Builder RequestBuilder = new Request.Builder();
             RequestBuilder.url(reqUrl);// 添加URL地址
             RequestBuilder.post(requestBody);
             Request request = RequestBuilder.build();
             mOkHttpClient.newCall(request).enqueue(new Callback() {
                 @Override
                 public void onFailure(Call call, IOException e) {
                     emitter.onError(e);
                     emitter.onComplete();
                     call.cancel();
                 }

                 @Override
                 public void onResponse(Call call, Response response) throws IOException {
                     String str = response.body().string();
                     if (response.isSuccessful()){
                         emitter.onNext(str);
                     }
                     emitter.onComplete();
                     call.cancel();
                 }
             });
         }
         }
        );
    }




    private Flowable<String> getSimpleObservable(final String data){
       return Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                    emitter.onNext(data);
                    emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
    }





    private void userSimple(){
        sendMultipart(" ", map, "key", files).lift(new ObservableOperator<String, String>() {
            @Override
            public Observer<? super String> apply(Observer<? super String> observer) throws Exception {

                Observer o1 =  new Observer<String>(){

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                };
                return o1;
            }
        });

        sendMultipart(" ", map, "key", files)


                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        int s1 = Integer.valueOf(s);
                        return s1;
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        String s = String.valueOf(integer);
                        return Observable.fromArray(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.i("observer", s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i("observer", "complete");
            }
        });
    }
}
