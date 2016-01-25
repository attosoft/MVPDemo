package com.attosoft.mvpdemo.data.entity;

/**
 * Created by ruyaya on 16/1/24.
 */
public class Contact {
    private long mContactId;

    private String mNickName;

    private String mEmail;

    public long getContactId() {
        return mContactId;
    }

    public String getNickName() {
        return mNickName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setNickName(String mNickName) {
        this.mNickName = mNickName;
    }

    public void setContactId(long mContactId) {
        this.mContactId = mContactId;
    }
}
