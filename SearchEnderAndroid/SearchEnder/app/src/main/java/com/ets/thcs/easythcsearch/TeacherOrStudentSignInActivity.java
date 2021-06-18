package com.ets.thcs.easythcsearch;

//import androidx.core.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;

public class TeacherOrStudentSignInActivity extends AppCompatActivity {
    Fragment fr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_or_student_sign_in);

        if (getIntent().getStringExtra("TAG").equals("TEACHER")) {
            System.out.println("In login");
            fr = new SignInTeacherFragment();
            setTitle("TEACHER");
        }
        if (getIntent().getStringExtra("TAG").equals("STUDENT")) {
            System.out.println("In Student login");
            fr = new SignInStudentFragment();
            setTitle("STUDENT");
        }

        getSupportFragmentManager().beginTransaction().add(R.id.fl_signin_frag_container, fr).commit();
    }
}
