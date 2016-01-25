package com.attosoft.mvpdemo.ui.viewholder;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.attosoft.mvpdemo.App;
import com.attosoft.mvpdemo.R;

/**
 * Created by ruyaya on 16/1/24.
 */
public class DemoViewHolder extends RecyclerView.ViewHolder{

    public ImageView mStar;
    public TextView mIndex;

    public DemoViewHolder(View itemView) {
        super(itemView);
        mStar = (ImageView) itemView.findViewById(R.id.star);
        mIndex = (TextView) itemView.findViewById(R.id.index);
    }

    public void setStar(boolean star) {
        Resources rs = App.getAppContext().getResources();
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
