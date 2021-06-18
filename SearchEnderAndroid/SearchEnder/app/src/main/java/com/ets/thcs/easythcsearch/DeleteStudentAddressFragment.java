package com.ets.thcs.easythcsearch;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ets.thcs.easythcsearch.helper.URLs;
import com.ets.thcs.easythcsearch.model.StudentAddressVo;
import com.ets.thcs.easythcsearch.util.WebserviceCallAppData;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeleteStudentAddressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteStudentAddressFragment extends Fragment {

    ProgressDialog pdScreen1;
    private String studentAddressJson;
    private StudentAddressVo studentAddressVo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_delete_student_address, container, false);
        System.out.println("In DeleteStudentAddressFragment");
        studentAddressJson=getActivity().getIntent().getStringExtra("studentAddressJson");
        studentAddressVo=new Gson().fromJson(studentAddressJson, StudentAddressVo.class);

        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage("Do you want to delete this address?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String BASE_URI = URLs.URL_DELETE_STUDENT_ADDRESS;
                System.out.println("getActivity()=> "+getActivity());
                pdScreen1 = new ProgressDialog(getActivity());
                pdScreen1.setIndeterminate(true);
                pdScreen1.setCancelable(false);
                pdScreen1.setMessage("Please wait, processing...");
                pdScreen1.show();
                new WebserviceCallAppData(getActivity(),ShowAddressActivity.class,"DELETE",pdScreen1).execute(BASE_URI,studentAddressJson);

            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(getActivity().getApplicationContext(),ShowAddressActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("TAG","STUDENT");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });
        alertDialogBuilder.show();
        return rootView;
    }
}