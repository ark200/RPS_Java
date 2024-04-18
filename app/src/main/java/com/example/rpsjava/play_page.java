package com.example.rpsjava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

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
            GameLog gameLog = new GameLog(System.currentTimeMillis(),gameUtils.getGameResult(),myscore,oppscore);
            SQLiteDatabase db = databaseHelper2.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper2.COL_DATE,gameLog.getDate());
            values.put(DatabaseHelper2.COL_RESULT,gameLog.getResult());
            values.put(DatabaseHelper2.COL_MY_SCORE,gameLog.getMyScore());
            values.put(DatabaseHelper2.COL_OPP_SCORE,gameLog.getOppScore());
            db.insert(DatabaseHelper2.TABLE_NAME,null,values);
            db.close();
            endGame(myscore, oppscore);

        }
    }

    private void endGame(int myScore, int oppScore){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("GAME OVER");
        builder.setMessage("MY SCORE: "+ myScore + "\nOPPONENT SCORE: "+oppScore);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper2.addGameLog(System.currentTimeMillis(), gameUtils.getGameResult(),myScore,oppScore);
                finish();
            }
        });
    }
}