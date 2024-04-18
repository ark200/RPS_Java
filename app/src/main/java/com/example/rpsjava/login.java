package com.example.rpsjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        EditText    usernameet  =   findViewById(R.id.username_login);
        EditText    passwordet  =   findViewById(R.id.password_login);
        Button      loginb      =   findViewById(R.id.login_button_main);
        Button      signupb     =   findViewById(R.id.signup_button_loginpage);


        signupb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent   = new Intent(login.this,signup.class);
                startActivity(intent);
                finish();
            }
        });

        loginb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usernameet.getText().toString().isEmpty()){
                    usernameet.setError("Username cannot be empty");
                    usernameet.requestFocus();
                    return;
                }
                else if(passwordet.getText().toString().isEmpty()){
                    passwordet.setError("Please enter the password");
                    passwordet.requestFocus();
                    return;
                }
                else{
                    String username = usernameet.getText().toString();
                    String password = passwordet.getText().toString();
                    DatabaseHelper dbHelper = new DatabaseHelper(login.this);
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?",new String[]{username,password});

                    if(cursor.getCount()>0){
                        cursor.moveToFirst();
                        Toast.makeText(login.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this,homepage.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(login.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                    }
                    cursor.close();
                    db.close();
                }
            }
        });
    }
}