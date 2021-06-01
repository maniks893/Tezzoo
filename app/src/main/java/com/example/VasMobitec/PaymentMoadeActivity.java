package com.example.VasMobitec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentMoadeActivity extends AppCompatActivity implements PaymentResultListener
{

    Button upi, paypal, qrgen;
    ImageView rev2plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_moade);

        String sAmount = "100";
        int amount = Math.round(Float.parseFloat(sAmount)*100);
        upi = findViewById(R.id.btn_upi);
        qrgen = findViewById(R.id.btn_qrgn);
        paypal = findViewById(R.id.btn_paypal);
        rev2plan = findViewById(R.id.rev2slt);

        rev2plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),plans.class);
                startActivity(intent);
            }
        });

        qrgen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QRcodeActivity.class);
                startActivity(intent);
            }
        });

        paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_test_wcbahHwKDshljq");
                checkout.setImage(R.drawable.ic_razorpay_glyph);

                JSONObject object = new JSONObject();
                try {
                    object.put("name", "manik");
                    object.put("Description","test payment");
                    object.put("theme.color","#0093DD");
                    object.put("currency","INR");
                    object.put("amount",amount);
                    object.put("prefill.contact", "8433427159");
                    object.put("prefill.email", "maniksingh41@hotmail.com");
                    checkout.open(PaymentMoadeActivity.this,object);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        upi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),UPIActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Payment ID");
        builder.setMessage(s);
        builder.show();
        

    }

    @Override
    public void onPaymentError(int i, String s) {

        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

    }
}