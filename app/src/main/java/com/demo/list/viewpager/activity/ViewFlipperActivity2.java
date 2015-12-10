package com.demo.list.viewpager.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.demo.list.viewpager.R;
import com.demo.list.viewpager.view.MyViewFlipper;

/**
 * Demo 2 :使用ViewPlipper完成滑动
 *  动态加载布局
 *
 * @author Glanms
 * @date 15.08.14
 */
public class ViewFlipperActivity2 extends AppCompatActivity implements MyViewFlipper.OnViewFlipperListener{

    private MyViewFlipper myViewFlipper = null;
    private int currentNumber;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper2);

        currentNumber = 1;  //默认页号
//        myViewFlipper = new MyViewFlipper(this);
        myViewFlipper = (MyViewFlipper) findViewById(R.id.body_flipper);

        // 给ViewFlipper绑定自定义滑动事件
        myViewFlipper.setOnViewFlipperListener(this);
        // 初始化页面数据,即View
        myViewFlipper.addView(createView(1));
    }

    /** 获取后一页的View */
    @Override
    public View getNextView() {
        // 滑到末页继续滑处理
        currentNumber = currentNumber == 10 ? 1 : currentNumber + 1;
        return createView(currentNumber);
    }

    /** 获取前一页的View */
    @Override
    public View getPreviousView() {
        // 滑到首页继续滑处理
        currentNumber = currentNumber == 1 ? 10 : currentNumber - 1;
        return createView(currentNumber);
    }

    private View createView(int currentNumber){
        LayoutInflater inflater = LayoutInflater.from(this);
        ScrollView resultView = (ScrollView)inflater.inflate(R.layout.flipper_view,null);
//        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.flipper_view,null);
        TextView tv = (TextView)resultView.findViewById(R.id.flipper_tv);
        tv.setText(""+currentNumber);
        return resultView;
    }
}
