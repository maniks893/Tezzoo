package com.example.VasMobitec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BplanActivity extends AppCompatActivity {

    Button planB1200, planb2000, plan4000, plan8000, tpay, backb, plan_800, plan_2000;
    TextView showtotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bplan);
        planB1200 = findViewById(R.id.btn_planB1200);
        planb2000 = findViewById(R.id.btn_plan2000);
        plan4000 = findViewById(R.id.btn_plan4000);
        plan8000 = findViewById(R.id.btn_plan8000);
        tpay = findViewById(R.id.btn_total_planB);
        backb = findViewById(R.id.btn_back_B1);
        plan_2000 = findViewById(R.id.btn_plan_6mnth_2000);
        plan_800 = findViewById(R.id.btn_planB800);
        showtotal = findViewById(R.id.show_total_planb_1yr);

        planB1200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtotal.setText("1416");
            }
        });

        planb2000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtotal.setText("2610");
            }
        });

        plan4000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtotal.setText("5170");
            }
        });

        plan8000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtotal.setText("10340");
            }
        });

        plan_800.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtotal.setText("944");
            }
        });


        plan_2000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtotal.setText("2610");
            }
        });

        tpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PaymentMoadeActivity.class);
                startActivity(intent);
            }
        });

        backb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Plans_Activity.class);
                startActivity(intent);
            }
        });
    }
}