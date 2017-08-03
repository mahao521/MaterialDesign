package com.mahao.materialdesigndemo.behavior;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Penghy on 2017/8/1.
 */


public class ToolbarBehavior extends ChildBehavior {

    int maxHeight = 400;

    int originHeight = 0;

    int unUseY = 0;

    int useY = 0;

    boolean  flag = true;

    /**
     * Default constructor for inflating Behaviors from layout. The Behavior will have
     * the opportunity to parse specially defined layout parameters. These parameters will
     * appear on the child view tag.
     *
     * @param context
     * @param attrs
     */
    public ToolbarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public void onLayoutFinish(View parent, View child) {
        super.onLayoutFinish(parent, child);

        if(originHeight == 0){

            originHeight = child.getHeight();
        }
    }

    @Override
    public void onNestedScroll(View coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

        Log.i("mahao1234",target.getScrollY()+"...."+dyUnconsumed+"。。。"+dyConsumed);
        unUseY = unUseY + Math.abs(dyUnconsumed);
      /*
        useY = useY + Math.abs(dyConsumed);
        if(unUseY != 0 && dyConsumed == 0){

           ViewCompat.setAlpha(child,1-unUseY*1.0f/(maxHeight-originHeight));
        }else {

           // ViewCompat.setAlpha(child,1);
            if(useY != 0 && dyUnconsumed == 0){
                ViewCompat.setAlpha(child,useY*1.0f/(maxHeight-originHeight));
            }
        }
        */

        /*int scrollY = target.getScrollY();
        float alpha = child.getAlpha();

            unUseY = unUseY + Math.abs(dyUnconsumed);
            useY = useY + Math.abs(dyConsumed);
            if(unUseY != 0 && dyConsumed == 0){

                ViewCompat.setAlpha(child,1-unUseY*1.0f/(maxHeight-originHeight));
            }else {

                // ViewCompat.setAlpha(child,1);
                if(useY != 0 && dyUnconsumed == 0){
                    ViewCompat.setAlpha(child,useY*1.0f/(maxHeight-originHeight));
                }
            }*/
/*
        ContainerNetedView contain = (ContainerNetedView) coordinatorLayout;
        int childCount = contain.getChildCount();
        for(int i = 0; i < childCount; i++){

            if(contain.getChildAt(i) instanceof ImageView){

                ViewGroup.LayoutParams layoutParams = contain.getChildAt(i).getLayoutParams();
                if(layoutParams.height == originHeight){

                }else if(layoutParams.height > originHeight){
                    ViewCompat.setAlpha(child,target.getScrollY()*1.0f/(maxHeight-originHeight));
                }
                break;
            }
        }



       /* if (2 <= target.getScrollY() &&  target.getScrollY() <= maxHeight || dyUnconsumed== 0 ) {

            //第一次进入不应该设置透明度
            ContainerNetedView contain = (ContainerNetedView) coordinatorLayout;
            int childCount = contain.getChildCount();
            for(int i = 0; i < childCount; i++){

                if(contain.getChildAt(i) instanceof ImageView){

                    ViewGroup.LayoutParams layoutParams = contain.getChildAt(i).getLayoutParams();
                    if(layoutParams.height == originHeight){
                      //  ViewCompat.setAlpha(child,1);
                    }else if(layoutParams.height > originHeight){
                        ViewCompat.setAlpha(child,target.getScrollY()*1.0f/(maxHeight-originHeight));
                    }
                    break;
                }
            }
        } else if (target.getScrollY() == 0) {
            //  ViewCompat.setAlpha(child,0);

            ViewCompat.setAlpha(child,1-unUseY*1.0f/(maxHeight-originHeight));
        }*/
        if (target.getScrollY() <= maxHeight) {
            //改变透明度
            ViewCompat.setAlpha(child,target.getScrollY()*1.0f/maxHeight);

        } else if (target.getScrollY() == 0) {
            ViewCompat.setAlpha(child,0);
        }
    }
}
