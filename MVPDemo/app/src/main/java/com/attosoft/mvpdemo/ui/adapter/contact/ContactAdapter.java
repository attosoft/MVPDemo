package com.attosoft.mvpdemo.ui.adapter.contact;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.attosoft.mvpdemo.App;
import com.attosoft.mvpdemo.R;
import com.attosoft.mvpdemo.ui.adapter.BaseContactAdapter;
import com.attosoft.mvpdemo.ui.adapter.item.BaseContactItem;
import com.attosoft.mvpdemo.ui.adapter.item.ContactItem;
import com.attosoft.mvpdemo.ui.view.contact.ItemClickListener;
import com.attosoft.mvpdemo.ui.viewholder.contact.ContactBottomViewHolder;
import com.attosoft.mvpdemo.ui.viewholder.contact.ContactGroupViewHolder;
import com.attosoft.mvpdemo.ui.viewholder.contact.ContactImportViewHolder;
import com.attosoft.mvpdemo.ui.viewholder.contact.ContactItemViewHolder;
import com.attosoft.mvpdemo.ui.viewholder.contact.ContactOtherViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andy on 15/11/6.
 */
public class ContactAdapter extends BaseContactAdapter {
    private ItemClickListener mItemClickListener;
    List<BaseContactItem> mData = new ArrayList<>();

    public void setData(List<BaseContactItem> data) {
        mData.clear();
        mData.addAll(data);
        updateGroupIndex(mData);
    }

    public void addItem(BaseContactItem item,int position){
        mData.add(position,item);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View itemView;
        switch (viewType) {
            case ContactItem.TYPE_ACTION:
                itemView = LayoutInflater.from(App.getAppContext()).inflate(ContactOtherViewHolder.LayoutResource, null);
                viewHolder = new ContactOtherViewHolder(itemView);
                break;
            case ContactItem.TYPE_IMPORT:
                itemView = LayoutInflater.from(App.getAppContext()).inflate(ContactImportViewHolder.LayoutResource, null);
                viewHolder = new ContactImportViewHolder(itemView);
                break;
            case ContactItem.TYPE_CONTACT_FOOT:
                itemView = LayoutInflater.from(App.getAppContext()).inflate(ContactBottomViewHolder.LayoutResource, null);
                viewHolder = new ContactBottomViewHolder(itemView);
                break;
            case ContactItem.TYPE_GROUP_FLAG:
                itemView = LayoutInflater.from(App.getAppContext()).inflate(ContactGroupViewHolder.LayoutResource, null);
                viewHolder = new ContactGroupViewHolder(itemView);
                break;
            case ContactItem.TYPE_CONTACT_ITEM:
                itemView = LayoutInflater.from(App.getAppContext()).inflate(ContactItemViewHolder.LayoutResource, null);
                viewHolder = new ContactItemViewHolder(itemView);
                break;
            case ContactItem.TYPE_SINGLE_FRIEND_REQUEST:

                break;
            case ContactItem.TYPE_MORE_FRIEND_REQUEST:

                break;
            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        BaseContactItem item = mData.get(position);
        if (holder instanceof ContactOtherViewHolder) {
            ContactOtherViewHolder otherViewHolder = (ContactOtherViewHolder) holder;
            bindOtherActionViewHolder((ContactItem) item, otherViewHolder, position);
        } else if (holder instanceof ContactGroupViewHolder) {
            ContactGroupViewHolder groupViewHolder = (ContactGroupViewHolder) holder;
            bindGroupViewHolder((ContactItem)item, groupViewHolder, position);
        } else if (holder instanceof ContactBottomViewHolder) {
            ContactBottomViewHolder bottomViewHolder = (ContactBottomViewHolder) holder;
            bindBottomViewHolder(bottomViewHolder, position);
        } else if (holder instanceof ContactItemViewHolder) {
            ContactItemViewHolder itemViewHolder = (ContactItemViewHolder) holder;
            bindContactItemViewHolder(itemViewHolder, position);
        } else {
            //do something
        }
    }

    private void bindOtherActionViewHolder(ContactItem item, ContactOtherViewHolder otherViewHolder, final int position) {
        Resources rs = App.getAppContext().getResources();
        switch (item.extralType) {
            case ContactItem.TYPE_OTHER_REQUEST:
                otherViewHolder.mTitle.setText(rs.getString(R.string.FriendRequests));
                otherViewHolder.mIcon.setImageResource(R.drawable.icon_friendrequest);
                break;
            case ContactItem.TYPE_OTHER_EMAIL_CONTACTS:
                otherViewHolder.mTitle.setText(rs.getString(R.string.EmailContacts));
                otherViewHolder.mIcon.setImageResource(R.drawable.icon_mail_b);
                break;
            case ContactItem.TYPE_OTHER_GROUPS:
                otherViewHolder.mTitle.setText(rs.getString(R.string.Groups));
                otherViewHolder.mIcon.setImageResource(R.drawable.icon_groups);
                break;
            default:
                break;
        }
        otherViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(v, position, mData.get(position));
            }
        });
    }

    private void bindGroupViewHolder(ContactItem item, ContactGroupViewHolder groupViewHolder, final int position) {
        groupViewHolder.setStar(item.isVipGroup);
        groupViewHolder.mIndex.setText(mData.get(position).displayName);
    }

    private void bindBottomViewHolder(ContactBottomViewHolder bottomViewHolder, final int position) {
        Integer size = (Integer) (mData.get(position).object);
        if (size > 1) {
            bottomViewHolder.mContactCount.setText(String.format(App.getAppContext().getResources().getString(R.string.NContacts), size + ""));
        } else {
            bottomViewHolder.mContactCount.setText(App.getAppContext().getResources().getString(R.string.OneContact));
        }
    }

    private void bindContactItemViewHolder(final ContactItemViewHolder itemViewHolder, final int position) {
        ContactItem contactItem = (ContactItem) mData.get(position);

        String nickName = contactItem.contact.getNickName();
        if (TextUtils.isEmpty(nickName)){
            nickName = " ";
        }

//        itemViewHolder.mIcon.setHttpImage(contactItem.contact.getAvatarUrl(), "50_50", AvatarDrawable.generate(nickName));
        itemViewHolder.mNickName.setText(nickName);

        if (contactItem.contact.getEmail() != null) {
            itemViewHolder.mEmail.setText(contactItem.contact.getEmail());
        }
        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, position, mData.get(position));
                }
            }
        });

        if (position < getItemCount() - 1) {
            if (getItemViewType(position + 1) == ContactItem.TYPE_GROUP_FLAG || getItemViewType(position + 1) == ContactItem.TYPE_CONTACT_FOOT) {
                itemViewHolder.hideDivider();
            } else if (getItemViewType(position + 1) == ContactItem.TYPE_CONTACT_ITEM) {
                itemViewHolder.showDivider();
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).type;
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    /**
     * 替换指定位置的item
     *
     * @param item
     * @param position
     */
    public void replaceItem(ContactItem item, int position) {
        if (position < getItemCount()) {
            mData.remove(position);
            mData.add(position, item);
        }
    }

}
