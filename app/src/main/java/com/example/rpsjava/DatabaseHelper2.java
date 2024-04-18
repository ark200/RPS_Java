package com.example.rpsjava;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper2 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "game_log";
    public static final String TABLE_NAME = "gamelogs";
    public static final String COL_ID = "id";
    public static final String COL_DATE ="date";
    public static final String COL_RESULT = "result";
    public static final String COL_MY_SCORE = "my_score";
    public static final String COL_OPP_SCORE =  "opp_score";

    public static final int DATABASE_VERSION =1;
    private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + "("+
            COL_ID + "INTERGER PRIMARY KEY AUTOINCREMENT, " +
            COL_DATE + "INTEGER NOT NULL, " +
            COL_RESULT + "TEXT NOT NULL, " +
            COL_MY_SCORE + "INTEGER NOT NULL, " +
            COL_OPP_SCORE + "INTEGER NOT NULL);";


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

    public void addGameLog(long date, String result, int myScore, int oppScore){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_DATE, date);
        values.put(COL_RESULT, result);
        values.put(COL_MY_SCORE, myScore);
        values.put(COL_OPP_SCORE, oppScore);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}
