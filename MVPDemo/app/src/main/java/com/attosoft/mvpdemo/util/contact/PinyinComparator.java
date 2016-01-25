package com.attosoft.mvpdemo.util.contact;


import com.attosoft.mvpdemo.ui.adapter.item.BaseContactItem;

import java.util.Comparator;

/**
 * Created by andy on 15/9/27.
 */
public class PinyinComparator implements Comparator<BaseContactItem> {
    @Override
    public int compare(BaseContactItem lhs, BaseContactItem rhs) {
        for (int i = 0; i < lhs.sortKey.length() && i < rhs.sortKey.length(); i++) {

            int codePoint1 = lhs.sortKey.charAt(i);
            int codePoint2 = rhs.sortKey.charAt(i);

            if (Character.isSupplementaryCodePoint(codePoint1)
                    || Character.isSupplementaryCodePoint(codePoint2)) {
                i++;
            }

            if (codePoint1 != codePoint2) {
                if (Character.isSupplementaryCodePoint(codePoint1)
                        || Character.isSupplementaryCodePoint(codePoint2)) {
                    return codePoint1 - codePoint2;
                }
                return codePoint1 - codePoint2;
            }
        }
        return lhs.sortKey.length() - rhs.sortKey.length();
    }
}
