package com.example.VasMobitec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AplanActivity extends AppCompatActivity {

    Button plan1200, plan800, totalA, backA;
    ImageView rev2P;
    TextView showA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplan);
        plan1200 = findViewById(R.id.btn_plan1200);
        plan800 = findViewById(R.id.btn_plan800);
        totalA = findViewById(R.id.btn_total_planA);
        backA = findViewById(R.id.btn_backA);
        showA = findViewById(R.id.show_total_A);
        rev2P = findViewById(R.id.revA);

        rev2P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),plans.class);
                startActivity(intent);
            }
        });

        plan1200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showA.setText("1416");
            }
        });

        plan800.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showA.setText("944");

            }
        });

        totalA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PaymentMoadeActivity.class);
                startActivity(intent);
            }
        });

        backA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Plans_Activity.class);
                startActivity(intent);
            }
        });



    }
}