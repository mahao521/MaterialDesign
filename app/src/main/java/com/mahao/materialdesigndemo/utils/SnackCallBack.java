package com.mahao.materialdesigndemo.utils;

import android.support.design.widget.Snackbar;

/**
 * Created by Penghy on 2017/7/14.
 */

public class SnackCallBack extends Snackbar.Callback{

    //已经消失之后
    @Override
    public void onDismissed(Snackbar transientBottomBar, int event) {
       // super.onDismissed(transientBottomBar, event);


    }

    @Override
    public void onShown(Snackbar sb) {
       // super.onShown(sb);
/*
        sb.getView().setBackgroundColor(Color.RED);
        Toast.makeText(sb.getContext(),"你快点我啊，不然我消失了哦",Toast.LENGTH_SHORT).show();*/
    }

}
