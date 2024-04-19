package com.example.rpsjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

        EditText etusername         =   findViewById(R.id.username_signup);
        EditText etpassword         =   findViewById(R.id.password_signup);
        EditText etconfirmpassword  =   findViewById(R.id.confirm_password_signup);
        Button signupbt             =   findViewById(R.id.signup_main);

        etusername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String username = etusername.getText().toString();
                    if(isUsernameTaken(username)){
                        etusername.setError("USERNAME ALREADY EXISTS");
                        etusername.requestFocus();
                    }
                }
            }
        });
        signupbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etusername.getText().toString().isEmpty()){
                    etusername.setError("USERNAME CANNOT BE EMPTY");
                    etusername.requestFocus();
                    return;
                }else if(etpassword.getText().toString().isEmpty()){
                    etpassword.setError("PASSWORD CANNOT BE EMPTY");
                    etpassword.requestFocus();
                    return;
                }else if(etconfirmpassword.getText().toString().isEmpty()){
                    etconfirmpassword.setError("CONFIRM PASSWORD CANNOT BE EMPTY");
                    etconfirmpassword.requestFocus();
                }else if(!etpassword.getText().toString().equals(etconfirmpassword.getText().toString())){
                    etpassword.setError("");
                    etconfirmpassword.setError("PASSWORDS DON'T MATCH");
                }else{
                    String username         =   etusername.getText().toString();
                    String password         =   etpassword.getText().toString();

                    DatabaseHelper dbHelper = new DatabaseHelper(signup.this);
                    SQLiteDatabase db       =   dbHelper.getReadableDatabase();

                    ContentValues contentValues =   new ContentValues();
                    contentValues.put(DatabaseHelper.COL_USERNAME,username);
                    contentValues.put(DatabaseHelper.COL_PASSWORD,password);

                    long result = db.insert(DatabaseHelper.TABLE_NAME,null, contentValues);

                    if(result != -1){
                        Toast.makeText(signup.this,"SIGNUP SUCCESSFUL",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(signup.this,homepage.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(signup.this,"SIGNUP FAILED",Toast.LENGTH_SHORT).show();
                    }
                    db.close();

                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private boolean isUsernameTaken(String username){
        DatabaseHelper dbHelper = new DatabaseHelper(signup.this);
        SQLiteDatabase db       = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ DatabaseHelper.TABLE_NAME+" WHERE "+DatabaseHelper.COL_USERNAME+" =?", new String[]{username});

        boolean exists = cursor.moveToFirst();
        cursor.close();
        db.close();

        return exists;
    }
}