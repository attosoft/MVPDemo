package com.attosoft.mvpdemo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.attosoft.mvpdemo.ui.adapter.item.BaseContactItem;
import com.attosoft.mvpdemo.ui.view.contact.IGroupIndex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andy on 15/10/2.
 */
public class BaseContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements IGroupIndex {

    protected String[] mLetterList = {"↑","★", "#","A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    /**字母索引*/
    protected Map<String,Integer> mGroupIndex = new HashMap<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int calculatePositionByGroup(String letter) {
        if(mGroupIndex.containsKey(letter)){
            return mGroupIndex.get(letter);
        }
        return -1;
    }

    @Override
    public void updateGroupIndex(List dataList) {
        mGroupIndex.clear();
        for(int i=0;i<dataList.size();i++){
            if(dataList.get(i) instanceof BaseContactItem){
                BaseContactItem item = (BaseContactItem)dataList.get(i);
                if(item.isGroup()){
                    mGroupIndex.put(item.sortKey.toUpperCase(),i);
                }
            }else {
                //
            }
        }
        mGroupIndex.put(mLetterList[0],0);
    }

}
