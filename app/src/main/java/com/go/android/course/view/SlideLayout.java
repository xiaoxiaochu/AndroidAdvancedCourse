package com.go.android.course.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by go on 2018/1/22.
 */

public class SlideLayout extends FrameLayout {



    private ViewDragHelper viewDragHelper;

    /**
     * 父控件的高度
     * 通过测量获得
     */
    private int mSlideHeight;

    /**
     * 父控件的宽度
     */
    private int mSlideWidth;

    /**
     * 控件在水平方向拖拽的距离范围
     */
    private int mSlideRange;


    /**
     * 左侧菜单面板容器对象
     */
    private ViewGroup mMenuContainer;

    /**
     * 主内容面板容器对象
     */
    private ViewGroup mMainContainer;


    /**
     * 控件的状态集合，包括3种状态
     */
    private enum Status {
        CLOSE,      // 打开
        OPEN,       // 关闭
        SLIDING     // 正在滑动
    }



    public SlideLayout(@NonNull Context context) {
        super(context);
    }

    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        viewDragHelper = ViewDragHelper.create(this, 1.0f, mCallback);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }



    ViewDragHelper.Callback mCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return false;
        }


        @Override
        public int getViewHorizontalDragRange(View child) {
            return super.getViewHorizontalDragRange(child);
        }


        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return super.clampViewPositionHorizontal(child, left, dx);
        }


        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }


        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
        }



    };



}
