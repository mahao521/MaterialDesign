package com.mahao.materialdesigndemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mahao.materialdesigndemo.adapter.FragmentAdapter;
import com.mahao.materialdesigndemo.topsnack.TopSnackBar;
import com.mahao.materialdesigndemo.utils.HomeViewPager;
import com.mahao.materialdesigndemo.utils.SnackCallBack;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.layout_draw)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.layout_content)
    LinearLayout mContentLayout;

    @BindView(R.id.layout_tab)
     TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    HomeViewPager mViewPager;

    @BindView(R.id.design_navigation_view)
    NavigationView mGationView;

    private  Snackbar snackbar = null;
    private  Snackbar snackbar1 = null;
    private  Snackbar snackBar2 = null;

    private TopSnackBar mTopSnackBar = null;

    private  float downX = 0,downY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //esource id of a background drawable to draw behind the status bar
        mDrawerLayout.setStatusBarBackground(R.color.colorAccent);
        // Sets the title of the drawer with the given gravity.
        mDrawerLayout.setAccessibilityDelegate(new View.AccessibilityDelegate());
        mDrawerLayout.setDrawerTitle(Gravity.START,"我在北京奋斗");
        mDrawerLayout.setDrawerShadow(R.drawable.shape_love,Gravity.START);

        //去掉menu_item的灰色background;
        mGationView.setItemIconTintList(null);

      /*  //修改从网络获取的icon
        Menu menu = mGationView.getMenu();
        for(int i = 0; i <menu.size(); i++){

            MenuItem item = menu.getItem(i);
            ImageView img = new ImageView(this);
            int width  = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,70,getResources().getDisplayMetrics());
            int height  = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,70,getResources().getDisplayMetrics());
            img.setMaxWidth(width);
            img.setMaxHeight(height);

            Picasso.with(this).load(getImgList().get(i))
                    .placeholder(R.mipmap.ic_launcher_round)
                    .centerCrop()
                    .resize(90,90).into(img);
            BitmapDrawable  drawable = (BitmapDrawable) img.getDrawable();
            item.setIcon(drawable);*/
       // }


        //设置item的点击监听
        mGationView.setNavigationItemSelectedListener(this);
        mGationView.getMenu().getItem(0).setChecked(true);

        //设置viewpager
        BossFragment boss = BossFragment.newBossFragment("老板");
        SuccessFragment sucuess = SuccessFragment.newBossFragment("成功");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(boss);
        fragments.add(sucuess);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        indictorLength();
        View headerView = mGationView.getHeaderView(0);
        final ImageView imgHeader = (ImageView) headerView.findViewById(R.id.img_header);
        imgHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                Intent intents = new Intent(MainActivity.this,ThridActivity.class);

                Pair pair = new Pair(imgHeader,"mahao");
                Pair pair1 = new Pair(imgHeader,"lisi");

                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, pair, pair1);
                startActivity(intents,optionsCompat.toBundle());
                mDrawerLayout.closeDrawer(GravityCompat.START);*/
            }
        });
    }

    /**
     *   设置indictor的长度
     */
    private void indictorLength() {
        Class<? extends TabLayout> aClass = mTabLayout.getClass();
        try {
            Field mTabStrip = aClass.getDeclaredField("mTabStrip");
            mTabStrip.setAccessible(true);
            LinearLayout layout = (LinearLayout) mTabStrip.get(mTabLayout);
            for(int i = 0; i < layout.getChildCount(); i++){

                final View childAt = layout.getChildAt(i);
                final ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();

                TextView txt = new TextView(this);
                final float sizeWidth = txt.getPaint().measureText(mTabLayout.getTabAt(i).getText().toString());

              /*  childAt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {

                        childAt.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        int width = childAt.getMeasuredWidth();
                        Log.i("mahao",width +"..." + sizeWidth);
                        if(width > sizeWidth){

                            layoutParams.width = (int) sizeWidth;
                        }
                        childAt.setLayoutParams(layoutParams);
                        childAt.invalidate();
                    }
                });*/
                layoutParams.width = (int) sizeWidth;
                layoutParams.leftMargin= 30;
                layoutParams.rightMargin = 30;
                childAt.setLayoutParams(layoutParams);
                childAt.invalidate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {

        Log.i("mahao","......a");
        super.onOptionsMenuClosed(menu);
    }


    @Override
    public void onContextMenuClosed(Menu menu) {
        Log.i("mahao","......b");
        super.onContextMenuClosed(menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Log.i("mahao","......c");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        Log.i("mahao","......d");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    /* *//**
     *  Cancel child touches
     *//*
    void cancelChildViewTouch() {

        if (!mChildrenCanceledTouch) {
            final long now = SystemClock.uptimeMillis();
            final MotionEvent cancelEvent = MotionEvent.obtain(now, now,
                    MotionEvent.ACTION_CANCEL, 0.0f, 0.0f, 0);
            final int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                getChildAt(i).dispatchTouchEvent(cancelEvent);
            }
            cancelEvent.recycle();
            mChildrenCanceledTouch = true;
        }
    }*/


    protected List<String> getImgList(){

        List<String> list = new ArrayList<>();
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489308136466&di=d3df0660b6cad8055ced40f081ff08ef&imgtype=0&src=http%3A%2F%2Fi5.3conline.com%2Fimages%2Fpiclib%2F201201%2F04%2Fbatch%2F1%2F123590%2F1325657824467t6txnk6u78.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489308136462&di=7a3427a1a7e1f529c7d95da7d6f405ee&imgtype=0&src=http%3A%2F%2Fimage91.360doc.com%2FDownloadImg%2F2015%2F12%2F0813%2F62615249_1.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489308469766&di=8568983d815a5160cded71746fc7a9ca&imgtype=0&src=http%3A%2F%2Fimg.tupianzj.com%2Fuploads%2Fallimg%2F160327%2F16-16032G21928.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489309860698&di=dc7644f463453eaa0850b5ef0aa38b25&imgtype=0&src=http%3A%2F%2Fimage95.360doc.com%2FDownloadImg%2F2016%2F02%2F2622%2F66721351_2.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1489299944&di=951c3ae0db72084b0fbfb9d8c51eb241&src=http://kantongxiao.com/upload/aHR0cDovL2cxLnlraW1nLmNvbS8wNTExNkY0OTUzRDJCMTdGNjcwODRDNzBCMzI2MjhGOA==.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1489300090&di=736e965dbc8651b962dd87c0a717adb1&src=http://imgchr.com/images/343691.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489310297792&di=634fe961ee23769e83937f0b48170772&imgtype=0&src=http%3A%2F%2Fimg.qqai.net%2Fuploads%2Fi_0_4035598123x1084105545_21.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489310726121&di=6e965108e646e728c2b0cd42a3bcba72&imgtype=0&src=http%3A%2F%2Fi1.17173.itc.cn%2F2014%2Fuploads%2Fvfz01%2Fvlog%2Fimages%2Fvideo%2F20140415%2F12727166_0.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489310802270&di=b6efe180acb9fbd6ac94c7f3d943241f&imgtype=0&src=http%3A%2F%2Fvi3.ku6img.com%2Fdata1%2Fp12%2Fku6video%2F2014%2F1%2F6%2F13%2F1394313850193_93233402_93233402%2F106.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489310844360&di=4fd247ce56a810672e8a8f49c8c53c00&imgtype=0&src=http%3A%2F%2Fimg.sc115.com%2Fhb%2Fyl2%2F19%2F881606270133806.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489310844359&di=abfc2266288fd3600d18bfd53536a99d&imgtype=0&src=http%3A%2F%2Fi.dimg.cc%2Fdc%2Fd8%2Fa3%2F56%2Ff8%2Fc9%2F66%2Fd7%2Fa0%2F8d%2F98%2Fdb%2F84%2F8d%2F71%2F26.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489310844357&di=5b232c6e28d488a493505e18e97613c3&imgtype=0&src=http%3A%2F%2Fimg0.pconline.com.cn%2Fpconline%2F1609%2F22%2F8394940_19_thumb.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489311045886&di=a4d52e143d533288a95221a0b972ab7b&imgtype=0&src=http%3A%2F%2Fdimg05.c-ctrip.com%2Fimages%2Ftg%2F076%2F253%2F885%2F59af24cce33e46748111ca3b682909ea.jpg");
        list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=970337886,1355285319&fm=23&gp=0.jpg");
        return list;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId){

            case R.id.menu_one:

                snackbar = Snackbar.make(mContentLayout, "马豪，加油哦！", Snackbar.LENGTH_SHORT)
                        .setAction("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.setText("已经老了啊");
                            }
                        });
                snackbar.show();
                break;

            case R.id.menu_two:

                snackbar1 = Snackbar.make(mContentLayout,"1234",Snackbar.LENGTH_SHORT)
                        .setAction("徐小凤",new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {

                                snackbar1.dismiss();
                            }
                        });
                snackbar1.addCallback(new SnackCallBack());
                snackbar1.show();
                break;

            case R.id.menu_three:

                snackBar2 = Snackbar.make(mContentLayout,"1234",Snackbar.LENGTH_SHORT);
                buildSnackBar(snackBar2);
                snackBar2.show();
                break;

            case R.id.menu_four:

                mTopSnackBar = TopSnackBar.make(mContentLayout,"1234",TopSnackBar.LENGTH_SHORT);
                buildSnackBar(mTopSnackBar);
                mTopSnackBar.show();
                break;

            case R.id.menu_five:

                break;

            case R.id.menu_six:


                break;

            case R.id.menu_seven:

                Intent intent = new Intent(this,SecondActivity.class);
                startActivity(intent);
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }


    /**
     * @param snackbar  设置自己的snackBar--View
     */
    public Snackbar buildSnackBar(Snackbar snackbar){

        View view = snackbar.getView();
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) view;
        snackView(layout);
        return snackbar;
    }

    /**
     * @param snackbar  设置自己的snackBar--View
     */
    public TopSnackBar buildSnackBar(TopSnackBar snackbar){

        View view = snackbar.getView();
        TopSnackBar.SnackbarLayout layout = (TopSnackBar.SnackbarLayout) view;
        snackView(layout);
        return snackbar;
    }

    /**
     *   设置当前显示view
     * @param layout
     */
    private void snackView(FrameLayout layout) {
        layout.setPadding(0,0,0,0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.gravity= Gravity.TOP;
        layout.setLayoutParams(layoutParams);
        View myView = LayoutInflater.from(this).inflate(R.layout.layout_show_snack,null);
        TextView txtView = (TextView) myView.findViewById(R.id.txt_show_snack);
        txtView.setText("这是一条提示消息哦！");
        layout.addView(myView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.i("mahao","event");
        if(!mViewPager.onTouchEvent(event)){

          /*  mDrawerLayout.openDrawer(Gravity.START);
            mDrawerLayout.onTouchEvent();
            mDrawerLayout.requestDisallowInterceptTouchEvent(true);
            mDrawerLayout.dispatchTouchEvent(event);*/
            mDrawerLayout.openDrawer(Gravity.START);
            mDrawerLayout.isDrawerOpen(Gravity.START);
            mDrawerLayout.onTouchEvent(event);
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){

            case MotionEvent.ACTION_DOWN:

                downX = ev.getX();
                downY = ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:

                float moveX = ev.getX();
                float moveY = ev.getY();
                int touchSlop = getTouchSlop();
                if(moveX > touchSlop && Math.abs(moveX-downX) > Math.abs(moveY-downY)){

                    float isDirectX = moveX - downX;
                    if(isDirectX > 0 && mViewPager.getCurrentItem() == 0 ){ //向右滑动

                        mViewPager.setFlag(true);
                    }else if(isDirectX <0 && mViewPager.getCurrentItem() == 0 ){ //向左滑动

                        mViewPager.setFlag(false);
                    }
                }
                break;

            case MotionEvent.ACTION_UP:

                float currentX = ev.getX();
                float currentY = ev.getY();

                float isDirectX = currentX - downX;
                float isDirectY = currentY - downY;
                Log.i("mahao","currentX - downX" + isDirectX);
                if(Math.abs(isDirectX) > Math.abs(isDirectY)){ //保证水平移动

                    if(isDirectX > 0 && mViewPager.getCurrentItem() == 0 ){ //向右滑动

                        mViewPager.setFlag(true);
                    }else if(isDirectX <0 && mViewPager.getCurrentItem() == 0 ){ //向左滑动

                        mViewPager.setFlag(false);
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * Distance in pixels a touch can wander before we think the user is scrolling
     */
    public int getTouchSlop(){

        int touchSlop =  ViewConfiguration.get(this).getScaledTouchSlop();
        return touchSlop;
    }


}
