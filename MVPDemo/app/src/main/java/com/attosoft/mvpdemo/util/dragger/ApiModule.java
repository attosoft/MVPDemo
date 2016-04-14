package com.attosoft.mvpdemo.util.dragger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by andy on 2016/4/13.
 */
@Module
public class ApiModule {
    @Provides
    @Singleton
    protected GitHubService provideGitHubService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitHubService.ENDPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .build();
        return retrofit.create(GitHubService.class);
    }
}
