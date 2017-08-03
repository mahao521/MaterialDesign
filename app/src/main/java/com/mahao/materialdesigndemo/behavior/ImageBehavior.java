package com.mahao.materialdesigndemo.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Penghy on 2017/7/19.
 */


public class ImageBehavior extends ChildBehavior {

   int maxHeight = 400;
   private int originHeight;

   /**
    * Default constructor for inflating Behaviors from layout. The Behavior will have
    * the opportunity to parse specially defined layout parameters. These parameters will
    * appear on the child view tag.
    *
    * @param context
    * @param attrs
    */
   public ImageBehavior(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   /**  布局初始化完成调用
    * @param parent
    * @param child
    */
   @Override
   public void onLayoutFinish(View parent, View child) {

      super.onLayoutFinish(parent, child);
      if(originHeight == 0){
         originHeight = child.getHeight();
      }
   }

   @Override
   public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
      return true;
   }

   @Override
   public void onNestedScroll(View coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

      // super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
      Log.i("mahao",target.getScrollY()+"...."+dyUnconsumed+"。。。"+dyConsumed);
      int scrollY = target.getScrollY();
      ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
      if(scrollY > 0 ){

         layoutParams.height -= Math.abs(dyConsumed);
         if(layoutParams.height <= originHeight){
            layoutParams.height = originHeight;
         }
         child.setLayoutParams(layoutParams);
      }else if(scrollY == 0){

         layoutParams.height += Math.abs(dyUnconsumed);
         if(layoutParams.height >= maxHeight){
            layoutParams.height = maxHeight;
         }
         child.setLayoutParams(layoutParams);
      }

   }

}
