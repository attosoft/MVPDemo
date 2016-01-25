package com.attosoft.mvpdemo.ui.viewholder.contact;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.attosoft.mvpdemo.R;

/**
 * Created by andy on 15/9/24.
 */
public class ContactOtherViewHolder extends RecyclerView.ViewHolder {

    public ImageView mIcon;
    public TextView mTitle;

    public ContactOtherViewHolder(View itemView) {
        super(itemView);
        mIcon = (ImageView) itemView.findViewById(R.id.icon);
        mTitle = (TextView) itemView.findViewById(R.id.title);
    }

    public final static int LayoutResource = R.layout.contact_round_item;
}
