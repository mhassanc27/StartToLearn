package com.ets.thcs.easythcsearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends Activity {

    private Button btnRegisterTeacher;
    private Button btnRegisterStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegisterTeacher = (Button) findViewById(R.id.btn_registerTeacher);
        btnRegisterStudent = (Button) findViewById(R.id.btn_registerStudent);
        System.out.println("Before Button clicked");
        btnRegisterTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),TeacherOrStudentRegisterActivity.class);
                i.putExtra("TAG","TEACHER");
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });


        btnRegisterStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("btnSignIn clicked");
                Intent i = new Intent(getApplicationContext(),TeacherOrStudentRegisterActivity.class);
                i.putExtra("TAG","STUDENT");
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


            }
        });
    }
}
