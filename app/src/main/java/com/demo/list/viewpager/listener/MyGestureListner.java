package com.demo.list.viewpager.listener;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2015/8/14.
 */
public class MyGestureListner extends GestureDetector.SimpleOnGestureListener {

    private OnFlingListener onFlingListener ;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float speedX, float speedY) {
        if(onFlingListener == null) {
            return super.onFling(e1, e2, speedX, speedY);
        }
        float XFrom = e1.getX(); //按下坐标
        float XTo = e2.getX();
        float YFrom = e1.getY();
        float YTo = e2.getY();
        Log.d("TAG","SpeedX->"+speedX+",SpeedY->"+speedY);
        Log.d("TAG","XFrom->"+XFrom+",XTo->"+XTo);
        // 左右滑动的幅度大于100，且滑动速度大于100时
        if(Math.abs(XFrom -XTo) >100 && Math.abs(speedX) >100){
            //X轴的幅度大于Y轴的幅度
            if(Math.abs(XFrom - XTo) >= Math.abs(YFrom - YTo)){
                //判断具体的方向
                if(XFrom >XTo){ //向左滑动
                    onFlingListener.flingToNext();
                }else { //向右滑动
                    onFlingListener.flingToPrevious();
                }
//                return true;
            }else {
                return false;
            }

        }
        return true;
    }

    /** 自定义滑动的回调接口 */
    public interface OnFlingListener{

        void flingToNext();  //滑动到下一个页面

        void flingToPrevious();  //滑动到前一个页面
    }

    public OnFlingListener getOnFlingListener() {
        return onFlingListener;
    }

    public void setOnFlingListener(OnFlingListener onFlingListener) {
        this.onFlingListener = onFlingListener;
    }
}
