package com.attosoft.mvpdemo.presenter.contact;


import com.attosoft.mvpdemo.abstractview.contact.IContactListView;
import com.attosoft.mvpdemo.presenter.IPresenter;

/**
 * Created by andy on 15/11/8.
 */
public interface IContactPresenter extends IPresenter {
    void setView(IContactListView view);
    void onItemClick(int position, Object object);

    void insertContacts();
}
