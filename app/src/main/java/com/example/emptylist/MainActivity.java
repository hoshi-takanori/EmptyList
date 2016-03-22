package com.example.emptylist;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshEmptyLayout mSwipeRefresh;
    private ListView mListView;
    private View mEmptyView;

    private MyListAdapter mListAdapter;
    private List<String> mListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefresh = (SwipeRefreshEmptyLayout) findViewById(R.id.swipe_refresh);
        mListView = (ListView) findViewById(R.id.list_view);
        mEmptyView = findViewById(R.id.empty_view);

        mListView.addHeaderView(getLayoutInflater().inflate(R.layout.list_header, mListView, false));
        mListView.setEmptyView(mEmptyView);
        mListAdapter = new MyListAdapter();
        mListView.setAdapter(mListAdapter);

        mSwipeRefresh.setViews(mListView, mEmptyView);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                genWords((int) (Math.random() * 26) + 1);
                mListAdapter.notifyDataSetChanged();
                mSwipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, Menu.NONE, "Clear");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            mListData = null;
            mListAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void genWords(int max) {
        String[] words = {
                "apple", "banana", "cat", "dinosaur", "elephant", "fish", "giraffe",
                "hotdog", "ice cream", "jump", "kite", "lion", "monkey", "nail polish",
                "owl", "pear", "question", "rocket", "skunk", "turtle", "umbrella",
                "vacuum", "watch", "x-ray", "yellow", "zebra"
        };
        mListData = new ArrayList<>(Arrays.asList(words));
        Collections.shuffle(mListData);
        mListData = mListData.subList(0, max);
    }

    public class MyListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mListData != null ? mListData.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
            ((TextView) view).setText((String) getItem(position));
            return view;
        }
    }
}
