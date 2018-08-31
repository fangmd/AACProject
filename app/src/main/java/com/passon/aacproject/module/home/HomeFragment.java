package com.passon.aacproject.module.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.passon.aacproject.R;
import com.passon.aacproject.base.LazyBaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends LazyBaseFragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void lazyLoad() {

    }

}
