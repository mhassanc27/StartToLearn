package com.ets.thcs.easythcsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class EditAddressActivity extends AppCompatActivity {
    Fragment fr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        if(getIntent().getStringExtra("TAG").equals("TEACHER")){
            System.out.println("In Teacher EditAddressActivity");
            fr=new EditTeacherAddressFragment();
            setTitle("TEACHER ADDRESS");

        }
        if(getIntent().getStringExtra("TAG").equals("STUDENT")){
            System.out.println("In Student EditAddressActivity");
            Bundle bundle=new Bundle();
            bundle.putString("studentAddressJson",getIntent().getStringExtra("studentAddressJson"));
            fr=new EditStudentAddressFragment();
            setTitle("STUDENT ADDRESS");

        }
        getSupportFragmentManager().beginTransaction().add(R.id.fl_edit_address_frag_container, fr).commit();
    }
}