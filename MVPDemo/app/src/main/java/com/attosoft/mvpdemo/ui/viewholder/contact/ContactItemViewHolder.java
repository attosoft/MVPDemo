package com.attosoft.mvpdemo.ui.viewholder.contact;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.attosoft.mvpdemo.R;

/**
 * Created by andy on 15/9/24.
 */
public class ContactItemViewHolder extends RecyclerView.ViewHolder {

    public ImageView mIcon;
    public TextView mNickName;
    public TextView mEmail;
    public View mDivider;
    public android.widget.CheckBox mCheckBox;

    public ContactItemViewHolder(View itemView) {
        super(itemView);
        mIcon = (ImageView) itemView.findViewById(R.id.icon);
        mNickName = (TextView) itemView.findViewById(R.id.group_name);
        mEmail = (TextView) itemView.findViewById(R.id.email);
        mDivider = itemView.findViewById(R.id.divider);
        mCheckBox = (android.widget.CheckBox)itemView.findViewById(R.id.item_selectbox);
    }

    public void hideDivider(){
        if(mDivider != null){
            mDivider.setVisibility(View.INVISIBLE);
        }
    }

    public void showDivider(){
        if(mDivider != null){
            mDivider.setVisibility(View.VISIBLE);
        }
    }

    public final static int LayoutResource = R.layout.contact_item;
    public final static int LayoutResourceSearch = R.layout.contact_item_search;
}
