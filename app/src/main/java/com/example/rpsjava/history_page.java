package com.example.rpsjava;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class history_page extends AppCompatActivity {

    private ListView gameLogLv;
    private ArrayList<String> gameLogs;
    private ArrayAdapter<String> adapter;

//    private GameLogAdapter gameLogAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_history_page);

        gameLogLv   =   findViewById(R.id.history_list);
        gameLogs    =   new ArrayList<>();
        adapter     =   new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,gameLogs);
        gameLogLv.setAdapter(adapter);

        DatabaseHelper2 dbHelper = new DatabaseHelper2(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection ={
                DatabaseHelper2.COL_ID,
                DatabaseHelper2.COL_DATE,
                DatabaseHelper2.COL_RESULT,
                DatabaseHelper2.COL_MY_SCORE,
                DatabaseHelper2.COL_OPP_SCORE
        };
        Cursor cursor = db.query(DatabaseHelper2.TABLE_NAME,projection,null,null,null,null,null);

        while (cursor.moveToNext()){
            int id          = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper2.COL_ID));
            long date       = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper2.COL_DATE));
            String result   = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper2.COL_RESULT));
            int myscore     = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper2.COL_MY_SCORE));
            int oppscore    = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper2.COL_OPP_SCORE));

            gameLogs.add("ID: "+id +"\n"+
                    "DATE: "+date+"\n"+
                    "MY SCORE: "+myscore +"\n"+
                    "OPP SCORE: "+oppscore+"\n");
        }
        adapter.notifyDataSetChanged();
        cursor.close();
        db.close();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}