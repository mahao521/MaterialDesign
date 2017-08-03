package com.mahao.materialdesigndemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;


public class SecondActivity extends AppCompatActivity {

    private View  mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
        getWindow().setEnterTransition(explode);


        setContentView(R.layout.activity_second);

        mImg = (ImageView) findViewById(R.id.img);

    }

    public void click(View view) {

        Intent intents = new Intent(SecondActivity.this,ThridActivity.class);
/*
      //  Pair pair = Pair.create(mImg,getString(R.string.app_name));
        Pair<View, String> img1 = Pair.create(mImg, getString(R.string.app_name));
        // Pair pair = new Pair(img,"mahao");
        //  Pair pair1 = new Pair(imgBoss,"lisi");
        Log.i("mahao","dianjil");
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(SecondActivity.this, img1);
        ActivityCompat.startActivity(this,intents,optionsCompat.toBundle());*/

        Pair<View, String> img1 = Pair.create(mImg, getString(R.string.app_name));
        //  Pair<View, String> img2 = Pair.create(imageView2, getString(R.string.app));
        ActivityOptionsCompat comapt= ActivityOptionsCompat.makeSceneTransitionAnimation(this, img1);
        //跳转
        ActivityCompat.startActivity(this,new Intent(this,ThridActivity.class),comapt.toBundle());


    }
}
