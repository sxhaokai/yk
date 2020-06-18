package com.yunke.view;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;


public abstract class YunkeBaseFragment extends Fragment {

    public View mRootView;
    public Context context;
    public InputMethodManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
    }

    // 设置界面对应的view对象
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null == mRootView) {

            mRootView = initView();
        } else {
            ViewGroup parent = (ViewGroup) this.mRootView.getParent();
            if (parent != null) {
                parent.removeView(this.mRootView);
            }

        }
        return this.mRootView;


    }

    private boolean hasInitData;

    // 填充数据的操作，
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // 拿数据去填充UI
        super.onActivityCreated(savedInstanceState);
        if (hasInitData) {
            return;
        }
        initData();
        hasInitData = true;

        manager = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    // 预设UI方法
    public abstract View initView();

    // 数据填充UI的方法
    public abstract void initData();

    /**
     * 隐藏软键盘
     */
    public void hideKeyboard() {
        if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getActivity().getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getActivity().getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


}
