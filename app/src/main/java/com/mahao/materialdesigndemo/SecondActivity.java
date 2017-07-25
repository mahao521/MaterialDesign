package com.mahao.materialdesigndemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

public class SecondActivity extends MainActivity {

    private DrawerLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mLayout = (DrawerLayout) findViewById(R.id.layout_draw);
    }
}
