package com.example.VasMobitec;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class dashboard extends AppCompatActivity {

    Button resetbtn, submitbtn, savebtn;
    EditText id, name, s_name, s_add, State, City, Pincode, email, phone, gstno;
    DatabaseReference reference;
    Member member;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        resetbtn = findViewById(R.id.reset_button);
        submitbtn = findViewById(R.id.reg_button);
        id = findViewById(R.id.registration_id);
        name = findViewById(R.id.name);
        s_name = findViewById(R.id.shop_name);
        s_add = findViewById(R.id.shop_add);
        State = findViewById(R.id.state);
        City = findViewById(R.id.city);
        Pincode = findViewById(R.id.pincode);
        email = findViewById(R.id.et_email);
        phone = findViewById(R.id.et_phone);
        gstno = findViewById(R.id.et_gst);
        savebtn = findViewById(R.id.btn_save);

        member = new Member();

        reference=FirebaseDatabase.getInstance().getReference("Member");
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int r_id = Integer.parseInt(id.getText().toString().trim());
                int p_code = Integer.parseInt(Pincode.getText().toString().trim());



                member.setR_id(r_id);
                member.setName(name.getText().toString().trim());
                member.setS_name(s_name.getText().toString().trim());
                member.setS_address(s_add.getText().toString().trim());
                member.setState(State.getText().toString().trim());
                member.setCity(City.getText().toString().trim());
                member.setM_pincode(p_code);
                member.setEmail(email.getText().toString().trim());
                member.setPhn(phone.getText().toString().trim());
                member.setGst(gstno.getText().toString().trim());
                reference.push().setValue(member);
                Toast.makeText(dashboard.this, "Successfully", Toast.LENGTH_SHORT).show();

            }
        });




        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailValidator(email);


                if ( !phone.getText().toString().trim().isEmpty()){
                    if ((phone.getText().toString().trim()).length() ==10){


                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + phone.getText().toString(),
                                60, TimeUnit.SECONDS,
                                dashboard.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                        Toast.makeText(dashboard.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                        Intent intent = new Intent(getApplicationContext(),OtpActivity2.class);
                                        intent.putExtra("mobile",phone.getText().toString());
                                        intent.putExtra("backendotp",backendotp);


                                        startActivity(intent);

                                    }
                                }
                        );



                        Intent intent = new Intent(getApplicationContext(),OtpActivity2.class);
                        intent.putExtra("mobile",phone.getText().toString());
                        startActivity(intent);
                    } else {
                        Toast.makeText(dashboard.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(dashboard.this, "Enter UserDetails", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id.setText("");
                name.setText("");
                s_name.setText("");
                s_add.setText("");
                State.setText("");
                City.setText("");
                Pincode.setText("");
                email.setText("");
                phone.setText("");
                gstno.setText("");

                Toast.makeText(dashboard.this, "Rest", Toast.LENGTH_SHORT).show();



            }
        });
    }
    public void emailValidator(EditText email) {


        String emailToText = email.getText().toString();


        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            Toast.makeText(this, "Email Verified !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
        }
    }


}