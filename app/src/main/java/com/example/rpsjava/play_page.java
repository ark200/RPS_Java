package com.example.rpsjava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class play_page extends AppCompatActivity {

    private GameUtils gameUtils;
    private DatabaseHelper2 databaseHelper2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_play_page);

        databaseHelper2 = new DatabaseHelper2(this);

        int winning_score = getIntent().getIntExtra("winning_score",3);
        gameUtils = new GameUtils(winning_score, this);

        Button rock         = findViewById(R.id.rock_button);
        Button paper        = findViewById(R.id.paper_button);
        Button scissors     = findViewById(R.id.scissors_button);

        TextView resulttv   = findViewById(R.id.game_result_text_view);
        TextView myscoretv  = findViewById(R.id.my_score_text_view);
        TextView oppscoretv = findViewById(R.id.opponent_score_text_view);

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = gameUtils.play("Rock");
                updateUI(result, gameUtils.getMy_score(), gameUtils.getOpp_score(), resulttv, myscoretv, oppscoretv);
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = gameUtils.play("Paper");
                updateUI(result, gameUtils.getMy_score(), gameUtils.getOpp_score(), resulttv, myscoretv, oppscoretv);
            }
        });
        scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = gameUtils.play("Scissors");
                updateUI(result, gameUtils.getMy_score(), gameUtils.getOpp_score(), resulttv, myscoretv, oppscoretv);
            }
        });
    }

    private void updateUI(String result, int myscore, int oppscore, TextView resultv, TextView myscoretv, TextView oppscoretv){
        resultv.setText(result);
        myscoretv.setText("MY SCORE: "+ myscore);
        oppscoretv.setText("OPPONENT SCORE: "+oppscore);

        if(myscore>= gameUtils.getWinning_score() || oppscore>=gameUtils.getWinning_score()){
            if(myscore == gameUtils.getWinning_score()){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String currentDate   = sdf.format(new Date());
                DatabaseHelper2 dbHelper = new DatabaseHelper2(play_page.this);
                SQLiteDatabase db        = dbHelper.getReadableDatabase();

                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper2.COL_DATE,currentDate);
                contentValues.put(DatabaseHelper2.COL_OPP_SCORE,oppscore);
                contentValues.put(DatabaseHelper2.COL_MY_SCORE,myscore);
                contentValues.put(DatabaseHelper2.COL_RESULT,"WON");
                long status = db.insert(DatabaseHelper2.TABLE_NAME, null, contentValues);
                if(status != -1){
                    Toast.makeText(play_page.this,"RECORD SAVED",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(play_page.this,"RECORD NOT SAVED",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(play_page.this,"CONGRATS, YOU WON!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(play_page.this,homepage.class);
                startActivity(intent);
                finish();
            }else if(oppscore == gameUtils.getWinning_score()){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String currentDate   = sdf.format(new Date());
                DatabaseHelper2 dbHelper = new DatabaseHelper2(play_page.this);
                SQLiteDatabase db        = dbHelper.getReadableDatabase();

                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper2.COL_DATE,currentDate);
                contentValues.put(DatabaseHelper2.COL_OPP_SCORE,oppscore);
                contentValues.put(DatabaseHelper2.COL_MY_SCORE,myscore);
                contentValues.put(DatabaseHelper2.COL_RESULT,"LOST");
                long status = db.insert(DatabaseHelper2.TABLE_NAME, null, contentValues);
                if(status != -1){
                    Toast.makeText(play_page.this,"RECORD SAVED",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(play_page.this,"RECORD NOT SAVED",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(play_page.this,"OPPONENT WON!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(play_page.this,homepage.class);
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}