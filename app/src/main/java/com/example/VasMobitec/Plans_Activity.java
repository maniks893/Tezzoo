package com.example.VasMobitec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Plans_Activity extends AppCompatActivity {

    Button planA, planB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans);
        planA = findViewById(R.id.btn_planA);
        planB = findViewById(R.id.btn_planB);

        planA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AplanActivity.class);
                startActivity(intent);

            }
        });
        planB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BplanActivity.class);
                startActivity(intent);

            }
        });
    }


}