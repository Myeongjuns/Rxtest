package com.gmail.blackbull8810.test01.common.presenter;

/**
 * Created by dev on 2016-12-09.
 */

public interface Presenter<T> {

    void attachView(T view);

    void detachView();

    void notConnectNetworking();

}
