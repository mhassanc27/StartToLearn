package com.ets.thcs.easythcsearch;

//import androidx.core.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class AddressActivity extends AppCompatActivity {
    Fragment fr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        if(getIntent().getStringExtra("TAG").equals("TEACHER")){
            System.out.println("In Teacher AddressActivity");
            fr=new TeacherAddressFragment();
            setTitle("TEACHER ADDRESS");

        }
        if(getIntent().getStringExtra("TAG").equals("STUDENT")){
            System.out.println("In Student AddressActivity");
            fr=new StudentAddressFragment();
            setTitle("STUDENT ADDRESS");

        }
        getSupportFragmentManager().beginTransaction().add(R.id.fl_address_frag_container, fr).commit();
    }
}
