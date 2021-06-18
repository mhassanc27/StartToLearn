package com.ets.thcs.easythcsearch;

//import androidx.core.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;



public class TeacherOrStudentRegisterActivity extends AppCompatActivity {

    Fragment fr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_or_student_register);

        if (getIntent().getStringExtra("TAG").equals("TEACHER")) {
            System.out.println("In login");
            fr = new RegisterTeacherFragment();
            setTitle("TEACHER");
        }
        if (getIntent().getStringExtra("TAG").equals("STUDENT")) {
            fr = new RegisterStudentFragment();
            setTitle("STUDENT");
        }

        getSupportFragmentManager().beginTransaction().add(R.id.fl_login_frag_container, fr).commit();
    }
}
