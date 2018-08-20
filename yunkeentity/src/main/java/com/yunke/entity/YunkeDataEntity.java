package com.yunke.entity;

/**-->
 *      String code;
 *      String meaasge;
 *      Object data;
 *
 * Created by haokai on 2018/8/20.
 */

public class YunkeDataEntity<T extends YunkeEntity> extends YunkeStatusEntity {
    private T data;

    public T getData() {
        return data;
    }
}
