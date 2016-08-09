package com.gui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.gui.recycleviewtest.R;

import java.util.List;

/**
 * Created by guizhigang on 16/8/8.
 */
public abstract class LGRecycleViewAdapter<T> extends RecyclerView.Adapter<LGViewHolder> {
    private final String TAG = "LGRecycleViewAdapter";

    private SparseArray<ItemClickListener> onClickListeners;

    private List<T> dataList;

    public interface ItemClickListener {
        void onItemClicked(View view,int position);
    }

    public LGRecycleViewAdapter(List<T> dataList) {
        this.dataList = dataList;
        onClickListeners = new SparseArray<>();
    }

    public void setOnItemClickListener(int viewId,ItemClickListener listener) {
        ItemClickListener listener_ = onClickListeners.get(viewId);
        if(listener_ == null){
            onClickListeners.put(viewId,listener);
        }
    }

    public abstract int getLayoutId(int viewType);

    //更新itemView视图
    public abstract void convert(LGViewHolder holder, T t, int position);

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
        convert(holder, itemModel, position);

        for (int i = 0; i < onClickListeners.size(); ++i){
            int id = onClickListeners.keyAt(i);
            View view = holder.getView(id);
            if(view == null)
                continue;
            final ItemClickListener listener = onClickListeners.get(id);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onItemClicked(v,position);
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
    }

    public void destroyAdapter(){
        if(onClickListeners != null)
            onClickListeners.clear();
        onClickListeners = null;

        if(dataList != null)
            dataList.clear();
        dataList = null;
    }
}