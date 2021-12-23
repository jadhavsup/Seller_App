package com.example.e_krushi_sellerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hbb20.CountryCodePicker;

public class LoginScreen extends AppCompatActivity {
    Button b1;
    CountryCodePicker ccp;
    EditText t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        b1=findViewById(R.id.b1);
        t1=findViewById(R.id.t1);
        ccp=findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(t1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginScreen.this,Otp_Verification.class);
                intent.putExtra("mobile", ccp.getFullNumberWithPlus().replace(" ", ""));
                startActivity(intent);
            }
        });
    }
}