package com.attosoft.mvpdemo.util.dragger;

import android.app.Application;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andy on 2016/4/13.
 */
@Module
public class MainModule {
    private final DemoApplication mApp;

    public MainModule(DemoApplication application) {
        mApp = application;
    }

    @Provides
    @Singleton
    protected Application provideApplication() {
        return mApp;
    }

    @Provides
    @Singleton
    protected Resources provideResources() {
        return mApp.getResources();
    }
}
