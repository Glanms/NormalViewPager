package com.demo.list.viewpager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

import com.demo.list.viewpager.R;
import com.demo.list.viewpager.listener.MyGestureListner;

/**
 * 自定义View滑动类：监听滑动事件，并做切换视图的处理
 * Created by Glanms on 2015/8/14.
 */
public class MyViewFlipper extends ViewFlipper implements MyGestureListner.OnFlingListener{

    // 手势监听类
    private GestureDetector gestureDetector = null;
    private OnViewFlipperListener mOnViewFlipperListener = null;


    public MyViewFlipper(Context context) {
        super(context);
    }

    public MyViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnViewFlipperListener(OnViewFlipperListener onViewFlipperListener){
        this.mOnViewFlipperListener = onViewFlipperListener;
        // 初始化自定义滑动事件监听器
        MyGestureListner myGestureListner = new MyGestureListner();
        // 绑定自定义的滑动监听器
        myGestureListner.setOnFlingListener(this);
        gestureDetector = new GestureDetector(myGestureListner);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(gestureDetector !=null){
            return gestureDetector.onTouchEvent(ev);
        }else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        gestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    /** 向下一条滑动事件 */
    @Override
    public void flingToNext() {
        if(mOnViewFlipperListener !=null){
            int childCnt = getChildCount();
            if(childCnt == 2){
                removeViewAt(1);
            }
            addView(mOnViewFlipperListener.getNextView(),0);
            if(childCnt != 0){
                setInAnimation(getContext(), R.anim.push_in_right);
                setOutAnimation(getContext(),R.anim.push_out_left);
                setDisplayedChild(0);
            }
        }
    }

    /** 向上一条滑动事件 */
    @Override
    public void flingToPrevious() {
        if(mOnViewFlipperListener != null){
            int childCnt = getChildCount();
            if(childCnt == 2){
                removeViewAt(1);
            }
            addView(mOnViewFlipperListener.getPreviousView(),0);
            if(childCnt != 0){
                setInAnimation(getContext(),R.anim.push_in_left);
                setOutAnimation(getContext(),R.anim.push_out_right);
                setDisplayedChild(0);
            }
        }
    }

    /** 自定义View变化监听回调接口 */
    public interface OnViewFlipperListener{
        View getPreviousView();  //获取前一个View

        View getNextView();    //获取后一个View
    }
}
