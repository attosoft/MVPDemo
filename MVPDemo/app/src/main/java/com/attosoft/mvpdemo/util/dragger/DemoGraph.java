package com.attosoft.mvpdemo.util.dragger;

import com.attosoft.mvpdemo.ui.activity.EmailListActivity;
import com.attosoft.mvpdemo.ui.activity.MainActivity;

/**
 * Dagger2的图接口
 * Created by andy on 2016/4/13.
 */
public interface DemoGraph {
    void inject(MainActivity mainActivity); // 注入MainActivity

    void inject(EmailListActivity emailListActivity); // 注入列表Activity
}
