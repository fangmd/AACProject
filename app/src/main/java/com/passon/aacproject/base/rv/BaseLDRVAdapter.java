package com.passon.aacproject.base.rv;

import android.content.Context;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by double on 2016/11/1.
 */

public abstract class BaseLDRVAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<T> mDatas;
    protected OnItemClickListener mOnItemClickListener;
    protected Context mContext;

    // header
    public static final int TYPE_HEADER = 99;
    public static final int TYPE_NORMAL = 88;
    public static final int TYPE_FOOTER = 77;
    private View mHeaderView;
    private View mFooterView;

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount());
    }

    public View getFooterView() {
        return mFooterView;
    }

    public View getHeaderView() {
        return mHeaderView;
    }
    //

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == getItemCount() - 1 && mFooterView != null) return TYPE_FOOTER;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }


    public BaseLDRVAdapter() {
        mDatas = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }

        if (mHeaderView != null && viewType == TYPE_HEADER) return new Holder(mHeaderView);
        if (mFooterView != null && viewType == TYPE_FOOTER) return new Holder(mFooterView);

        return onCreateVH(parent, viewType);
    }

    public abstract RecyclerView.ViewHolder onCreateVH(ViewGroup parent, int viewType);


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;
        if (getItemViewType(position) == TYPE_FOOTER) return;

        final int pos = getRealPosition(holder);
        T t = mDatas.get(pos);
        onBind(holder, position, t);

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(holder, position, t);
                    }
                }
            });
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        if (mHeaderView != null) {
            position--;
        }
        return position;
    }

    @Override
    public int getItemCount() {
        int ret = mDatas.size();
        if (mHeaderView != null) {
            ret++;
        }
        if (mFooterView != null) {
            ret++;
        }
        return ret;
    }


    public abstract void onBind(RecyclerView.ViewHolder viewHolder, int position, T data);

    //---data relate--
    public void setData(List<T> newData) {
        if (mDatas != null) {
            ObjDiffCallback objDiffCallback = new ObjDiffCallback(mDatas, newData);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(objDiffCallback);

            mDatas.clear();
            mDatas.addAll(newData);
            diffResult.dispatchUpdatesTo(this);
        } else {
            // first initialization
            mDatas = newData;
        }
    }

    public List<T> getDatas() {
        return mDatas;
    }
    //---


    public void setOnItemClickListener(OnItemClickListener li) {
        mOnItemClickListener = li;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(RecyclerView.ViewHolder holder, int position, T data);
    }

    // attacth recyclerview
    protected RecyclerView mRecyclerView;

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(holder.getLayoutPosition() == 0);
        }
    }

}
