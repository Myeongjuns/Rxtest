package com.gmail.blackbull8810.test01.util.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gmail.blackbull8810.test01.util.Logger;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by mkr on 2017-08-22.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "busan.db";
    public final static String _TABLE_NAME_BOOKMARK = "bookmark";
    private static final int DATABASE_VERSION = 2; //2가 기본값
    private Context context;
    SQLiteDatabase db;
    private static DBHelper instance;
    CompositeSubscription compositeSubscription;

//    public MessageDBController messageDBController;
//    public BestFriendDBController bestFriendDBController;
//    public MyRoomDBController myRoomDBController;

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null)
            instance = new DBHelper(context);
        Logger.log("#123 DBHelper getInstance");
        return instance;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        Logger.log("#123 private DBHelper");
        compositeSubscription = new CompositeSubscription();
//        messageDBController = new MessageDBController(compositeSubscription, this);
//        bestFriendDBController = new BestFriendDBController(compositeSubscription, this);
//        myRoomDBController = new MyRoomDBController(compositeSubscription, this);

    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Logger.log("#123 onCreate db");
        this.db = db;
        String CRETE_TABLE1 = "create table IF NOT EXISTS " + _TABLE_NAME_BOOKMARK + "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,"
                + "name VARCHAR UNIQUE,"
                + "ceo VARCHAR default '',"
                + "addr VARCHAR default '',"
                + "tel VARCHAR default '',"
                + "img_url VARCHAR default ''"
                + ")";


        try {
            db.execSQL(CRETE_TABLE1);
//            db.execSQL(MessageDBController.CREATE_MESSAGE_TABLE);
            Logger.log("#123 database 생성.");

        } catch (Exception e) {
            e.printStackTrace();
            Logger.log("#123 database 실패.");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Logger.log("onUpgrade db -> " + oldVersion + ">>>" + newVersion);
        if(oldVersion >= newVersion)
            return;
        onCreate(db);
    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public void openDB() {
        if (db == null || !db.isOpen()) {
            db = getWritableDatabase();
//            messageDBController.open(db);
//            bestFriendDBController.open(db);
//            myRoomDBController.open(db);
        }
    }

    public void closeDB() {
        if (db != null && db.isOpen()) {
            db.close();
            db = null;
            compositeSubscription.clear();
            Logger.log("#1500 close db done");
        }
    }

    public boolean isDBOpend() {
        if (db != null) {
            return db.isOpen();
        }

        return false;
    }

    public void addBookMark(String name, String ceo, String addr, String tel, String img_url) {

        String query = "INSERT INTO bookmark (name,ceo,addr,tel,img_url) values( ? , ? , ? , ? , ?)";
        db.execSQL(query, new String[]{name + "", ceo + "", addr + "" , tel + "" , img_url + ""});
    }

    public void deleteBookMark(String name) {
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM bookmark WHERE name='" + name + "';");
        //db.close();

    }
    public void deleteAllData() {
        String query = "DELETE FROM " + _TABLE_NAME_BOOKMARK;
        db.execSQL(query);
    }
    public String getResult() {
        String result = "";
        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM bookmark", null);
        while (cursor.moveToNext()) {
            result += cursor.getInt(0)
                    + " , "
                    + cursor.getString(1)
                    + " , "
                    + cursor.getString(2)
                    + " , "
                    + cursor.getString(3)
                    + " , "
                    + cursor.getString(4)
                    + " , "
                    + cursor.getString(5)
                    + " , "
                    + cursor.getString(6)
                    + " , "
                    + cursor.getString(7)
                    + "\n";
        }
        return result;
    }
    public String getName() {
        String result = "";
        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM bookmark", null);
//        Cursor cursor = db.rawQuery("SELECT * FROM bookmark WHERE name = " + name , null);
        while (cursor.moveToNext()) {
            result = cursor.getString(1);
        }
        return result;
    }

    public String compareTitle(String title) {

        String result = "";
        String sql = "SELECT COUNT(DISTINCT name) FROM bookmark WHERE name IN (?)";
        Cursor cursor = db.rawQuery(sql, new String[]{title});
//        String result = "";
//        Cursor cursor = db.rawQuery("SELECT COUNT(DISTINCT name) FROM bookmark WHERE name IN (?)", null);

        while (cursor.moveToNext()) {
            result = cursor.getString(0);
        }
        return result;
    }


}
