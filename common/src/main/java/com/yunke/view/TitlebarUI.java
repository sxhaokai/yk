package com.yunke.view;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunke.R;


/**
 * @author Administrator
 */
public class TitlebarUI extends FrameLayout implements OnClickListener ,TitleBarInterface{

    private TitleBarClickListner mListner;
    private TextView tvTitle;
    private TextView tvRight;
    private Activity mActivity;
    private RelativeLayout rl_right_iv_container;
    private ImageView iv_right;

    public TitlebarUI(@NonNull Context context) {
        this(context, null);
    }

    public TitlebarUI(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitlebarUI(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater.from(context).inflate(R.layout.view_title_bar, this);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvRight = (TextView) findViewById(R.id.tv_right);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        rl_right_iv_container = (RelativeLayout) findViewById(R.id.rl_right_iv_container);

        findViewById(R.id.iv_left).setOnClickListener(this);
        findViewById(R.id.rl_left).setOnClickListener(this);
        findViewById(R.id.tv_right).setOnClickListener(this);
        findViewById(R.id.rl_right_iv_container).setOnClickListener(this);
    }

    public void setRightText(String rightText) {
        tvRight.setText(rightText);
        tvRight.setVisibility(VISIBLE);
        rl_right_iv_container.setVisibility(GONE);
    }
    public void setRightIvResId(int rightIvResId) {
        iv_right.setImageResource(rightIvResId);
        rl_right_iv_container.setVisibility(VISIBLE);
        tvRight.setVisibility(GONE);
    }


    @Override
    public void setTitle(Activity activity ,String title) {
        tvTitle.setText(title);
        this.mActivity = activity;
    }

    @Override
    public void setTitleClickListener(TitleBarClickListner listener) {
        this.mListner = listener;
    }

    @Override
    public View selfView() {
        return this;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.rl_left || i == R.id.iv_left) {
            if (null != mActivity) {
                mActivity.finish();
            }
            if (null != this.mListner) {
                this.mListner.onTitleLeftClick(v);
            }
        } else if (i == R.id.tv_right || i == R.id.rl_right_iv_container) {
            if (null != this.mListner) {
                this.mListner.onTitleRightClick(v);
            }
        }
    }

    public void setLeftInvisible() {
        findViewById(R.id.rl_left).setVisibility(GONE);
    }

    public interface TitleBarClickListner {
        void onTitleLeftClick(View v);
        void onTitleRightClick(View v);
    }

}
