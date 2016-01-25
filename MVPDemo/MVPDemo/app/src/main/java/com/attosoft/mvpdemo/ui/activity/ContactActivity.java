package com.attosoft.mvpdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.attosoft.mvpdemo.R;
import com.attosoft.mvpdemo.abstractview.contact.IContactListView;
import com.attosoft.mvpdemo.presenter.contact.IContactPresenter;
import com.attosoft.mvpdemo.presenter.contact.impl.ContactPresenter;
import com.attosoft.mvpdemo.ui.adapter.contact.ContactAdapter;
import com.attosoft.mvpdemo.ui.adapter.item.BaseContactItem;
import com.attosoft.mvpdemo.ui.adapter.item.ContactItem;
import com.attosoft.mvpdemo.ui.view.contact.ItemClickListener;
import com.attosoft.mvpdemo.util.ToastUtil;

import java.util.List;

/**
 * Created by andy on 16/1/24.
 */
public class ContactActivity extends BaseActivity implements IContactListView{

    private Button btnInsertContacts;

    private RecyclerView mContactListView;

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

        btnInsertContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.getInstance().show("setOnClickListener");
                mContactPresenter.insertContacts();
            }
        });
    }

    @Override
    protected void initView() {
        mContactAdapter = new ContactAdapter();
        mContactListView.setLayoutManager(new LinearLayoutManager(this));
        mContactListView.setAdapter(mContactAdapter);

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

    }

    @Override
    public void renderContactList(List<BaseContactItem> list) {
        mContactAdapter.setDatas(list);
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
    public void showEmptyBox() {

    }

    @Override
    public void hideEmptyBox() {

    }
}
