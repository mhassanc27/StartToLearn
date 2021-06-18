package com.ets.thcs.easythcsearch;

//import androidx.core.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;



public class TeacherOrStudentDetailsActivity extends AppCompatActivity {

    Fragment fr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_or_student_details);

        if (getIntent().getStringExtra("TAG").equals("TEACHER")) {
            System.out.println("In Details");
            fr = new TeacherDetailsFragment();
            setTitle("TEACHER DETAILS");
        }
        if (getIntent().getStringExtra("TAG").equals("STUDENT")) {
            System.out.println("In IF Student Details MESSAGE=> "+getIntent().getStringExtra("MESSAGE"));
            System.out.println("In IF Student Details studentJson=> "+getIntent().getStringExtra("studentJson"));

            Bundle bundle = new Bundle();
            bundle.putString("studentJson", getIntent().getStringExtra("studentJson"));

            fr = new StudentDetailsFragment();
            setTitle("STUDENT DETAILS");
        }

        getSupportFragmentManager().beginTransaction().add(R.id.fl_details_frag_container, fr).commit();
    }
}
