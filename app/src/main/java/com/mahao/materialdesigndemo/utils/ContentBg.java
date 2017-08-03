package com.mahao.materialdesigndemo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import com.mahao.materialdesigndemo.R;

/**
 * Created by Penghy on 2017/7/25.
 */
public class ContentBg extends View {

    private Paint mPaint;
    private int mContentWidth;
    private int mContentHeight;

    private int defauleWidth;
    private int defaultHeight;
    private Bitmap mBitmap;
    private Paint mBitmapPaint;


    public ContentBg(Context context) {
        this(context,null);
    }

    public ContentBg(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ContentBg(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initData(context,attrs);
        initBitmap(context);
    }

    private void initBitmap(Context context) {

        Drawable drawable = context.getResources().getDrawable(R.mipmap.lecture_vedio);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        mBitmap = bitmapDrawable.getBitmap();

        mContentWidth = 0;
        mContentHeight = 0;

        WindowManager  windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        defauleWidth = point.x;
        defaultHeight = point.x / point.y * point.x;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if(widthMode == MeasureSpec.EXACTLY || heightMode == MeasureSpec.EXACTLY){

            mContentWidth = widthSize;
            mContentHeight = heightSize;

        }else {
            mContentWidth = defauleWidth;
            mContentHeight = defaultHeight;
        }
    }

    private void initData(Context context, AttributeSet attrs) {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        //防抖动
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#33a19e9e"));

        mBitmapPaint = new Paint();
        mBitmapPaint.setFilterBitmap(true);
        mPaint.setColor(Color.parseColor("#00a19e9e"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //This behaves the same as save(), but in addition it allocates and
        // redirects drawing to an offscreen bitmap
        int saved = canvas.saveLayer(null,null,Canvas.ALL_SAVE_FLAG);

        canvas.drawRect(0,0,mContentWidth,mContentHeight,mPaint);
        canvas.drawARGB(120, 0, 0, 0);

        mBitmapPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mBitmap,mContentWidth/2 - mBitmap.getWidth()/2,
                mContentHeight/2-mBitmap.getHeight()/2,mBitmapPaint);
        mBitmapPaint.setXfermode(null);

        /**
         *    now the canvas is back in the same state it was before the initial
         *    call to save().
         */
        canvas.restoreToCount(saved);

    }
}
