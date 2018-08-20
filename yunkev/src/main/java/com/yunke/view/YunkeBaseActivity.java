package com.yunke.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;


public abstract class YunkeBaseActivity extends AppCompatActivity {


    //share使用常量
    //share name
    public static final String SHARE_INDEX = "share_index";
    //用户手机
    public static final String PHONE = "share_phone";
    //用户token
    public static final String TOKEN = "share_token";
    //用户id
    public static final String USERID = "share_userid";
    //用户photo
    public static final String PHOTO = "share_photo";
    //用户类型
    public static final String TYPE = "share_type";
    //用户身份
    public static final String IDENTITY = "share_identity";
    //环信id
    public static final String HUANXIN_ID = "share_huanxin";
    //用户姓名
    public static final String NAME = "share_name";
    //用户id
//    public static final String CORPORATEID = "share_corporateid";
    //是否消息提醒
    public static final String NOTIFY = "share_notify";
    //通讯录的时间戳
    public static final String CONTACT_TIME = "contact_time";


    // 用于注册结束app的广播
    public static final String EXITAPP = "com.yanshanoa.finish";

    private InputMethodManager manager;

    //标示当前activity状态
    public final static int ONCREATE = 1;
    public final static int ONRESUME = 2;
    public final static int ONPAUSE = 3;
    public final static int ONSTOP = 4;
    private boolean compatActivity = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //设置主题
        setSelfTheme();
        setContentView(setContentId());
        initView();
        initListner();
        refresh();
    }

    protected abstract void initListner();

    protected abstract int setContentId();

    protected void initView(){

    }

    protected abstract void refresh();

    /**
     * 设置自己的主题
     */
    protected void setSelfTheme() {

    }

    /**
     * 隐藏软键盘
     */
    protected void hideKeyboard() {
        if (getCurrentFocus() != null)
            manager.hideSoftInputFromWindow(getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
