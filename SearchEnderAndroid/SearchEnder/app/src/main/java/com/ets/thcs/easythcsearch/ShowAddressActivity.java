package com.ets.thcs.easythcsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class ShowAddressActivity extends AppCompatActivity {
    Fragment fr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_address);

        if(getIntent().getStringExtra("TAG").equals("TEACHER")){
            System.out.println("In Teacher ShowAddressActivity");
            fr=new ShowTeacherAddressFragment();
            setTitle("TEACHER ADDRESS");

        }
        if(getIntent().getStringExtra("TAG").equals("STUDENT")){
            System.out.println("In Student ShowAddressActivity");
            Bundle bundle = new Bundle();
            bundle.putString("studentJson", getIntent().getStringExtra("studentJson"));

            fr=new ShowStudentAddressFragment();
            setTitle("STUDENT ADDRESS");

        }
        getSupportFragmentManager().beginTransaction().add(R.id.fl_show_address_frag_container, fr).commit();
    }
}