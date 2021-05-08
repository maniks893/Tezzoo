package com.example.teezoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText enternumber;
    Button sendbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enternumber = findViewById(R.id.otp);
        sendbtn = findViewById(R.id.otp_btn);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( !enternumber.getText().toString().trim().isEmpty()){
                    if ((enternumber.getText().toString().trim()).length() ==10){
                        Intent intent = new Intent(getApplicationContext(),OtpActivity.class);
                        intent.putExtra("mobile",enternumber.getText().toString());
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}