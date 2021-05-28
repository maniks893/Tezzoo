package com.example.VasMobitec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpActivity2 extends AppCompatActivity {

    EditText input1_cstmr, input2_cstmr, input3_cstmr, input4_cstmr, input5_cstmr, input6_cstmr;
    String getotpbackend_cstmr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp2);
        final Button getotp_cstmr = findViewById(R.id.getotp_btn_cstmr);
        input1_cstmr = findViewById(R.id.otp_1_cstmr);
        input2_cstmr = findViewById(R.id.otp_2_cstmr);
        input3_cstmr = findViewById(R.id.otp_3_cstmr);
        input4_cstmr = findViewById(R.id.otp_4_cstmr);
        input5_cstmr = findViewById(R.id.otp_5_cstmr);
        input6_cstmr = findViewById(R.id.otp_6_cstmr);

        TextView textView = findViewById(R.id.show_number_cstmr);
        textView.setText(String.format(
                "+91-%s" , getIntent().getStringExtra("mobile")
        ));

        getotpbackend_cstmr = getIntent().getStringExtra("backendotp");
        final ProgressBar progressBarotp_cstmr = findViewById(R.id.progress_getotp_btn_cstmr);


        getotp_cstmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input1_cstmr.getText().toString().trim().isEmpty() && !input2_cstmr.getText().toString().trim().isEmpty() && !input3_cstmr.getText().toString().trim().isEmpty() && !input4_cstmr.getText().toString().trim().isEmpty() && !input5_cstmr.getText().toString().trim().isEmpty() && !input6_cstmr.getText().toString().trim().isEmpty()){
                    String entercodeotp_cstmr = input1_cstmr.getText().toString()+
                            input2_cstmr.getText().toString()+
                            input3_cstmr.getText().toString()+
                            input4_cstmr.getText().toString()+
                            input5_cstmr.getText().toString()+
                            input6_cstmr.getText().toString();

                    if(getotpbackend_cstmr!=null){
                        progressBarotp_cstmr.setVisibility(View.VISIBLE);
                        getotp_cstmr.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getotpbackend_cstmr, entercodeotp_cstmr
                        );

                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBarotp_cstmr.setVisibility(View.GONE);
                                        getotp_cstmr.setVisibility(View.VISIBLE);

                                        if (task.isSuccessful()){
                                            Intent intent = new Intent(getApplicationContext(), Plans_Activity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(OtpActivity2.this, "ENTER CORRECT OTP", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });

                    }else {
                        Toast.makeText(OtpActivity2.this, "PLEASE CHECK INTERNET CONNECTION", Toast.LENGTH_SHORT).show();
                    }

                    // Toast.makeText(OtpActivity.this, "otp verify",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(OtpActivity2.this, "Please enter all number",Toast.LENGTH_LONG).show();
                }


            }
        });

        numberotpmove();


        TextView resendlable_cstmr = findViewById(R.id.resend_otp_cstmr);
        resendlable_cstmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60, TimeUnit.SECONDS,
                        OtpActivity2.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {



                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(OtpActivity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendotp_cstmr, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                getotpbackend_cstmr = newbackendotp_cstmr;
                                Toast.makeText(OtpActivity2.this, "OTP SENT SUCCESSFULLY", Toast.LENGTH_SHORT).show();



                            }
                        }
                );

            }
        });




    }

    private void numberotpmove() {

        input1_cstmr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!toString().trim().isEmpty()){
                    input2_cstmr.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input2_cstmr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!toString().trim().isEmpty()){
                    input3_cstmr.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input3_cstmr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!toString().trim().isEmpty()){
                    input4_cstmr.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input4_cstmr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!toString().trim().isEmpty()){
                    input5_cstmr.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input5_cstmr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!toString().trim().isEmpty()){
                    input6_cstmr.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}