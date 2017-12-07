package com.quickvideo.quickvideo.allbasic;

/**
 * Created by Dabin on 2017/12/7.
 */

public interface BaseIPresenter<T> {
    void attach(T view);
    void detach();
}
