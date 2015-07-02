package com.kido.board.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kido.board.R;

public class FragmentNewAd extends Fragment {
    private String mParamId;



    public static FragmentNewAd newInstance(long param) {
        FragmentNewAd fragment = new FragmentNewAd();
        Bundle args = new Bundle();
        args.putLong("idAd", param);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentNewAd() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamId = getArguments().getString("idAd");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_new_ad, container, false);
    }
}
