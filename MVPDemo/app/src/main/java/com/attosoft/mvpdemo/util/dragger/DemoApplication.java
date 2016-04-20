package com.attosoft.mvpdemo.util.dragger;

import android.app.Application;

/**
 * Created by andy on 2016/4/13.
 */
public class DemoApplication extends Application{
    private static DemoGraph sDemoGraph;
    private static DemoApplication sInstance;

    @Override public void onCreate() {
        super.onCreate();
        sInstance = this;
        buildComponentAndInject();
    }

    public static DemoGraph component() {
        return sDemoGraph;
    }

    public static void buildComponentAndInject() {
        sDemoGraph = DemoComponent.Initializer.init(sInstance);
    }

    public static DemoApplication getAppContext(){
        return sInstance;
    }
}
