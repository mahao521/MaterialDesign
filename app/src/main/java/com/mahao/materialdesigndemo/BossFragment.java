package com.mahao.materialdesigndemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class BossFragment extends Fragment {


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
        return inflater.inflate(R.layout.fragment_boss, container, false);
    }

}
