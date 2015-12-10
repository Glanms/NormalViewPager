package com.demo.list.viewpager.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.list.viewpager.R;
import com.demo.list.viewpager.adapter.SimpleFragmentAdapter;
import com.demo.list.viewpager.fragment.SimpleFragment;
import com.demo.list.viewpager.fragment.SimpleFragment2;
import com.demo.list.viewpager.fragment.SimpleFragment3;
import com.demo.list.viewpager.fragment.SimpleFragment4;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo 1 ：使用ViewPager + Fragment实现
 * （当然，用静态布局的VieewPager+Layout也可以实现，但官方推荐Fragment实现）
 *  ViewPager页的Activity
 *
 * @author Glanms
 * @date 15.08.13
 */
public class SimpleFragmentActivity extends AppCompatActivity {

    private List<Fragment> fragmentList = new ArrayList<>();
    private SimpleFragmentAdapter mFragmentAdapter = null;
    private ViewPager viewPager = null;
    /**
     * 四个Tab中显示的TextView
     */
    private TextView tab_tv1, tab_tv2, tab_tv3, tab_tv4 = null;
    /**
     * Tab显示的底部色块
     */
    private ImageView tab_bottom = null;
    private SimpleFragment key_sf;
    private SimpleFragment2 recond_sf;
    private SimpleFragment3 collect_sf;
    private SimpleFragment4 contacts_sf;
    /**
     * Tab默认选中页
     */
    private int currentIndex;
    /**
     * 屏幕宽度
     */
    private int screenWidth;
    private static final String TAG = "SFViewpager";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_fragment);

        initView();
        init();
        initTabLineWidth();
    }

    private void initView(){
      /*  inflater = LayoutInflater.from(this);
        View rootView = inflater.inflate(R.layout.activity_main_top_tab,null);*/
        tab_tv1 = (TextView)this.findViewById(R.id.main_top_tab_tv1);
        tab_tv2 = (TextView)this.findViewById(R.id.main_top_tab_tv2);
        tab_tv3 = (TextView)this.findViewById(R.id.main_top_tab_tv3);
        tab_tv4 = (TextView)this.findViewById(R.id.main_top_tab_tv4);
        tab_bottom = (ImageView)this.findViewById(R.id.main_top_tab_iv);
        viewPager = (ViewPager)this.findViewById(R.id.simple_viewpager);
    }

    /**
     * 初始化Fragment和ViewPager
     */
    private void init(){
        key_sf = new SimpleFragment();
        recond_sf = new SimpleFragment2();
        collect_sf = new SimpleFragment3();
        contacts_sf = new SimpleFragment4();
        fragmentList.add(key_sf);
        fragmentList.add(recond_sf);
        fragmentList.add(collect_sf);
        fragmentList.add(contacts_sf);
        mFragmentAdapter = new SimpleFragmentAdapter(
                this.getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(mFragmentAdapter);
        viewPager.setCurrentItem(0);
        isActived(tab_tv1);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             *  页面滑动监听
             * @param position  当前页面，即滑动到的页面
             * @param offset  页面的偏移百分比
             * @param positionOffsetPixels 页面的偏移像素值
             */
            @Override
            public void onPageScrolled(int position, float offset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)
                        tab_bottom.getLayoutParams();
                Log.d(TAG,"Offset--"+offset);
                /**
                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                 * 设置tab_bottom的左边距 滑动场景：
                 * 记4个页面,
                 * 从左到右分别为0,1,2,3
                 * 0->1; 1->2; 2->3; 3->2; 2->1; 1->0
                 */
                if (currentIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 4) + currentIndex
                            * (screenWidth / 4));

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 4) + currentIndex
                            * (screenWidth / 4));

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 4) + currentIndex
                            * (screenWidth / 4));
                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 4) + currentIndex
                            * (screenWidth / 4));
                }
                else if (currentIndex == 2 && position == 2) // 2->3
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 4) + currentIndex
                            * (screenWidth / 4));
                } else if (currentIndex == 3 && position == 2) // 3->2
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 4) + currentIndex
                            * (screenWidth / 4));
                }

                tab_bottom.setLayoutParams(lp);

            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position)
                {
                    case 0:
                        isActived(tab_tv1);
                        break;
                    case 1:
                        isActived(tab_tv2);
                        break;
                    case 2:
                        isActived(tab_tv3);
                        break;
                    case 3:
                        isActived(tab_tv4);
                        break;
                }
                currentIndex = position;
            }

            /**
             * 滑动的三种状态
             * @param state （0，1，2）-->0 正在滑动 1 滑动结束 2 什么都没做
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG,"State--"+state);
            }
        });

    }

    /**
     * 当Tab页被激活时
     * @param view  Tabtitle
     */
    private void isActived(TextView view){
        view.setTextColor(this.getResources().getColor(R.color.main_top_tab_text_actived));
    }

    /**
     * 设置Tab条的宽度（1/4屏幕宽度,根据tab数确定）
     */
    private void initTabLineWidth(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)tab_bottom.getLayoutParams();
        screenWidth = displayMetrics.widthPixels;
        lp.width = screenWidth /4;
        tab_bottom.setLayoutParams(lp);
    }

    /**
     * 设置tab_tv的默认颜色
     */
    private void resetTextView(){
        tab_tv1.setTextColor(getResources().getColor(R.color.main_top_tab_text_normal));
        tab_tv2.setTextColor(getResources().getColor(R.color.main_top_tab_text_normal));
        tab_tv3.setTextColor(getResources().getColor(R.color.main_top_tab_text_normal));
        tab_tv4.setTextColor(getResources().getColor(R.color.main_top_tab_text_normal));
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_right);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simple, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
