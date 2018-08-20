package com.yunke.entity;

import java.util.ArrayList;

/**
 * Created by hao.kai on 2017/6/8.
 */

public abstract class YunkeListEntity<T extends YunkeEntity> extends YunkeStatusEntity {
    public abstract ArrayList<T> getDataDataList();
}
