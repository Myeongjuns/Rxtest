package com.gmail.blackbull8810.test01.MVP.model;

import com.gmail.blackbull8810.test01.MVP.data.Info;
import com.gmail.blackbull8810.test01.util.RetrofitFactory;

import io.reactivex.Observable;
import retrofit2.Converter;
import retrofit2.http.GET;

public interface InfoListApiService {

    @GET("/test")
    Observable<Info> getInfoList();

    class Factory extends Converter.Factory {
        public static InfoListApiService create() {

            return RetrofitFactory.initRetrofit().create(InfoListApiService.class);
        }
    }
}
