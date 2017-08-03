package com.mahao.materialdesigndemo;


import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class BossFragment extends Fragment {


    @BindView(R.id.img_boss)
    ImageView imgBoss;

    @BindView(R.id.btn_date)
    Button btnDate;

    @BindView(R.id.txt_two)
    TextView txtView;


    public BossFragment() {
    }

    public static BossFragment newBossFragment(String args){

        BossFragment fragment = new BossFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",args);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_boss, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick({R.id.img_boss,R.id.btn_date,R.id.txt_two,R.id.txt_one})
    public void userClick(View v) {

        switch (v.getId()){

            case R.id.btn_date:

                Button btn = (Button) v;
                //The View will be clipped to the animating circle
                Animator circularReveal = ViewAnimationUtils.createCircularReveal(v, btn.getWidth() / 2, btn.getHeight() / 2, 0, btn.getWidth() / 2);
                circularReveal.setDuration(1000);
                circularReveal.start();
                break;

            case R.id.img_boss:

                Intent intents = new Intent(getActivity(),ThridActivity.class);

                Pair pair = new Pair(imgBoss,getString(R.string.app_name));
              //  Pair pair1 = new Pair(imgBoss,"lisi");

                Log.i("mahao","dianjil");
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pair);
                startActivity(intents,optionsCompat.toBundle());
                break;

            case R.id.txt_two:
                Intent intentss = new Intent(getActivity(),ThridActivity.class);
                startActivity(intentss);
                break;

            case R.id.txt_one:
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;

        }
    }
}
