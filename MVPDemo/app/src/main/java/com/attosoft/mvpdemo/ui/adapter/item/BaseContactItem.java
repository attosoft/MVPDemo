package com.attosoft.mvpdemo.ui.adapter.item;

import java.io.Serializable;

/**
 * Created by andy on 15/9/29.
 */
public class BaseContactItem implements Serializable{
    /**
     * new friend request 、 Email Contacts、Groups
     */
    public final static int TYPE_ACTION = -1;
    /**
     * 分组
     */
    public final static int TYPE_GROUP_FLAG = 0;
    /**
     * contact item
     */
    public final static int TYPE_CONTACT_ITEM = 1;
    /**
     * 底部显示联系人个数
     */
    public final static int TYPE_CONTACT_FOOT = 2;
    /**
     * import from phone contacts
     */
    public final static int TYPE_IMPORT = 3;
    /**
     * contact item
     */
    public final static int TYPE_EMAIL_ITEM = 4;
    /**
     * friend request item
     */
    public final static int TYPE_FRIEND_REQUEST_ITEM = 5;

    /**
     * load more
     */
    public final static int TYPE_FRIEND_REQUEST_LOAD_MORE = 10;
    /**
     * new Friend request
     */
    public final static int TYPE_OTHER_REQUEST = 11;
    /**
     * Email Contacts
     */
    public final static int TYPE_OTHER_EMAIL_CONTACTS = 12;
    /**
     * Group
     */
    public final static int TYPE_OTHER_GROUPS = 13;
    /**
     * import from Phone Contacts
     */
    public final static int TYPE_OTHER_IMPORT_PHONE_CONTACT = 14;

    /**
     * have one friend request
     */
    public final static int TYPE_SINGLE_FRIEND_REQUEST = 15;

    /**
     * have more friend request
     */
    public final static int TYPE_MORE_FRIEND_REQUEST = 16;

    /**
     * 分组项
     */
    public final static int TYPE_GROUPS_ITEM = 17;

    /**显示的类型*/
    public int type = TYPE_CONTACT_ITEM;
    /**附加类别*/
    public int extralType = 0;
    /**存放其他类别的对象*/
    public Object object;

    public BaseContactItem(int type) {
        this.type = type;
    }

    public BaseContactItem(int type, Object o) {
        this.type = type;
        this.object = o;
    }

    /**排序键*/
    public String sortKey = "";
    /***/
    public String displayName = "";

    /***
     * 是否是分组项
     * @return
     */
    public boolean isGroup(){
        return type == TYPE_GROUP_FLAG;
    }

    private boolean bSelected = false;

    public boolean isSelected(){
        return bSelected;
    }

    public void setSelected(boolean selected){
        bSelected = selected;
    }

    public String getNickName() {
        return null;
    }

    public String getEmail() {
        return null;
    }

}
