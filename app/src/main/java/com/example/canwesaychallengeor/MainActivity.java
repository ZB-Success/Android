package com.example.canwesaychallengeor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText uname,password;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= findViewById(R.id.btn);
        uname=findViewById(R.id.Username);
        password=findViewById(R.id.password);

        // we have assumed one admin

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uname.getText().toString().equals("")){
                    uname.setError("This field is required");
                }
                if(password.getText().toString().equals("")){
                    password.setError("This field is required");
                }
                if(uname.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Toast.makeText(MainActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(MainActivity.this, RecyclerAdminView.class);// this is RecyclerAdminView not adminpage
                    startActivity(intent);
                }

            }
        });

    }
}