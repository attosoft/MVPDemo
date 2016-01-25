package com.attosoft.mvpdemo.ui.viewholder.contact;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.attosoft.mvpdemo.R;

/**
 * Created by andy on 15/9/28.
 */
public class ContactImportViewHolder extends RecyclerView.ViewHolder {

    public TextView mImport;

    public ContactImportViewHolder(View itemView) {
        super(itemView);
        mImport = (TextView)itemView.findViewById(R.id.import_phone_contact);
    }
    public final static int LayoutResource = R.layout.contact_import_phone_contact;
}
