package com.rajendra.onlineproductsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public  DBManager(Context ctx){
        context = ctx;
    }


    public  void close(){
        dbHelper.close();
    }

 
}
