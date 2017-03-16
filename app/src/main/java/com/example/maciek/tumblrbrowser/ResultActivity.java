package com.example.maciek.tumblrbrowser;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusAppCompatActivity;

@RequiresPresenter(ResultPresenter.class)
public class ResultActivity extends NucleusAppCompatActivity<ResultPresenter> implements OnItemClickActionListener {

    public static final String NAME_SEARCH = "name_search";
    ResultAdapter adapter;
    Post post;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        String name = getIntent().getStringExtra(NAME_SEARCH);

        adapter = new ResultAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickActionListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(name);

        dataLoading(name);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dataLoading(name);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void dataLoading(String name) {
        getPresenter().getDataAsync(name);
    }

    public static Intent createIntent(Context context, String name) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(NAME_SEARCH, name);
        return intent;
    }

    public void setPostDetailOnMainThread(Post post) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setPostList(Arrays.asList(post));
            }
        });
    }

    @Override
    public void onItemClick(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }


    //!!!!!!!!!!!!!!!!!!!!
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}