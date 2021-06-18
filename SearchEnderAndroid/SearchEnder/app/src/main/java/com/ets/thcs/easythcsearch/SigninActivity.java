package com.ets.thcs.easythcsearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SigninActivity extends Activity {

    private Button btnSignInTeacher;
    private Button btnSignInStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        btnSignInTeacher = (Button) findViewById(R.id.btn_signinTeacher1);
        btnSignInStudent = (Button) findViewById(R.id.btn_signinStudent1);
        System.out.println("Before Button clicked");
        btnSignInTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),TeacherOrStudentSignInActivity.class);
                i.putExtra("TAG","TEACHER");
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });


        btnSignInStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("btnSignIn clicked");
                Intent i = new Intent(getApplicationContext(),TeacherOrStudentSignInActivity.class);
                i.putExtra("TAG","STUDENT");
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


            }
        });
    }
}
