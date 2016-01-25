package com.attosoft.mvpdemo.ui.view.contact;

import com.attosoft.mvpdemo.ui.adapter.item.BaseContactItem;

import java.util.List;

/**
 * Created by andy on 15/10/2.
 */
public interface IGroupIndex<T extends BaseContactItem> {
    /**
     * 计算对应的字符是属于哪个group并且获取索引
     */
    int calculatePositionByGroup(String letter);

    void updateGroupIndex(List<T> dataList);
}
