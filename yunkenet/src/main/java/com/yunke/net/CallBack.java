package com.yunke.net;


import com.yunke.util.L;

import retrofit2.Call;

/**
 * Created by hao.kai on 2017/6/8.
 */

public abstract class CallBack<T> {

    /**
     * 返回数据，并且请求的数据是正确的
     *
     * @param body
     */
    public abstract void onSuccess(T body);

    /**
     * 网络异常，链接服务器异常
     *
     * @param call
     * @param failMessage
     */
    public void netFail(Call call, String failMessage) {
        L.e("请求失败 ： " + failMessage);
    }

    /**
     * 返回数据，但是请求的数据有误
     *
     * @param body
     */
    public void onError(T body) {

    }
}
