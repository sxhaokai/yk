package com.yunke.net;

import com.yunke.entity.YunkeStatusEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hao.kai on 2017/6/8.
 */

public abstract class CB2<T extends YunkeStatusEntity> extends CallBack<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (null != YunkeNet.getInstance().getmCallBackHandler()) {
            YunkeNet.getInstance().getmCallBackHandler().onHandle(call ,response ,this);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        netFail(call, t.getMessage());
    }
}
