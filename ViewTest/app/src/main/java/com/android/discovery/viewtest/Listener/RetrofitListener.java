package com.android.discovery.viewtest.Listener;

public interface RetrofitListener<T> {
    void onStart();
    void onFailed(String errorString);
    void onSuccess(T t);
    void onFinish();
}
