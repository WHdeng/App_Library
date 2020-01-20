package com.android.discovery.viewtest;

import com.android.discovery.viewtest.Bean.TestBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("get")
    Observable<TestBean> getUserInfo(@Query("username") String name,
                                     @Query("password") String password);
}
