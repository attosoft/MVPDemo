package com.attosoft.mvpdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.attosoft.mvpdemo.R;
import com.attosoft.mvpdemo.abstractview.contact.IContactListView;
import com.attosoft.mvpdemo.data.entity.Contact;
import com.attosoft.mvpdemo.presenter.contact.IContactPresenter;
import com.attosoft.mvpdemo.presenter.contact.impl.ContactPresenter;
import com.attosoft.mvpdemo.ui.adapter.contact.ContactAdapter;
import com.attosoft.mvpdemo.ui.adapter.item.BaseContactItem;
import com.attosoft.mvpdemo.ui.adapter.item.ContactItem;
import com.attosoft.mvpdemo.ui.view.contact.ItemClickListener;

import java.util.List;

/**
 * Created by andy on 16/1/24.
 */
public class ContactActivity extends BaseActivity implements IContactListView{

    private Button btnInsertContacts;

    private RecyclerView mContactListView;

    private View mEmptyView;

    private ContactAdapter mContactAdapter;

    private IContactPresenter mContactPresenter;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_contact;
    }

    @Override
    protected void findViewByResourceId() {
        btnInsertContacts = findView(R.id.btn_insert_contacts);
        mContactListView = findView(R.id.recyclerView);
        mEmptyView = findView(R.id.empty_view);
    }

    @Override
    protected void setListener() {
        mContactAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position, Object object) {
                mContactPresenter.onItemClick(position,object);
            }

            @Override
            public void onItemDelete(int position, Object object) {

            }
        });

        btnInsertContacts.setOnClickListener(v -> {
            mContactPresenter.insertContacts();
        });
    }

    @Override
    protected void initView() {
        mEmptyView.setVisibility(View.GONE);
        mContactAdapter = new ContactAdapter();
        mContactListView.setLayoutManager(new LinearLayoutManager(this));
        mContactListView.setAdapter(mContactAdapter);
        mContactListView.setItemAnimator(new DefaultItemAnimator());

        mContactPresenter = new ContactPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContactPresenter.onCreate();
        mContactPresenter.setView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mContactPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mContactPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mContactPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mContactPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContactPresenter.onDestroy();
    }

    @Override
    public void renderContactItem(ContactItem item, int position) {
        /**滚动到指定位置*/
        ((LinearLayoutManager) mContactListView.getLayoutManager()).scrollToPositionWithOffset(position,
                0);
        mContactAdapter.replaceItem(item, position);
        mContactAdapter.notifyItemChanged(position);
    }

    @Override
    public void renderContactList(List<BaseContactItem> list) {
        mContactAdapter.setData(list);
        mContactAdapter.notifyDataSetChanged();
    }

    @Override
    public void renderFastScrollBar(List<String> letters) {

    }

    @Override
    public void destroyView() {
        this.finish();
    }

    @Override
    public void gotoContactProfile(Contact contact) {
        Intent intent = new Intent();
        intent.putExtra(ContactProfileActivity.ACTION_NICK_NAME,contact.getNickName());
        intent.setClass(this,ContactProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void showEmptyBox() {
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyBox() {
        mEmptyView.setVisibility(View.GONE);
    }
}
