package com.ch.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class TitleView extends RelativeLayout {
    private int mTitleCenterTextSize;
    private int mTitleLeftTextSize;
    private int mTitleRightTextSize;
    private TextView mTvLeft;
    private TextView mTvCenter;
    private TextView mTvRight;
    private String mStringLeft;
    private String mStringCenter;
    private String mStringRight;

    private int mLeftBackDrawableRes;
    private int mCenterDrawableRes;
    private int mRightDrawableRes;


    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.TitleView, defStyleAttr, 0);
        mTitleLeftTextSize = array.getDimensionPixelSize(R.styleable.TitleView_title_left_size, 14);
        mTitleCenterTextSize = array.getDimensionPixelSize(R.styleable.TitleView_title_center_size, 16);
        mTitleRightTextSize = array.getDimensionPixelSize(R.styleable.TitleView_title_right_size, 14);
        mStringLeft = array.getString(R.styleable.TitleView_title_left_str);
        mStringCenter = array.getString(R.styleable.TitleView_title_center_str);
        mStringRight = array.getString(R.styleable.TitleView_title_right_str);
        mLeftBackDrawableRes = array.getResourceId(R.styleable.TitleView_title_left_drawable, R.mipmap.ic_launcher);
        mCenterDrawableRes = array.getResourceId(R.styleable.TitleView_title_center_drawable, R.drawable.ic_launcher_background);
        mRightDrawableRes = array.getResourceId(R.styleable.TitleView_title_right_drawable, R.drawable.ic_launcher_background);

        array.recycle();

        initView(context);
    }

    private void initView(Context context) {
        RelativeLayout rl = new RelativeLayout(context);
        rl.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        RelativeLayout.LayoutParams relLayoutParams = (LayoutParams) rl.getLayoutParams();
        if (relLayoutParams == null) {
            relLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }
        rl.setPadding(16, 0, 16, 0);
        rl.setLayoutParams(relLayoutParams);

        rl.addView(createLeftTv(context));
        rl.addView(createCenterTv(context));
        rl.addView(createRightTv(context));

        addView(rl);
    }

    private TextView createLeftTv(final Context context) {
        if (mTvLeft == null)
            mTvLeft = new TextView(context);
        if (!TextUtils.isEmpty(mStringLeft)) {
            mTvLeft.setText(mStringLeft);
            mTvLeft.setTextSize(mTitleLeftTextSize);
        } else {
            mTvLeft.setBackgroundResource(mLeftBackDrawableRes);
        }
        RelativeLayout.LayoutParams layoutParams = (LayoutParams) mTvLeft.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        }
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mTvLeft.setLayoutParams(layoutParams);
        mTvLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnTitleLeftClick != null) {
                    mOnTitleLeftClick.leftTvClick();
                } else {
                    if (context instanceof Activity) {
                        if (!((Activity) context).isFinishing()) {
                            ((Activity) context).finish();
                        }
                    }
                }
            }
        });
        return mTvLeft;
    }

    private TextView createCenterTv(Context context) {
        if (mTvCenter == null)
            mTvCenter = new TextView(context);
        if (!TextUtils.isEmpty(mStringCenter)) {
            mTvCenter.setText(mStringCenter);
            mTvCenter.setTextSize(mTitleCenterTextSize);
        } else {
            mTvCenter.setBackgroundResource(mCenterDrawableRes);
        }
        RelativeLayout.LayoutParams layoutParams = (LayoutParams) mTvCenter.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        }
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mTvCenter.setLayoutParams(layoutParams);
        if (mOnTitleCenterClick != null) {
            mOnTitleCenterClick.centerTvClick();
        }
        return mTvCenter;
    }

    private TextView createRightTv(Context context) {
        if (mTvRight == null)
            mTvRight = new TextView(context);
        if (!TextUtils.isEmpty(mStringRight)) {
            mTvRight.setText(mStringRight);
            mTvRight.setTextSize(mTitleRightTextSize);
        } else {
            mTvRight.setBackgroundResource(mRightDrawableRes);
        }
        RelativeLayout.LayoutParams layoutParams = (LayoutParams) mTvRight.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        }
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mTvRight.setLayoutParams(layoutParams);
        if (mOnTitleRightClick != null) {
            mOnTitleRightClick.rightTvClick();
        }
        return mTvRight;
    }

    public OnTitleLeftClick mOnTitleLeftClick;

    public OnTitleCenterClick mOnTitleCenterClick;

    public OnTitleRightClick mOnTitleRightClick;

    public void setOnTitleLeftClick(OnTitleLeftClick titleLeftClick) {
        this.mOnTitleLeftClick = titleLeftClick;
    }

    public void setOnTitleCenterClick(OnTitleCenterClick titleCenterClick) {
        this.mOnTitleCenterClick = titleCenterClick;
    }

    public void setOnTitleRightClick(OnTitleRightClick titleRightClick) {
        this.mOnTitleRightClick = titleRightClick;
    }

    public interface OnTitleLeftClick {
        void leftTvClick();
    }

    public interface OnTitleCenterClick {
        void centerTvClick();
    }

    public interface OnTitleRightClick {
        void rightTvClick();
    }
}
