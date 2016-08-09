package com.gui.recycleviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gui.adapter.LGRecycleViewAdapter;
import com.gui.adapter.LGViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    @BindView(R.id.id_recycle_view)
    RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 20; ++i) {
            datas.add("item:" + (i + 1));
        }
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        MainAdapter mainAdapter = new MainAdapter(datas);
        mainAdapter.setOnItemClickListener(new LGRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d(TAG,"click view..." + position);
            }
        });
        recyclerView.setAdapter(mainAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private static class MainAdapter extends LGRecycleViewAdapter<String> {

        public MainAdapter(List<String> dataList) {
            super(dataList);
        }

        @Override
        public int getClickViewId(int viewType) {
            return R.id.icon;
        }

        @Override
        public int getLayoutId(int viewType) {
            return R.layout.item_view_main;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public void convert(LGViewHolder holder, String s) {
            TextView textView = (TextView) holder.getView(R.id.id_text);
            textView.setText(s);
        }
    }
}
