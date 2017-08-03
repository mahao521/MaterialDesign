package com.mahao.materialdesigndemo.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.NestedScrollingParent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.mahao.materialdesigndemo.R;

import java.lang.reflect.Constructor;

/**
 * Created by Penghy on 2017/7/27.
 */

/**
 *   执行过程，1 ： toolbarbehavior.onNestedScroll();
 *           2 :  toolbarbehavior.onNestedScroll() -- ContainerBehavior.onNestedScroll()
 *           3 :  ContainerBehavior.onNestedScroll()-----viewGrop.onNestedScroll();
 *           4 :  NetedScrollerView --ontouMove--dispatchNestedPreScroll------((NestedScrollingParent) parent).onNestedPreScroll();
 *           5 :  NetedScrollerView --ontouMove--dispatchNestedScroll--- ((NestedScrollingParent) parent).onNestedScroll
 *           6 :  NestedScrollView-----重写onNestedScroll();
 *           7 :  viewGrop实现ViewParent(); ---重写ContainerNetedView（）
 *           8 :  判断NestedScrollView 距离屏幕顶部的位置，如果未滑动顶部---调用ViewGroup---NestedScrollView；
 *                                                     如果滑动到顶部---调用NestedScrollView---NestedScrollView；
 *
 *
 *           ContainerNetedView：是ViewGroup的子类， 重写了onNestedScroll() ；
 *           ContainerNetedView又重新实现NestedScrollingParent()，为了强转，实现多态调用，调用子类的方法。

 */

public class ContainerNetedView extends RelativeLayout implements NestedScrollingParent, ViewTreeObserver.OnGlobalLayoutListener {

    private float mStartX;
    private float mStartY;

    public ContainerNetedView(Context context) {
        super(context);
    }

    public ContainerNetedView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContainerNetedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     *    执行顺序 setcontentView----onlayoutinflateFinish-----(onSizeChange)btn.setWith();
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        for(int i = 0; i < getChildCount(); i++){

            View childAt = getChildAt(i);
            ContainerParams  layoutParams = (ContainerParams) childAt.getLayoutParams();
            ChildBehavior behavior = layoutParams.getBehavior();
            if(behavior != null){
                behavior.onSizeChanged(this,childAt,w,h,oldw,oldh);
            }
        }
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
         getViewTreeObserver().addOnGlobalLayoutListener(this);
    }


    @Override
    public void onGlobalLayout() {

        for(int i = 0; i < getChildCount(); i++){

            View childAt = getChildAt(i);
            ContainerParams layoutParams = (ContainerParams) childAt.getLayoutParams();
            ChildBehavior behavior = layoutParams.getBehavior();
            if(behavior != null){

                behavior.onLayoutFinish(this,childAt);
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //记录按下的起始位置
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                 mStartX = event.getRawX();
                 mStartY = event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:

                dealWith(event);
                break;

            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     *   处理滑动事件
     * @param event
     */
    private void dealWith(MotionEvent event) {

        float rawX = event.getRawX();
        float rawY = event.getRawY();

        for(int i = 0; i < getChildCount(); i++){

            View childAt = getChildAt(i);
            ContainerParams params = (ContainerParams) childAt.getLayoutParams();
            ChildBehavior behavior = params.getBehavior();
            if(behavior != null){
                behavior.onTouchMove(this,childAt,event,rawX,rawY,mStartX,mStartY);
            }
        }
    }


    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {

    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {

    }

    @Override
    public void onStopNestedScroll(View child) {

    }

    /**
     *   target是NetedscrollView
     * @param target The descendent view controlling the nested scroll
     * @param dxConsumed Horizontal scroll distance in pixels already consumed by target
     * @param dyConsumed Vertical scroll distance in pixels already consumed by target
     * @param dxUnconsumed Horizontal scroll distance in pixels not consumed by target
     * @param dyUnconsumed Vertical scroll distance in pixels not consumed by target
     */
    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
       // super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

        for(int i = 0; i < getChildCount(); i++){

            View childAt = getChildAt(i);
            ContainerParams layoutParams = (ContainerParams) childAt.getLayoutParams();
         //   Log.i("mahao",layoutParams.getBehavior()+".................../");
            ChildBehavior behavior = layoutParams.getBehavior();
            if(behavior != null){
                behavior.onNestedScroll(this,childAt,target,dxConsumed,dyConsumed,dxUnconsumed,dyUnconsumed);
            }
        }
    }

/*    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ContainerParams(ContainerParams.WRAP_CONTENT,ContainerParams.WRAP_CONTENT);
    }*/

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        return new ContainerParams(lp);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new ContainerParams(getContext(),attrs);
    }

    public class ContainerParams extends RelativeLayout.LayoutParams{

        private ChildBehavior mBehavior;

        public ChildBehavior getBehavior(){

            return mBehavior;
        }

        public ContainerParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            //解析xml.
            TypedArray typedArray = c.obtainStyledAttributes(attrs, R.styleable.child_behavior);
            String itemBehavior = typedArray.getString(R.styleable.child_behavior_item_behavior);
         //   Log.i("mahao","name--------" + itemBehavior);
            if (TextUtils.isEmpty(itemBehavior)) {
                return ;
            }
            try {

       /*         final Class clazz = Class.forName(name, true,
                        context.getClassLoader());
                Constructor c = clazz.getConstructor(new Class[]{Context.class,AttributeSet.class});
                c.setAccessible(true);*/


                Class classh = Class.forName(itemBehavior, true, c.getClassLoader());
                Constructor constructor = classh.getConstructor(new Class[]{Context.class,AttributeSet.class});
                constructor.setAccessible(true);
                mBehavior = (ChildBehavior) constructor.newInstance(c, attrs);
             //   Log.i("mahao",mBehavior+"..............");

            } catch (Exception e) {
                e.printStackTrace();
            }
            typedArray.recycle();
        }

        public ContainerParams(int w, int h) {
            super(w, h);
        }

        public ContainerParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }

}















