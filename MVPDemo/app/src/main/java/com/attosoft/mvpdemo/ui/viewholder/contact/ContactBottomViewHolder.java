package com.attosoft.mvpdemo.ui.viewholder.contact;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.attosoft.mvpdemo.R;

/**
 * Created by andy on 15/9/25.
 */
public class ContactBottomViewHolder extends RecyclerView.ViewHolder {

    public TextView mContactCount;

    public ContactBottomViewHolder(View itemView) {
        super(itemView);
        mContactCount = (TextView)itemView.findViewById(R.id.contact_count);
    }

    public final static int LayoutResource = R.layout.contact_bottom;
}
