package com.gmail.blackbull8810.test01.MVP.model;

import android.content.Context;

import com.gmail.blackbull8810.test01.MVP.data.Info;

import java.io.UnsupportedEncodingException;

import io.reactivex.Observable;

public class InfoModel {

    Context mContext;

    public InfoModel(Context context) {
        mContext = context;
    }
    public Observable<Info> getInfoList() throws UnsupportedEncodingException {

        return InfoListApiService.Factory.create().getInfoList();
    }
}
