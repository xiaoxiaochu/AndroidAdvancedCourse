package com.go.android.course.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;



/**
 * Created by go on 2018/1/22.
 */

public class DragLayout extends LinearLayout {

    private  ViewDragHelper mDragHelper;

    private View mDragView1;
    private View mDragView2;

    private boolean mDragEdge;
    private boolean mDragHorizontal;
    private boolean mDragCapture;
    private boolean mDragVertical;

    public DragLayout(Context context) {
        this(context, null);
    }

    public DragLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mDragHelper = ViewDragHelper.create(this, 1.0f, new DragCallback());

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount < 2){
            throw new IllegalStateException("the child view must more than two");
        }
        mDragView1 = getChildAt(0);
        mDragView2 = getChildAt(1);
    }


    public void setDragHorizontal(boolean dragHorizontal) {
        mDragHorizontal = dragHorizontal;
        mDragView2.setVisibility(View.GONE);
    }

    public void setDragVertical(boolean dragVertical) {
        mDragVertical = dragVertical;
        mDragView2.setVisibility(View.GONE);
    }

    public void setDragEdge(boolean dragEdge) {
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
        mDragEdge = dragEdge;
        mDragView2.setVisibility(View.GONE);
    }

    public void setDragCapture(boolean dragCapture) {
        mDragCapture = dragCapture;
    }

    private class DragCallback  extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            if (mDragCapture) {
                return child == mDragView1;
            }
            return true;
        }


        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.i("DragLayout", "clampViewPositionHorizontal " + left + "," + dx);
            if (mDragHorizontal || mDragCapture || mDragEdge) {
                final int leftBound = getPaddingLeft();
                final int rightBound = getWidth() - mDragView1.getWidth();
                Log.i("DragLayout", "leftBound:"+ leftBound + " dragViewWidth" + mDragView1.getWidth() + " rightBound" + rightBound);

                final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
//                final int newLeft = Math.max(left, leftBound);


                return newLeft;
            }
            return super.clampViewPositionHorizontal(child, left, dx);
        }


        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            //super.onViewPositionChanged(changedView, left, top, dx, dy);
            invalidate();
        }


        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);

        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            if (mDragEdge) {
                mDragHelper.captureChildView(mDragView1, pointerId);
            }
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            if (mDragVertical) {
                final int topBound = getPaddingTop();
                final int bottomBound = getHeight() - mDragView1.getHeight();

                final int newTop = Math.min(Math.max(top, topBound), bottomBound);

                return newTop;
            }
            return super.clampViewPositionVertical(child, top, dy);
        }
    };


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP){
            mDragHelper.cancel();
            return false;
        }
        return mDragHelper.shouldInterceptTouchEvent(ev);
        //return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

}
