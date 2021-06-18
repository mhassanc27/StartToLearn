package com.ets.thcs.easythcsearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PromtLoginActivity extends Activity {

    private Button btnSignIn;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("In PromtLoginActivity OnCreate");
        setContentView(R.layout.activity_promt_login);

        btnRegister = (Button) findViewById(R.id.btn_register);
        btnSignIn = (Button) findViewById(R.id.btn_sign_in);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                i.putExtra("TAG","REGISTER");
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("btnSignIn clicked");
                Intent i = new Intent(getApplicationContext(),SigninActivity.class);
                i.putExtra("TAG","LOGIN");
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


            }
        });
    }
}
