package com.attosoft.mvpdemo;

import android.test.InstrumentationTestCase;
import android.util.Log;

import com.attosoft.mvpdemo.abstractview.contact.IContactListView;
import com.attosoft.mvpdemo.data.entity.Contact;
import com.attosoft.mvpdemo.presenter.contact.IContactPresenter;
import com.attosoft.mvpdemo.presenter.contact.impl.ContactPresenter;
import com.attosoft.mvpdemo.ui.adapter.item.BaseContactItem;
import com.attosoft.mvpdemo.ui.adapter.item.ContactItem;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by andy on 2016/1/25.
 */
public class TestContactPresenter extends InstrumentationTestCase {

    private final static String TAG = "TestContactPresenter";

    final CountDownLatch signal = new CountDownLatch(1);
    boolean bInserted = false;

    public void test() throws Exception{
        final IContactPresenter contactPresenter = new ContactPresenter();
        contactPresenter.setView(new IContactListView() {
            @Override
            public void renderContactItem(ContactItem item, int position) {
                Log.d(TAG, "[" + "renderContactItem" + "]" + "item.contact.getNickName:" + item.contact.getNickName() + ",position=" + position);

                /**最后环节释放信号量*/
                signal.countDown();
            }

            @Override
            public void renderContactList(List<BaseContactItem> list) {
                Log.d(TAG,"["+"renderContactList"+"]" + "list.size() = " + list.size());

                //加载完成后添加新的项
                if(!bInserted) {
                    contactPresenter.insertContacts();
                    bInserted = true;
                }
            }

            @Override
            public void renderFastScrollBar(List<String> letters) {

            }

            @Override
            public void destroyView() {

            }

            @Override
            public void gotoContactProfile(Contact contact) {
                Log.d(TAG,"[" + "gotoContactProfile" + "]" + "contact:" + contact.getNickName());
            }

            @Override
            public void showEmptyBox() {
//                Log.d(TAG,"[" + "" + "]");
                Log.d(TAG,"[" + "showEmptyBox" + "]");
            }

            @Override
            public void hideEmptyBox() {
                Log.d(TAG,"[" + "hideEmptyBox" + "]");
            }
        });

        contactPresenter.onCreate();
        contactPresenter.onResume();
//        contactPresenter.onPause();
//        contactPresenter.onStop();
//        contactPresenter.onDestroy();

        /**由于异步model更新，所以添加这个等待执行完成*/
        try {
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
