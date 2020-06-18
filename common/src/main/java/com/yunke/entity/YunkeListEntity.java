package com.yunke.entity;

import java.util.ArrayList;

/**
 * Created by hao.kai on 2017/6/8.
 */

public class YunkeListEntity<T extends YunkeEntity> implements YunkeEntity {
    private int pageSize;
    private int pageIndex;
    private int totalCount;
    private int pageCount;
    private ArrayList<T> page;
    public  ArrayList<T> getDataDataList() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getPageCount() {
        return pageCount;
    }
}
