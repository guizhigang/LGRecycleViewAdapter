package com.gui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by guizhigang on 16/8/8.
 */
public abstract class LGRecycleViewAdapter<T> extends RecyclerView.Adapter<LGViewHolder> {
    private final String TAG = "LGRecycleViewAdapter";

    public interface OnItemClickListener{
        void onClick(View view,int position);
    }

    private List<T> dataList;
    private OnItemClickListener onItemClickListener;

    public LGRecycleViewAdapter(List<T> dataList) {
        this.dataList = dataList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public abstract int getLayoutId(int viewType);

    public abstract int getClickViewId(int viewType);

    //更新itemView视图,绑定事件等操作
    public abstract void convert(LGViewHolder holder, T t);

    public T getItem(final int position){
        if(dataList == null)
            return null;
        return dataList.get(position);
    }

    @Override
    public LGViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = getLayoutId(viewType);
        LGViewHolder viewHolder = LGViewHolder.getViewHolder(parent, layoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LGViewHolder holder, final int position) {
        T itemModel = dataList.get(position);
        convert(holder, itemModel);
        int viewType = getItemViewType(position);
        int clickViewId = getClickViewId(viewType);
        final View clickView = holder.getView(clickViewId);
        if(clickView != null){
            clickView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.onClick(clickView,position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (dataList == null)
            return 0;
        return dataList.size();
    }

    @Override
    public void onViewRecycled(LGViewHolder holder) {
        super.onViewRecycled(holder);
        Log.d(TAG,"onViewRecycled");
    }
}