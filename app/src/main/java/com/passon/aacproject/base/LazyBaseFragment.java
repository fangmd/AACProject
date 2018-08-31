package com.passon.aacproject.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: Created by fangmingdong on -下午3:48
 * Description: LazyFragment 的基类
 */
public abstract class LazyBaseFragment extends BaseFragment {

    /**
     * View 初始化状态，true: 已经初始化, false: 没有初始化
     */
    protected boolean isViewInit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isViewInit = true;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void loadData() {
        if (isViewInit) {
            lazyLoad();
        }
    }

    public abstract void lazyLoad();

}
