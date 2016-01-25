package com.attosoft.mvpdemo.util.contact;

import android.text.TextUtils;

import com.attosoft.mvpdemo.App;
import com.attosoft.mvpdemo.R;
import com.attosoft.mvpdemo.data.entity.Contact;
import com.attosoft.mvpdemo.ui.adapter.item.BaseContactItem;
import com.attosoft.mvpdemo.ui.adapter.item.ContactItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by andy on 15/11/5.
 */
public class ContactSeparateUtil {

    protected String[] mLetterList = {"↑", "★", "#", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

//    public ContactItem getFriendRequestAction(List<XRushAddFriendRequest> requestList){
//        /**新盆友请求*/
//        int type = ContactItem.TYPE_ACTION;
//        if(requestList != null) {
//            if(requestList.size() == 1) {
//                type = ContactItem.TYPE_SINGLE_FRIEND_REQUEST;
//            } else if(requestList.size() > 1){
//                type = ContactItem.TYPE_MORE_FRIEND_REQUEST;
//            }
//        }
//        ContactItem newFriedRequests = new ContactItem(type);
//        newFriedRequests.object = requestList;
//        newFriedRequests.extralType = ContactItem.TYPE_OTHER_REQUEST;
//        return newFriedRequests;
//    }

    /**
     * 邮件联系人
     */
    public ContactItem getEmailContactAction() {
        ContactItem emailContacts = new ContactItem(ContactItem.TYPE_ACTION);
        emailContacts.extralType = ContactItem.TYPE_OTHER_EMAIL_CONTACTS;
        return emailContacts;
    }

    /**
     * 群组
     */
    public ContactItem getGroupAction() {
        ContactItem otherGroup = new ContactItem(ContactItem.TYPE_ACTION);
        otherGroup.extralType = ContactItem.TYPE_OTHER_GROUPS;
        return otherGroup;
    }

    /**
     * 联系人个数
     *
     * @param size
     * @return
     */
    public ContactItem getFootItem(int size) {
        ContactItem itemFoot = new ContactItem(ContactItem.TYPE_CONTACT_FOOT, size);
        return itemFoot;
    }

    public ContactItem getRecentContactGroup(){
        ContactItem contactItem = new ContactItem(BaseContactItem.TYPE_GROUP_FLAG);
        contactItem.displayName = App.getAppContext().getString(R.string.Recent);
        contactItem.sortKey = "recent";
        return contactItem;
    }

    public List<ContactItem> getContactListWithoutGroup(List<Contact> xRushContacts){
        List<ContactItem> contactItemList = new ArrayList<>();

        for (Contact contact : xRushContacts) {
            ContactItem contactItem = new ContactItem(BaseContactItem.TYPE_CONTACT_ITEM);
            contactItem.contact = contact;

            String alias = contact.getNickName();
//            if (TextUtils.isEmpty(alias)) {
//                alias = contact.getFullName();
//            }


            if (alias.length() == 0) {
                contactItem.sortKey = PinyinUtil.getInstance().convertToPinyin("" + contact.getContactId(), true).toLowerCase();
//                if (contactItem.contact.getEmail() != null && contactItem.contact.getEmail().size() > 0) {
                    contactItem.displayName = (contactItem.contact.getEmail());
//                }
            } else {
                contactItem.sortKey = PinyinUtil.getInstance().convertToPinyin(alias, true).toLowerCase();
                contactItem.displayName = alias;
            }
            contactItemList.add(contactItem);
        }
        Collections.sort(contactItemList, new PinyinComparator());
        return contactItemList;
    }


    /**
     * 新盆友请求
     */
    public void getFriendAction() {
//        int type = ContactItem.TYPE_ACTION;
//        if(requestList != null) {
//            if(requestList.size() == 1) {
//                type = ContactItem.TYPE_SINGLE_FRIEND_REQUEST;
//            } else if(requestList.size() > 1){
//                type = ContactItem.TYPE_MORE_FRIEND_REQUEST;
//            }
//        }
//        ContactItem newFriedRequests = new ContactItem(type);
//        newFriedRequests.extralType = ContactItem.TYPE_OTHER_REQUEST;
//        actionList.add(newFriedRequests);
    }


    public List<ContactItem> separateContacts(Map<Long, Contact> rushContacts) {
        List<ContactItem> sparateContactList = new ArrayList<>();

        List<Contact> vipContacts = new ArrayList<>();
        List<Contact> normalContacts = new ArrayList<>();
        for (Map.Entry<Long, Contact> entry : rushContacts.entrySet()) {
//            if (entry.getValue().getIsVip()) {
//                vipContacts.add(entry.getValue());
//            } else {
                normalContacts.add(entry.getValue());
//            }
        }

        //分组
        List<ContactItem> tempSortList = sortContact(normalContacts);
        //#分组下的
        List<ContactItem> otherGroupList = new ArrayList<>();
        //在分组上面的都默认认为是其他分组下的item
        for (int i = 0; i < tempSortList.size(); i++) {
            if (tempSortList.get(i).isGroup()) {
                break;
            } else {
                otherGroupList.add(tempSortList.get(i));
            }
        }

        tempSortList.removeAll(otherGroupList);

        //添加vip分组（队头）
        if (vipContacts.size() > 0) {
            ContactItem vipGroupItem = new ContactItem(BaseContactItem.TYPE_GROUP_FLAG);
            vipGroupItem.sortKey = mLetterList[1];
            vipGroupItem.isVipGroup = true;
            vipGroupItem.displayName = "VIP";
            tempSortList.addAll(0, sortContactWithoutGroup(vipContacts));
            //随便填一个值，更新索引值的时候会再赋值
            tempSortList.add(0, vipGroupItem);
        } else {
            //
        }

        if (otherGroupList.size() > 0) {
            /**添加 "#" 分组*/
            ContactItem otherSortItem = new ContactItem(BaseContactItem.TYPE_GROUP_FLAG);
            otherSortItem.sortKey = mLetterList[2];
            otherSortItem.displayName = mLetterList[2];
            //随便填一个值，更新索引值的时候会再赋值
            tempSortList.add(otherSortItem);
        } else {
            //
        }
        tempSortList.addAll(otherGroupList);
        sparateContactList.addAll(tempSortList);

        return sparateContactList;
    }

    /**
     * 更新索引
     */
    public List<String> updateGroupIndex(List<BaseContactItem> contactItems) {
        List<String> indexList = new ArrayList<>();
        //添加"↑"索引
        indexList.add(0, mLetterList[0]);
        for (int i = 0; i < contactItems.size(); i++) {
            if (contactItems.get(i).isGroup()) {
                indexList.add(contactItems.get(i).sortKey.toUpperCase());
            }
        }

        return indexList;
    }

    /**
     * 分组
     */
    private List<ContactItem> sortContact(List<Contact> rushContacts) {
        LinkedList<ContactItem> tempSortList = new LinkedList<>();

        Map<String, Integer> groupIndex = new HashMap<>();
        /**添加相应的email contact*/
        for (Contact contact : rushContacts) {
            ContactItem contactItem = new ContactItem(BaseContactItem.TYPE_CONTACT_ITEM);
            contactItem.contact = contact;
            String alias = contact.getNickName();
//            if (TextUtils.isEmpty(alias)) {
//                alias = contact.getFullName();
//            }


            if (alias.length() == 0) {
                contactItem.sortKey = PinyinUtil.getInstance().convertToPinyin("" + contact.getContactId(), true).toLowerCase();
//                if (contactItem.contact.getEmail() != null && contactItem.contact.getEmail().size() > 0) {
                    contactItem.displayName = (contactItem.contact.getEmail());
//                }
            } else {
                contactItem.sortKey = PinyinUtil.getInstance().convertToPinyin(alias, true).toLowerCase();
                contactItem.displayName = alias;
            }

            /**首字母*/
            String sortLetterUpper = contactItem.sortKey.substring(0, 1).toUpperCase();
            /**首字母没有在索引表中，并且字母是A-Z的字母，则创建分组项*/
            if (!groupIndex.containsKey(sortLetterUpper) && sortLetterUpper.matches("[A-Z]")) {
                /**添加索引 暂时添加为-1*/
                groupIndex.put(sortLetterUpper, -1);
                ContactItem groupItem = new ContactItem(BaseContactItem.TYPE_GROUP_FLAG);
                /**所有的英文字母都转为小写，以方便排序和分组*/
                groupItem.sortKey = sortLetterUpper.toLowerCase();
                groupItem.displayName = sortLetterUpper;
                tempSortList.add(groupItem);
            }
            tempSortList.add(contactItem);
        }
        Collections.sort(tempSortList, new PinyinComparator());
        return tempSortList;
    }

    /**
     * 排序不分组
     *
     * @param mailContacts
     * @return
     */
    private List<ContactItem> sortContactWithoutGroup(List<Contact> mailContacts) {
        LinkedList<ContactItem> tempSortList = new LinkedList<>();
        /**添加相应的email contact*/
        for (Contact contact : mailContacts) {
            ContactItem contactItem = new ContactItem(BaseContactItem.TYPE_CONTACT_ITEM);
            contactItem.contact = contact;
            if (contact.getNickName().length() == 0) {
                contactItem.sortKey = PinyinUtil.getInstance().convertToPinyin(contact.getEmail(), true).toLowerCase();
                contactItem.displayName = contact.getEmail();
            } else {
                contactItem.sortKey = PinyinUtil.getInstance().convertToPinyin(contact.getNickName(), true).toLowerCase();
                contactItem.displayName = contact.getNickName();
            }
            tempSortList.add(contactItem);
        }

        Collections.sort(tempSortList, new PinyinComparator());
        return tempSortList;
    }

    private List<ContactItem> sortContactWithoutSort(List<Contact> mailContacts) {
        LinkedList<ContactItem> tempSortList = new LinkedList<>();
        /**添加相应的email contact*/
        for (Contact contact : mailContacts) {
            ContactItem contactItem = new ContactItem(BaseContactItem.TYPE_CONTACT_ITEM);
            contactItem.contact = contact;
            if (contact.getNickName().length() == 0) {
                contactItem.sortKey = PinyinUtil.getInstance().convertToPinyin(contact.getEmail(), true).toLowerCase();
                contactItem.displayName = contact.getEmail();
            } else {
                contactItem.sortKey = PinyinUtil.getInstance().convertToPinyin(contact.getNickName(), true).toLowerCase();
                contactItem.displayName = contact.getNickName();
            }
            tempSortList.add(contactItem);
        }

        return tempSortList;
    }


    public List<ContactItem> sortRecentContacts(List<Contact> xRushContactList){
        LinkedList<ContactItem> tempSortList = new LinkedList<>();
        if(xRushContactList.size()>0) {
            ContactItem contactItem = new ContactItem(BaseContactItem.TYPE_GROUP_FLAG);
            contactItem.sortKey = "recent";
            contactItem.displayName = App.getAppContext().getResources().getString(R.string.Recents);
            tempSortList.add(contactItem);
        }
        tempSortList.addAll(sortContactWithoutGroup(xRushContactList));
        return tempSortList;
    }

    public List<ContactItem> getRecentContacts(List<Contact> xRushContactList){
        LinkedList<ContactItem> tempSortList = new LinkedList<>();
        if(xRushContactList.size()>0) {
            ContactItem contactItem = new ContactItem(BaseContactItem.TYPE_GROUP_FLAG);
            contactItem.sortKey = "recent";
            contactItem.displayName = App.getAppContext().getResources().getString(R.string.Recents);
            tempSortList.add(contactItem);
        }
        tempSortList.addAll(sortContactWithoutSort(xRushContactList));
        return tempSortList;
    }
}
