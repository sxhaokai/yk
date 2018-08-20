package com.yunke.net;

import com.yunke.entity.YunkeStatusEntity;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by haokai on 2018/8/15.
 */

public interface CallBackHandler<T extends YunkeStatusEntity> {
    void  onHandle(Call<T> call, Response<T> response ,CallBack<T> callBack);
}
