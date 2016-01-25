package com.attosoft.mvpdemo.util.contact;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by andy on 15/9/29.
 */
public class ContactUtil {

    private static ContactUtil mInstance;

    public synchronized static ContactUtil getInstance() {
        if (mInstance == null) {
            mInstance = new ContactUtil();
        }
        return mInstance;
    }

    private ContactUtil() {
    }

    /**
     * 获得联系人的电话号码
     *
     * @param context
     * @param contactId
     */
    private void getContactPhoneList(Context context, String contactId) {
        // 获得联系人的电话号码
        Cursor phones = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                        + " = " + contactId, null, null);
        if (phones.moveToFirst()) {
            do {
                // 遍历所有的电话号码
                String phoneNumber = phones
                        .getString(phones
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String phoneType = phones
                        .getString(phones
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
            } while (phones.moveToNext());
        }
    }

//    /**
//     * 获取该联系人邮箱
//     *
//     * @param context
//     * @param contactId
//     */
//    private List<XMailContact> getContactEmail(Context context, String contactId, String nickName) {
//        List<XMailContact> emailContacts = new ArrayList<>();
//        // 获取该联系人邮箱
//        Cursor emails = context.getContentResolver().query(
//                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
//                null,
//                ContactsContract.CommonDataKinds.Phone.CONTACT_ID
//                        + " = " + contactId, null, null);
//        if (emails != null && emails.moveToFirst()) {
//            do {
//                // 遍历所有的电话号码
//                String emailType = emails
//                        .getString(emails
//                                .getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
//                String emailValue = emails
//                        .getString(emails
//                                .getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
//                XMailContact xMailContact = new XMailContact(emailValue,
//                        "",
//                        nickName,
//                        "",
//                        "",
//                        0,
//                        false,
//                        false,
//                        false,
//                        false,
//                        true);
//                emailContacts.add(xMailContact);
//
//            } while (emails.moveToNext());
//        }
//
//        if (emails != null) {
//            emails.close();
//        }
//        return emailContacts;
//    }

    /**
     * 获取该联系人IM
     *
     * @param context
     * @param contactId
     */
    private void getContactIM(Context context, String contactId) {
        Cursor IMs = context.getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                new String[]{ContactsContract.Contacts.Data._ID, ContactsContract.CommonDataKinds.Im.PROTOCOL, ContactsContract.CommonDataKinds.Im.DATA},
                ContactsContract.Data.CONTACT_ID + "=?" + " AND " + ContactsContract.Contacts.Data.MIMETYPE + "='"
                        + ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE + "'",
                new String[]{contactId}, null);
        if (IMs.moveToFirst()) {
            do {
                String protocol = IMs.getString(IMs
                        .getColumnIndex(ContactsContract.CommonDataKinds.Im.PROTOCOL));
                String date = IMs
                        .getString(IMs.getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA));
            } while (IMs.moveToNext());
        }
    }

    /**
     * 获取该联系人地址
     *
     * @param context
     * @param contactId
     */
    private void getContactAddress(Context context, String contactId) {
        // 获取该联系人地址
        Cursor address = context.getContentResolver()
                .query(
                        ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + " = " + contactId, null, null);
        if (address.moveToFirst()) {
            do {
                // 遍历所有的地址
                String street = address
                        .getString(address
                                .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
                String city = address
                        .getString(address
                                .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
                String region = address
                        .getString(address
                                .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
                String postCode = address
                        .getString(address
                                .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
                String formatAddress = address
                        .getString(address
                                .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS));
            } while (address.moveToNext());
        }
    }

    /**
     * 获取该联系人组织
     *
     * @param context
     * @param contactId
     */
    private void getContactOrganization(Context context, String contactId) {
        // 获取该联系人组织
        Cursor organizations = context.getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                new String[]{ContactsContract.Contacts.Data._ID, ContactsContract.CommonDataKinds.Organization.COMPANY,
                        ContactsContract.CommonDataKinds.Organization.TITLE},
                ContactsContract.Data.CONTACT_ID + "=?" + " AND " + ContactsContract.Contacts.Data.MIMETYPE + "='"
                        + ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE + "'",
                new String[]{contactId}, null);
        if (organizations.moveToFirst()) {
            do {
                String company = organizations.getString(organizations
                        .getColumnIndex(ContactsContract.CommonDataKinds.Organization.COMPANY));
                String title = organizations.getString(organizations
                        .getColumnIndex(ContactsContract.CommonDataKinds.Organization.TITLE));
            } while (organizations.moveToNext());
        }
    }

    /**
     * 获取备注信息
     *
     * @param context
     * @param contactId
     */
    private void getContactNotes(Context context, String contactId) {
        // 获取备注信息
        Cursor notes = context.getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                new String[]{ContactsContract.Contacts.Data._ID, ContactsContract.CommonDataKinds.Note.NOTE},
                ContactsContract.Data.CONTACT_ID + "=?" + " AND " + ContactsContract.Data.MIMETYPE + "='"
                        + ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE + "'",
                new String[]{contactId}, null);
        if (notes.moveToFirst()) {
            do {
                String noteinfo = notes.getString(notes
                        .getColumnIndex(ContactsContract.CommonDataKinds.Note.NOTE));
            } while (notes.moveToNext());
        }
    }

    /**
     * 获取nickname信息
     *
     * @param context
     * @param contactId
     */
    private String getContactNickName(Context context, String contactId) {
        String nickname_ = "";
        // 获取nickname信息
        Cursor nicknames = context.getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                new String[]{ContactsContract.Contacts.Data._ID, ContactsContract.CommonDataKinds.Nickname.NAME},
                ContactsContract.Data.CONTACT_ID + "=?" + " AND " + ContactsContract.Contacts.Data.MIMETYPE + "='"
                        + ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE + "'",
                new String[]{contactId}, null);
        if (nicknames.moveToFirst()) {
            do {
                nickname_ = nicknames.getString(nicknames
                        .getColumnIndex(ContactsContract.CommonDataKinds.Nickname.NAME));
            } while (nicknames.moveToNext());
        }

        return nickname_;
    }

//    /**
//     * 获取所有联系人
//     *
//     * @param context
//     */
//    public List<XMailContact> getEmailContacts(Context context) {
//        List<XMailContact> xMailContacts = new ArrayList<>();
//        // 获得所有的联系人
//        Cursor cur = context.getContentResolver().query(
//                ContactsContract.Contacts.CONTENT_URI,
//                null,
//                null,
//                null,
//                ContactsContract.Contacts.DISPLAY_NAME
//                        + " COLLATE LOCALIZED ASC");
//        // 循环遍历
//        if (cur.moveToFirst()) {
//            int idColumn = cur.getColumnIndex(ContactsContract.Contacts._ID);
//
//            int displayNameColumn = cur
//                    .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
//
//            do {
//                // 获得联系人的ID号
//                String contactId = cur.getString(idColumn);
//                // 获得联系人姓名
//                String disPlayName = cur.getString(displayNameColumn);
//
//                // 查看该联系人有多少个电话号码。如果没有这返回值为0
//                int phoneCount = cur
//                        .getInt(cur
//                                .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
////                Log.e("attosoft", "username:" + disPlayName);
////                if (phoneCount > 0) {
////                    // 获得联系人的电话号码
////                    getContactPhoneList(context, contactId);
////                }
//                // 获取nickname信息
//
//                String nickName = !TextUtils.isEmpty(disPlayName) ? disPlayName : getContactNickName(context, contactId);
//
//                // 获取该联系人邮箱
//                xMailContacts.addAll(getContactEmail(context, contactId, nickName));
//
//                // 获取该联系人IM
////                getContactIM(context, contactId);
//
//                // 获取该联系人地址
////                getContactAddress(context, contactId);
//
//                // 获取该联系人组织
////                getContactOrganization(context, contactId);
//
//                // 获取备注信息
////                getContactNotes(context, contactId);
//
//            } while (cur.moveToNext());
//
//        }
//
//        return xMailContacts;
//    }

//    public List<XMailContact> getEmails(Context context) {
//        List<XMailContact> emails = new ArrayList<>();
//        /**获取所有有email地址的联系人列表*/
//        String[] projectionEmail = {ContactsContract.CommonDataKinds.Email._ID,
//                ContactsContract.CommonDataKinds.Email.CONTACT_ID,
//                ContactsContract.CommonDataKinds.Email.ADDRESS
//        };
//        Cursor emailsCursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
//                projectionEmail,
//                null, null, null);
//        if (emailsCursor != null && emailsCursor.moveToFirst()) {
//            do {
//                String contactId = emailsCursor
//                        .getString(emailsCursor
//                                .getColumnIndex(ContactsContract.CommonDataKinds.Email.CONTACT_ID));
//                String emailAddress = emailsCursor
//                        .getString(emailsCursor
//                                .getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
//                String displayName = getNameByContactId(context, contactId);
//                XMailContact xMailContact = new XMailContact(emailAddress,
//                        "",
//                        displayName,
//                        "",
//                        "",
//                        0,
//                        false,
//                        false,
//                        false,
//                        false,
//                        true);
//                emails.add(xMailContact);
//            } while (emailsCursor.moveToNext());
//        }
//        if (emailsCursor != null) {
//            emailsCursor.close();
//        }
//
//        return emails;
//    }

    /**
     * 通过contactId获取显示的名字
     *
     * @param context
     * @param contactId
     * @return
     */
    public String getNameByContactId(Context context, String contactId) {
        String name = "";
        String[] projectionName = {ContactsContract.CommonDataKinds.Phone._ID,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Nickname.NAME
        };
        // 获取displayName信息
        Cursor displayNameCursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projectionName,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                new String[]{contactId}, null);

        if (displayNameCursor.moveToFirst()) {
            do {
                String displayName = displayNameCursor.getString(displayNameCursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String nickName = displayNameCursor.getString(displayNameCursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Nickname.NAME));
                if (displayName.equals("") && nickName != null) {
                    name = nickName;
                } else {
                    name = displayName;
                }
            } while (displayNameCursor.moveToNext());
        }
        displayNameCursor.close();
        return name;
    }

//    public String getRushContactAvatarUrl(XRushContact rushContact) {
//        String avatarUrl = rushContact.getAvatarUrl();
//        if(TextUtils.isEmpty(avatarUrl)) {
//            avatarUrl = (rushContact.getNickName());
//            if(TextUtils.isEmpty(avatarUrl)) {
//                avatarUrl = rushContact.getFullName();
//            }
//        }
//        return avatarUrl;
//    }
//
//    public static String getXMailContactName(XMailContact contact){
//        String name = "";
//        if(!TextUtils.isEmpty(contact.getName())){
//            name = contact.getName();
//        }else {
//            if(!TextUtils.isEmpty(contact.getMailAddress()) && contact.getMailAddress().lastIndexOf("@") != -1){
//                name = contact.getMailAddress().substring(0,contact.getMailAddress().lastIndexOf("@"));
//            }else {
//                name = contact.getMailAddress();
//            }
//        }
//        return name;
//    }
//
//    public static String getXRushContactName(XRushContact contact) {
//        String name = "";
//        if (!TextUtils.isEmpty(contact.getNickName())) {
//            name = contact.getNickName();
//        } else if (!TextUtils.isEmpty(contact.getFullName())) {
//            name = contact.getFullName();
//        } else {
//            if (contact.getEmail().size()>0){
//                if (!TextUtils.isEmpty(contact.getEmail().get(0)) && contact.getEmail().get(0).lastIndexOf("@") != -1) {
//                    name = contact.getEmail().get(0).substring(0, contact.getEmail().get(0).lastIndexOf("@"));
//                } else {
//                    name = contact.getEmail().get(0);
//                }
//            }else {
//                name = "";
//            }
//        }
//        return name;
//    }
}
