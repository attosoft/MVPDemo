package com.attosoft.mvpdemo.presenter.contact.impl;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.attosoft.mvpdemo.abstractview.contact.IContactListView;
import com.attosoft.mvpdemo.data.entity.Contact;
import com.attosoft.mvpdemo.model.ContactModel;
import com.attosoft.mvpdemo.presenter.contact.IContactPresenter;
import com.attosoft.mvpdemo.ui.adapter.item.BaseContactItem;
import com.attosoft.mvpdemo.util.contact.ContactSeparateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andy on 15/11/6.
 */
public class ContactPresenter implements IContactPresenter, ContactModel.IContactUpdateCallback {

    private final String TAG = this.getClass().toString();

    private IContactListView mView;

    private Map<Long, Contact> mContactMap = new HashMap<>();

    private ContactSeparateUtil mContactSeparateUtil;

    private final int MSG_LOAD_CONTACT = 1;

    private final int MSG_INSERT_CONTACT = 2;

    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_LOAD_CONTACT:
                    ContactModel.getInstance().loadContacts(ContactPresenter.this);
                    break;
                case MSG_INSERT_CONTACT:
                    ContactModel.getInstance().insertContact();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void setView(IContactListView view) {
        mView = view;
    }

    @Override
    public void onItemClick(int position, Object object) {

    }

    @Override
    public void insertContacts() {
        Message msg = Message.obtain();
        msg.what = MSG_INSERT_CONTACT;

        mHandler.removeMessages(MSG_INSERT_CONTACT);
        mHandler.sendMessageDelayed(msg, 500);
    }

    /**
     * 生命周期相关 start
     */
    @Override
    public void onCreate() {
        ContactModel.getInstance().registerDataModelCallback(this);
        mContactSeparateUtil = new ContactSeparateUtil();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
//        ContactModel.getInstance().loadContacts(this);
        Message msg = Message.obtain();
        msg.what = MSG_LOAD_CONTACT;

        mHandler.removeMessages(MSG_LOAD_CONTACT);
        mHandler.sendMessageDelayed(msg,1000);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        ContactModel.getInstance().unregisterDataModelCallback(this);
    }
    /** 生命周期相关 end*/


    /**
     * model 更新到达 start
     */

    @Override
    public void updateContactAction(ContactModel.UpdateType type, Contact request) {
        switch (type) {
            case UPDATE:
                break;
            case ADD: {
                mContactMap.put(request.getContactId(), request);

                List<BaseContactItem> items = new ArrayList<>();
                items.addAll(mContactSeparateUtil.separateContacts(mContactMap));
                mView.renderContactList(items);
            }
            break;
            case DELETE:
                break;
            default:
                break;
        }
    }

    @Override
    public void loadContactAction(ContactModel.UpdateType type, List<Contact> contacts) {
        switch (type) {
            case UPDATE:
                break;
            case ADD: {
                mContactMap.clear();
                for (Contact contact : contacts) {
                    mContactMap.put(contact.getContactId(), contact);
                }
                List<BaseContactItem> items = new ArrayList<>();
                items.addAll(mContactSeparateUtil.separateContacts(mContactMap));
                mView.renderContactList(items);
            }
            break;
            case DELETE:
                break;
            default:
                break;
        }

    }

    /** model 更新到达 end*/
}
