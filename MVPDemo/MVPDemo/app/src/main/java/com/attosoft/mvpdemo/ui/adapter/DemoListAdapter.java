package com.attosoft.mvpdemo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.attosoft.mvpdemo.App;
import com.attosoft.mvpdemo.ui.adapter.item.DemoListItem;
import com.attosoft.mvpdemo.ui.view.contact.ItemClickListener;
import com.attosoft.mvpdemo.ui.viewholder.DemoViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andy on 16/1/24.
 */
public class DemoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ItemClickListener mItemClickListener;
    private List<Integer> mData = new ArrayList<>();

    public void setOnItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setData(List<Integer> data){
        mData.clear();
        mData.addAll(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType){
            case DemoListItem.TYPE_CONTACT:
            case DemoListItem.TYPE_MAIL:
            case DemoListItem.TYPE_SETTING:
            default:
                itemView = LayoutInflater.from(App.getAppContext()).inflate(DemoViewHolder.LayoutResource,null);
                viewHolder = new DemoViewHolder(itemView);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof DemoViewHolder){
            DemoViewHolder demoViewHolder = (DemoViewHolder)holder;
            bindDemoViewHolder(demoViewHolder,position);
        }else {
            //do something
        }
    }

    private void bindDemoViewHolder(DemoViewHolder viewHolder, final int position){
        switch (getItemViewType(position)){
            case DemoListItem.TYPE_CONTACT:
                viewHolder.mIndex.setText("联系人");
                break;
            case DemoListItem.TYPE_MAIL:
                viewHolder.mIndex.setText("Email");
                break;
            case DemoListItem.TYPE_SETTING:
                viewHolder.mIndex.setText("设置");
                break;
            default:
                viewHolder.mIndex.setText("默认");
                break;
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener != null){
                    mItemClickListener.onItemClick(v,getItemViewType(position),null);
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = DemoListItem.TYPE_CONTACT;
        switch (mData.get(position)){
            case DemoListItem.TYPE_CONTACT:
                viewType = DemoListItem.TYPE_CONTACT;
                break;
            case DemoListItem.TYPE_MAIL:
                viewType = DemoListItem.TYPE_MAIL;
                break;
            case DemoListItem.TYPE_SETTING:
                viewType = DemoListItem.TYPE_SETTING;
                break;
            default:
                viewType = DemoListItem.TYPE_CONTACT;
                break;
        }
        return viewType;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
