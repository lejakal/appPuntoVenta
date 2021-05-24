package com.example.danielreyes.TAT.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "androidsqlite.db";
    public static final int DATABASE_VERSION = 8;
    private SQLiteDatabase db;

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public ConexionSQLiteHelper(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, "androidsqlite.db", null, 8);
    }
}
