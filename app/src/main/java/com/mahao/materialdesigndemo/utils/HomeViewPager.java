package com.mahao.materialdesigndemo.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Penghy on 2017/8/3.
 */


public class HomeViewPager extends ViewPager {


    private boolean flag = false;

    public HomeViewPager(Context context) {
        super(context);
    }

    public HomeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     *   如果滑动事件在
     * @param ev
     * @return
     */

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        Log.i("mahao","1234");
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        Log.i("mahao","5678" + flag);
       // flag = true;
        if(flag){

            return false;
        }
        return super.onTouchEvent(ev);
    }

    /**
     *   设置状态是否已经到最左边
     * @param flag
     */
    public  void setFlag(boolean flag){

        this.flag = flag;
    }
}
