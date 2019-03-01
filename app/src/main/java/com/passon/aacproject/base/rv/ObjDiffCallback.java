package com.passon.aacproject.base.rv;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Author: Created by fangmingdong on 2018/12/28-2:41 PM
 * Description:
 */
public class ObjDiffCallback<T> extends DiffUtil.Callback {

    private final List<T> oldPosts, newPosts;

    public ObjDiffCallback(List<T> oldPosts, List<T> newPosts) {
        this.oldPosts = oldPosts;
        this.newPosts = newPosts;
    }

    @Override
    public int getOldListSize() {
        return oldPosts.size();
    }

    @Override
    public int getNewListSize() {
        return newPosts.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldPosts.get(oldItemPosition).equals(newPosts.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldPosts.get(oldItemPosition).equals(newPosts.get(newItemPosition));
    }

}
