package com.ets.thcs.easythcsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class DeleteAddressActivity extends AppCompatActivity {

    Fragment fr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_address);

        if(getIntent().getStringExtra("TAG").equals("TEACHER")){
            System.out.println("In Teacher DeleteAddressActivity");
            fr=new DeleteTeacherAddressFragment();
            setTitle("Delete TEACHER ADDRESS");

        }
        if(getIntent().getStringExtra("TAG").equals("STUDENT")){
            System.out.println("In Student DeleteAddressActivity");
            Bundle bundle=new Bundle();
            bundle.putString("studentAddressJson",getIntent().getStringExtra("studentAddressJson"));
            fr=new DeleteStudentAddressFragment();
            setTitle("Delete STUDENT ADDRESS");

        }
        getSupportFragmentManager().beginTransaction().add(R.id.fl_delete_address_frag_container, fr).commit();
    }
}