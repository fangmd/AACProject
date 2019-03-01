package com.passon.aacproject.module.investment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.passon.aacproject.R;
import com.passon.aacproject.base.LazyBaseFragment;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvestFragment extends LazyBaseFragment {


    public InvestFragment() {
        // Required empty public constructor
    }

    public static InvestFragment newInstance() {
        Bundle args = new Bundle();
        InvestFragment fragment = new InvestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.invest_fragment, container, false);
    }

    @Override
    public void lazyLoad() {


    }

}
