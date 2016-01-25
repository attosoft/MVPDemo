package com.attosoft.mvpdemo.ui.adapter.item;

import android.text.TextUtils;

import com.attosoft.mvpdemo.data.entity.Contact;

/**
 * Created by andy on 15/9/24.
 */
public class ContactItem extends BaseContactItem implements IContactItem {

    public Contact contact = null;

    public ContactItem(int type) {
        super(type);
    }

    public ContactItem(int type, Object o) {
        super(type, o);
    }

    public boolean isVipGroup = false;

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String getNickName() {
        if (contact != null) {
            return contact.getNickName();
        }
        return null;
    }

    @Override
    public String getEmail() {
        if (TextUtils.isEmpty(contact.getEmail())) {
            return contact.getEmail();
        }
        return null;
    }

    public long getContactId() {
        if (contact != null) {
            return contact.getContactId();
        }
        return 0;
    }
}
