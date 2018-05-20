package com.gmail.blackbull8810.test01.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mkr on 2017-08-21.
 */

public class Variable {

    public final static String _SERVER_HOST = "http://www.bsnamgu.go.kr/openapi/service/";
    public final static String _SERVER_HOST_RPI = "http://211.193.71.67:3000/";

    //공공데이터 기본형
    //http://www.bsnamgu.go.kr/openapi/service/GoodPriceRestaurant/getGoodPriceRestaurant?serviceKey=7ByNsgT7GpyYneqohtIpIW%2FYA0tT4hqj37t9AkolPGOnORW15sraDkiAC2VrUlNCfBOqmJEOV9ufRSfydGwUcA%3D%3D&numOfRows=56&pageSize=10&pageNo=1&startPage=1

    public final static String _GOODFOOD_SERVICE_KEY = "7ByNsgT7GpyYneqohtIpIW%2FYA0tT4hqj37t9AkolPGOnORW15sraDkiAC2VrUlNCfBOqmJEOV9ufRSfydGwUcA%3D%3D";
    public final static String _GOODFOOD_LIST_SERVER_REQUEST_URL = "GoodPriceRestaurant/getGoodPriceRestaurant?numOfRows=56";

    public final static String _GOODFOOD_HOMEPAGE_URL = "http://www.bsnamgu.go.kr/board/list.namgu?boardId=BBS_0000075&menuCd=DOM_000000104003008000&contentsSid=197";

    public static final String WIFE_STATE = "WIFI";
    public static final String MOBILE_STATE = "MOBILE";
    public static final String NONE_STATE = "NONE";

    public Variable() throws UnsupportedEncodingException {
    }

    public static String getWhatKindOfNetwork(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return WIFE_STATE;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return MOBILE_STATE;
            }
        }
        return NONE_STATE;
    }

    private static class CheckConnect extends Thread {
        private boolean success;
        private String host;

        public CheckConnect(String host){
            this.host = host;
        }

        @Override
        public void run() {

            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection)new URL(host).openConnection();
                conn.setRequestProperty("User-Agent","Android");
                conn.setConnectTimeout(1000);
                conn.connect();
                int responseCode = conn.getResponseCode();
                if(responseCode == 204) success = true;
                else success = false;
            }
            catch (Exception e) {
                e.printStackTrace();
                success = false;
            }
            if(conn != null){
                conn.disconnect();
            }
        }

        public boolean isSuccess(){
            return success;
        }
    }
    public static final String CONNECTION_CONFIRM_CLIENT_URL = "http://clients3.google.com/generate_204";


    public static boolean isOnline(String Url) {
        CheckConnect cc = new CheckConnect(Url);
        cc.start();
        try{
            cc.join();
            return cc.isSuccess();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}