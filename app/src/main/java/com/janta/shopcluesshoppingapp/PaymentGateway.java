package com.janta.shopcluesshoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.janta.shopcluesshoppingapp.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentGateway extends AppCompatActivity implements View.OnClickListener, PaymentResultListener {
    private LinearLayout mRazorPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);
        initViews();

    }

    private void initViews() {
        mRazorPay = findViewById(R.id.razorPay);
        mRazorPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
    }

    private void startPayment() {
        String  amtStr = Integer.toString(500*100);
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_MVo8XLbqFup11L");
        checkout.setImage(R.drawable.rzp_logo);
        final Activity activity = this;
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Abhishek");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", amtStr);//pass amount in currency subunits
            options.put("prefill.email", "gaurav.kumar@example.com");
            options.put("prefill.contact","7777011329");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("TAG Payment", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        showCustomDialog();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed! Due to " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
    }

    void showCustomDialog() {
        LayoutInflater li = LayoutInflater.from(PaymentGateway.this);
        View view = li.inflate(R.layout.custom_popup, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(view);
        Button btnContinue = view.findViewById(R.id.btnCustomPopup);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentGateway.this, OrderConfirmed.class));
            }
        });

        alertDialogBuilder.create().show();
    }
}