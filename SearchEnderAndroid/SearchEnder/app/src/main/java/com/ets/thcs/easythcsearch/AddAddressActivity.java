package com.ets.thcs.easythcsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class AddAddressActivity extends AppCompatActivity {
    Fragment fr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        if(getIntent().getStringExtra("TAG").equals("TEACHER")){
            System.out.println("In Teacher AddAddressActivity");
            fr=new AddTeacherAddressFragment();
            setTitle("TEACHER ADDRESS");

        }
        if(getIntent().getStringExtra("TAG").equals("STUDENT")){
            System.out.println("In Student AddAddressActivity");
            fr=new AddStudentAddressFragment();
            setTitle("STUDENT ADDRESS");

        }
        getSupportFragmentManager().beginTransaction().add(R.id.fl_add_address_frag_container, fr).commit();
    }
}