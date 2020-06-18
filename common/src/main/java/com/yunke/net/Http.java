package com.yunke.net;


import com.yunke.util.L;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hao.kai on 2017/6/8.
 */

public class Http<T> {

    private  T mService;
    private  Retrofit mRetrofit;

    public T with(Class<? extends T> clazz) {
        if (mService == null)
            mService = createService(clazz);
        return mService;
    }

    private  T createService(Class<? extends T> clazz) {
        if(null == mRetrofit) {

            mRetrofit = getRetrofit();
        }
        return mRetrofit.create(clazz);
    }



    private static Retrofit getRetrofit() {

        OkHttpClient client = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(20, TimeUnit.SECONDS)//20s
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(YunkeNet.getInstance().getmInterceptor())//拦截器
                    .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            L.e(message);
                        }
                    }).setLevel(HttpLoggingInterceptor.Level.BODY))//拦截器
                    .build();

        return new Retrofit.Builder()
                .baseUrl(YunkeNet.getInstance().getmHost())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

}
