package com.demo.list.viewpager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

import com.demo.list.viewpager.R;

/**
 * Demo 2 :使用ViewPlipper完成滑动
 * 同一个Activity引入多个Layout(静态加载布局)
 *
 * @author Glanms
 * @date 15.08.14
 */
public class ViewFlipperActivity1 extends AppCompatActivity implements View.OnTouchListener{

    private ViewFlipper viewFlipper ;
    private float mTouchDistanceX = 0; //滑动的距离
    private float mTouchDownX = 0; //按下的横坐标
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper1);

        viewFlipper = (ViewFlipper)this.findViewById(R.id.view_flipper1);
        viewFlipper.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            mTouchDownX = event.getX();
            return true;
        }
        else if(event.getAction() == MotionEvent.ACTION_UP)
        {
            mTouchDistanceX = event.getX() - mTouchDownX;
            if(mTouchDistanceX >100){     //右滑显示前一项
                viewFlipper.setInAnimation(this,R.anim.anim_slide_in_left);
                viewFlipper.setOutAnimation(this,R.anim.anim_slide_out_right);
                viewFlipper.showPrevious();
            }else if(mTouchDistanceX < -100){   //左滑显示下一项
                viewFlipper.setInAnimation(this,R.anim.anim_slide_in_right);
                viewFlipper.setOutAnimation(this,R.anim.anim_slide_out_left);
                viewFlipper.showNext();
            }
            return true;
        }
        return false;
    }

}
