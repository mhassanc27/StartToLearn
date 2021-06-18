package com.ets.thcs.easythcsearch;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class RegisterTeacherActivity extends Activity {
    String[] language ={"C","C++","Java",".NET","iPhone","Android","ASP.NET","PHP"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,language);
    }
}
