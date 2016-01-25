package com.attosoft.mvpdemo;

import android.app.Application;

/**
 * Created by ruyaya on 16/1/24.
 */
public class App extends Application{

    private static App mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static App getAppContext(){
        return mContext;
    }
}
