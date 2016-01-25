package com.attosoft.mvpdemo.ui.view.contact;

import android.view.View;

/**
 * Created by andy on 15/9/25.
 */
public interface ItemClickListener {
    /***
     * 整条点击事件
     * @param v
     * @param position
     * @param object
     */
    void onItemClick(View v, int position, Object object);

    /**
     * 左滑删除按钮
     * @param position
     * @param object
     */
    void onItemDelete(int position, Object object);
}
