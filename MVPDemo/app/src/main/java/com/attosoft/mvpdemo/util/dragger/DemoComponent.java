package com.attosoft.mvpdemo.util.dragger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andy on 2016/4/13.
 */
@Singleton
@Component(modules = {MainModule.class, ApiModule.class})
public interface DemoComponent extends DemoGraph{
    final class Initializer {
        private Initializer() {
        } // No instances.

        // 初始化组件
        public static DemoComponent init(DemoApplication app) {
            return DaggerDemoComponent.builder()
                    .mainModule(new MainModule(app))
                    .build();
        }
    }
}
