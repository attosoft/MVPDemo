package com.attosoft.mvpdemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.attosoft.mvpdemo.R;
import com.attosoft.mvpdemo.ui.adapter.contact.ContactAdapter;
import com.attosoft.mvpdemo.util.dragger.DemoApplication;
import com.attosoft.mvpdemo.util.dragger.GitHubService;
import com.attosoft.mvpdemo.util.dragger.ListAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by andy on 2016/4/13.
 */
public class EmailListActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.btn_insert_contacts)
    Button mBtnInsert;
    @Bind(R.id.recyclerView)
    RecyclerView mList;
    @Bind(R.id.empty_view)
    View mEmptyView;
    private ListAdapter mContactAdapter;

    @Inject
    GitHubService mGitHubService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emaillist);
        ButterKnife.bind(this);
        DemoApplication.component().inject(this);
        mBtnInsert.setOnClickListener(this);
        mEmptyView.setVisibility(View.GONE);

        mContactAdapter = new ListAdapter();
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(mContactAdapter);
        mList.setItemAnimator(new DefaultItemAnimator());
        loadData(mContactAdapter);
    }

    // 加载数据
    private void loadData(ListAdapter adapter) {
        mGitHubService.getRepoData("SpikeKing")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapter::setRepos);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert_contacts:
                break;
            default:
                break;
        }
    }
}
