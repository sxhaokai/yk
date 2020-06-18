package com.yunke.net;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hao.kai on 2016/11/23.
 */

public abstract class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder();
        requestBuilder.addHeader("Accept" ,"application/json");
        requestBuilder.addHeader("Content-Type" ,"application/json");
        requestBuilder.addHeader("token" , provideToken());
        requestBuilder.addHeader("userId" ,provideUserId());

//        if (original.body() != null && original.body() instanceof FormBody) {
//            //转换为FormBody
//            FormBody oldBody = (FormBody) original.body();
//            //重构请求体
//            FormBody.Builder newBody = new FormBody.Builder();
//            //原始的参数
//            TreeMap<String, Object> map = new TreeMap<>();
//            for (int i = 0; i < oldBody.size(); i++) {
//                map.put(oldBody.name(i) ,oldBody.value(i));
//                newBody.addEncoded(oldBody.encodedName(i), oldBody.encodedValue(i));
//            }
//            //*****加密*****
//            newBody.add("result" ,PostEncryptUtil.encrypt(map));
//            requestBuilder.method(original.method(), newBody.build());
//        }


        return chain.proceed(requestBuilder.build());
    }

    public abstract String provideToken();
    public abstract String provideUserId();

}
