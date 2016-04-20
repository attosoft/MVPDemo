package com.attosoft.mvpdemo.ui.viewholder.contact;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.attosoft.mvpdemo.R;
import com.attosoft.mvpdemo.util.dragger.DemoApplication;

/**
 * Created by andy on 15/9/24.
 */
public class ContactGroupViewHolder extends RecyclerView.ViewHolder {

    public ImageView mStar;
    public TextView mIndex;

    public ContactGroupViewHolder(View itemView) {
        super(itemView);
        mStar = (ImageView) itemView.findViewById(R.id.star);
        mIndex = (TextView) itemView.findViewById(R.id.index);
    }

    public void setStar(boolean star) {
        Resources rs = DemoApplication.getAppContext().getResources();
        if (star) {
            mStar.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mIndex.getLayoutParams();
            params.setMargins(rs.getDimensionPixelSize(R.dimen.dp_3), 0, 0, 0);
            mIndex.setLayoutParams(params);
        } else {
            mStar.setVisibility(View.GONE);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mIndex.getLayoutParams();
            params.setMargins(rs.getDimensionPixelSize(R.dimen.dp_12), 0, 0, 0);
            mIndex.setLayoutParams(params);
        }
    }

    public final static int LayoutResource = R.layout.contact_item_group;
}
