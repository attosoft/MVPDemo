package com.attosoft.mvpdemo.model;

import android.os.Handler;
import android.os.Message;

import com.attosoft.mvpdemo.data.entity.Contact;
import com.attosoft.mvpdemo.util.dragger.DemoApplication;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by andy on 15/11/4.
 */
public class ContactModel {

    private final String TAG = this.getClass().toString();

    public ContactModel() {

    }

    public void onDestroy() {

    }

    public enum UpdateType {
        NONE,
        //添加好友
        ADD,
        //删除好友
        DELETE,
        //更新好友
        UPDATE,
        //拉黑
        BLOCK,
        //获取好友列表成功
        LOAD_CONTACTS_SUCCESS,
        //获取好友列表失败
        LOAD_CONTACTS_ERROR,
        //加载好友请求类别和总共大小
        LOAD_REQUEST_SUCCESS,
    }

    /**
     * 访问者接口
     */
    public interface IContactUpdateCallback {
        /**
         * 好友更新
         *
         * @param type    更新类型
         * @param request 更新内容
         */
        void updateContactAction(UpdateType type, Contact request);

        /**
         * 加载联系人列表
         *
         * @param type
         */
        void loadContactAction(UpdateType type, List<Contact> contacts);
    }

    private static volatile ContactModel mInstance = null;

    public static ContactModel getInstance() {
        ContactModel localInstance = mInstance;
        if (localInstance == null) {
            synchronized (ContactModel.class) {
                localInstance = mInstance;
                if (localInstance == null) {
                    mInstance = localInstance = new ContactModel();
                }
            }
        }
        return localInstance;
    }

    /***
     * 确保是通过UiThread通知更新数据了
     */
    private Handler mHandler = new Handler(DemoApplication.getAppContext().getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private ArrayList<WeakReference<IContactUpdateCallback>> mClients = new ArrayList<>();

    /**
     * 通知所有观察者
     */
    private void notifyAllClients(UpdateType type, Contact notify) {
        for (int i = mClients.size() - 1; i >= 0; i--) {
            IContactUpdateCallback c = mClients.get(i).get();
            if (c == null) {
                // 顺便移除已死的访问者
                mClients.remove(i);
            } else {
                switch (type) {
                    case ADD:
                        c.updateContactAction(UpdateType.ADD, notify);
                        break;
                    case DELETE:
                        c.updateContactAction(UpdateType.DELETE, notify);
                        break;
                    case UPDATE:
                        c.updateContactAction(UpdateType.UPDATE, notify);
                        break;
                    case BLOCK:
                        c.updateContactAction(UpdateType.BLOCK, notify);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * 通知所有观察者
     */
    private void notifyAllClients(UpdateType type, List<Contact> contacts) {
        for (int i = mClients.size() - 1; i >= 0; i--) {
            IContactUpdateCallback c = mClients.get(i).get();
            if (c == null) {
                // 顺便移除已死的访问者
                mClients.remove(i);
            } else {
                switch (type) {
                    case LOAD_CONTACTS_SUCCESS:
                        c.loadContactAction(type, contacts);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void notifyClient(UpdateType type, List<Contact> contacts, IContactUpdateCallback client) {
        client.loadContactAction(type, contacts);
    }

    /**
     * 注册
     */
    public void registerDataModelCallback(IContactUpdateCallback client) {
        if (client == null) {
            return;
        }

        for (int i = mClients.size() - 1; i >= 0; i--) {
            IContactUpdateCallback c = mClients.get(i).get();
            if (c == null) {
                //顺便移除已死的访问者
                mClients.remove(i);
            } else if (c == client) {
                return;
            } else {

            }
        }

        mClients.add(new WeakReference<>(client));
    }

    /**
     * 注销
     */
    public void unregisterDataModelCallback(IContactUpdateCallback client) {
        for (int i = mClients.size() - 1; i >= 0; i--) {
            IContactUpdateCallback c = mClients.get(i).get();
            if (c == null) {
                //顺便移除已死的访问者
                mClients.remove(i);
            } else if (c == client) {
                mClients.remove(i);
            } else {

            }
        }
    }

    protected String[] mLetterList = {"↑", "★", "#", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};
    /**
     * * 获取联系人列表（由于有多个观察者存在，并且这个调用是异步的，所以不能用广播方式通知，只能通知指定的观察者）
     *
     * @param client 由哪个观察者触发的就通知他
     */
    public void loadContacts(IContactUpdateCallback client) {
        List<Contact> contactList = new LinkedList<>();
        for (int i = 0;i<mLetterList.length;i++){
            Contact contact = new Contact();
            contact.setContactId(++count);
            contact.setNickName(mLetterList[i] + "_letter");
            contact.setEmail(mLetterList[i] + "@gmail.com");
            contactList.add(contact);
        }
        client.loadContactAction(UpdateType.ADD,contactList);
    }

    private int count = 1;
    public void insertContact() {
        Contact contact1 = new Contact();
        contact1.setContactId(++count);
        contact1.setEmail(count + "@gmail.com");
        contact1.setNickName(count + "#nickName");
        notifyAllClients(UpdateType.ADD, contact1);
    }

}
