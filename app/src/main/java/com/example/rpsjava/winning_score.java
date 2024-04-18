package com.example.rpsjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class winning_score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_winning_score);

        EditText winning_scoreet    =   findViewById(R.id.winning_scoreet);
        Button proceedbt            =   findViewById(R.id.proceed_winning_score);


        proceedbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(winning_scoreet.getText().toString().isEmpty()){
                    Toast.makeText(winning_score.this,"Please enter a Winning Score",Toast.LENGTH_SHORT).show();
                }
                else {
                    int winning_score = Integer.parseInt(winning_scoreet.getText().toString());
                    if(winning_score<3){
                        Toast.makeText(winning_score.this,"Minimum Winning Score is 3", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(winning_score.this, play_page.class);
                        intent.putExtra("winning_score", winning_score);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}