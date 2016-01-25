package com.attosoft.mvpdemo.ui.activity;

import android.content.Intent;
import android.widget.TextView;

import com.attosoft.mvpdemo.R;

/**
 * Created by Tikey on 2016/1/25.
 */
public class ContactProfileActivity extends BaseActivity{

    public static final String ACTION_NICK_NAME = "action_nick_name";

    private TextView mContactInfo;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_contact_profile;
    }

    @Override
    protected void findViewByResourceId() {
        mContactInfo = findView(R.id.contact_info);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if(intent.hasExtra(ACTION_NICK_NAME)) {
            String nickName = getIntent().getStringExtra(ACTION_NICK_NAME);
            mContactInfo.setText("联系人昵称：" + nickName);
        }
    }
}
