package com.android.discovery.viewtest.Listener;

public interface RetrofitListener<T> {
    void onStarted();
    void onFailed(String errorString);
    void onSuccess(T t, int flag);
    void onFinish();
}
