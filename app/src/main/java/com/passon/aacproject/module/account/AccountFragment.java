package com.passon.aacproject.module.account;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.passon.aacproject.R;
import com.passon.aacproject.base.LazyBaseFragment;
import com.passon.aacproject.entity.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends LazyBaseFragment {


    @BindView(R.id.tv_account)
    TextView mTvAccount;
    Unbinder unbinder;
    private AccountViewModel mAccountViewModel;
    private boolean mFirst = true;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance() {
        Bundle args = new Bundle();
        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.account_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void lazyLoad() {
        mAccountViewModel.init();

        if (mFirst) {
            mAccountViewModel.startInterval();
            mFirst = false;
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LifecycleOwner lifecycleOwner = getViewLifecycleOwner();
        mAccountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
        mAccountViewModel.user().observe(lifecycleOwner, this::user);
        mAccountViewModel.cnt().observe(lifecycleOwner, this::cnt);
    }

    private void cnt(Long aLong) {
        mTvAccount.setText(aLong + "");
    }

    private void user(User user) {
        mTvAccount.setText(user.name + user.age);
    }

}
