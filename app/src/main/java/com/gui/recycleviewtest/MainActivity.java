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
    private static final String TAG = "MainActivity";

    @BindView(R.id.id_recycle_view)
    RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private MainAdapter mainAdapter;

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
        mainAdapter = new MainAdapter(datas);
        mainAdapter.setOnItemClickListener(R.id.root, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Log.d(TAG,"root clicked..." + position);
            }
        });
        mainAdapter.setOnItemClickListener(R.id.icon, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Log.d(TAG,"icon clicked..." + position);
            }
        });

        recyclerView.setAdapter(mainAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainAdapter.destroyAdapter();
    }

    private static class MainAdapter extends LGRecycleViewAdapter<String> {

        public MainAdapter(List<String> dataList) {
            super(dataList);
        }

        @Override
        public int getLayoutId(int viewType) {
            if(viewType == 1)
                return R.layout.item_view_main1;
            return R.layout.item_view_main2;
        }

        //支持不同viewType视图
        @Override
        public int getItemViewType(int position) {
            String model = getItem(position);
            if(position % 2 == 0)
                return  1;
            return  2;
//            return super.getItemViewType(position);
        }

        @Override
        public void convert(LGViewHolder holder, String s, final int position) {
            TextView textView = (TextView) holder.getView(R.id.id_text);
            textView.setText(s);
        }
    }
}
