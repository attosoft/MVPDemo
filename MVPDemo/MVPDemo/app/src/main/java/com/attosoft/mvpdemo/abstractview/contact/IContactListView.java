package com.attosoft.mvpdemo.abstractview.contact;

import com.attosoft.mvpdemo.abstractview.IView;
import com.attosoft.mvpdemo.data.entity.Contact;
import com.attosoft.mvpdemo.ui.adapter.item.BaseContactItem;
import com.attosoft.mvpdemo.ui.adapter.item.ContactItem;

import java.util.List;

/**
 * Created by andy on 15/11/6.
 */
public interface IContactListView extends IView {
    void renderContactItem(ContactItem item, int position);

    void renderContactList(List<BaseContactItem> list);

    void renderFastScrollBar(List<String> letters);

    void destroyView();

    /***
     * 显示空箱提示
     */
    void showEmptyBox();

    void hideEmptyBox();
}
