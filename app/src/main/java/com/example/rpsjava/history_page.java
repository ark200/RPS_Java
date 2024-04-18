package com.example.rpsjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.List;

public class history_page extends AppCompatActivity {

    private ListView gameLogLv;
    private GameLogAdapter gameLogAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_history_page);

        gameLogLv   =   findViewById(R.id.history_list);

        DatabaseHelper2 dbHelper = new DatabaseHelper2(this);
    }
}