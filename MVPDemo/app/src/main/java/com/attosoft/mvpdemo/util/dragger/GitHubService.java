package com.attosoft.mvpdemo.util.dragger;


import java.util.ArrayList;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by andy on 2016/4/13.
 */
public interface GitHubService {
    String ENDPOINT = "https://api.github.com";

    // 获取库, 获取的是数组
    @GET("/users/{user}/repos")
    Observable<ArrayList<ListAdapter.Repo>> getRepoData(@Path("user") String user);
}
