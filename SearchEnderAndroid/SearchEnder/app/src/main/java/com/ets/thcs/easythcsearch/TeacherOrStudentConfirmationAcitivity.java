package com.ets.thcs.easythcsearch;

//import androidx.core.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;



public class TeacherOrStudentConfirmationAcitivity extends AppCompatActivity {

    Fragment fr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_or_student_confirmation);

        if (getIntent().getStringExtra("TAG").equals("TEACHER")) {
            Bundle bundle = new Bundle();
            bundle.putString("CODE", getIntent().getStringExtra("CODE"));
            // set Fragmentclass Arguments
            //Fragmentclass fragobj = new Fragmentclass();



            System.out.println("In Teacher confirmation");
            fr = new ConfirmationTeacherFragment();
            fr.setArguments(bundle);
            setTitle("TEACHER CONFIRMATION");
        }
        if (getIntent().getStringExtra("TAG").equals("STUDENT")) {

            Bundle bundle = new Bundle();
            bundle.putString("CODE", getIntent().getStringExtra("CODE"));

            System.out.println("In Student confirmation");
            fr = new ConfirmationStudentFragment();
            fr.setArguments(bundle);
            setTitle("STUDENT CONFIRMATION");
        }

        getSupportFragmentManager().beginTransaction().add(R.id.fl_login_frag_container, fr).commit();
    }
}
