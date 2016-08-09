package com.gui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by guizhigang on 16/8/8.
 */
public class LGViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;//缓存itemView内部的子View
    private Context context;

    public LGViewHolder(Context context, View itemView, ViewGroup parent) {
        super(itemView);
        this.context = context;
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    public static LGViewHolder getViewHolder(ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new LGViewHolder(parent.getContext(), itemView, parent);
    }

    public View getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }
}
