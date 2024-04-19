package com.example.rpsjava;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper2 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "game_log";
    public static final String TABLE_NAME = "gamelogs";
    public static final String COL_ID = "id";
    public static final String COL_DATE ="date";
    public static final String COL_RESULT = "result";
    public static final String COL_MY_SCORE = "my_score";
    public static final String COL_OPP_SCORE =  "opp_score";

    public static final int DATABASE_VERSION =1;

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_DATE + " INTEGER NOT NULL, " +
            COL_RESULT + " TEXT NOT NULL, " +
            COL_MY_SCORE + " INTEGER NOT NULL, " +
            COL_OPP_SCORE + " INTEGER NOT NULL);";


    public DatabaseHelper2(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
