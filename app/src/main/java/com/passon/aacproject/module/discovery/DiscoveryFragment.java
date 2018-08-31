package com.passon.aacproject.module.discovery;


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
public class DiscoveryFragment extends LazyBaseFragment {


    public DiscoveryFragment() {
        // Required empty public constructor
    }

    public static DiscoveryFragment newInstance() {
        Bundle args = new Bundle();
        DiscoveryFragment fragment = new DiscoveryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.discovery_fragment, container, false);
    }

    @Override
    public void lazyLoad() {

    }

}
