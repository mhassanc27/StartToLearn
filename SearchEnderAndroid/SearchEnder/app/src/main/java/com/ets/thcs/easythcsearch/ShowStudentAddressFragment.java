package com.ets.thcs.easythcsearch;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ets.thcs.easythcsearch.helper.Constants;
import com.ets.thcs.easythcsearch.helper.URLs;
import com.ets.thcs.easythcsearch.model.StudentAddressVo;
import com.ets.thcs.easythcsearch.model.StudentVo;
import com.ets.thcs.easythcsearch.util.RecyclerViewAdapter;
import com.ets.thcs.easythcsearch.util.WebserviceCallAppData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowStudentAddressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowStudentAddressFragment extends Fragment {

    private Button btnEditAddress;
    private Button btnDeleteAddress;
    private Button btnAddAddress;
    private RecyclerView rvAddressList;

    private ProgressDialog pdScreen1;

    private String studentJson;
    private StudentVo studentVo;
    private List<StudentAddressVo> studentAddressVoList;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootVeiw=inflater.inflate(R.layout.fragment_show_student_address, container, false);

        //btnEditAddress=(Button) rootVeiw.findViewById(R.id.btn_edit_address);
        //btnDeleteAddress=(Button) rootVeiw.findViewById(R.id.btn_delete_address);
        btnAddAddress=(Button) rootVeiw.findViewById(R.id.btn_add_address);
        rvAddressList=(RecyclerView) rootVeiw.findViewById(R.id.rv_address_list);
        studentAddressVoList=new ArrayList<>();
        recyclerViewAdapter=new RecyclerViewAdapter(studentAddressVoList,getContext());
        rvAddressList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAddressList.setAdapter(recyclerViewAdapter);


        //Get the Student Data

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.LOGIN_INFO, Context.MODE_PRIVATE);
        studentJson=sharedPreferences.getString("studentJson","");

        if(studentJson!=null && studentJson!=""){
            System.out.println("In ShowStudentAddressFragment studentJson=> "+studentJson);
            Gson gson=new Gson();
            studentVo=gson.fromJson(studentJson, StudentVo.class);
            System.out.println("In ShowStudentAddressFragment studentVo=>"+studentVo);
        }

        //studentJson=getActivity().getIntent().getStringExtra("studentJson");


        //Get the data by calling REST service (getAddressByStudentId)
        if(studentVo!=null && studentVo.getId()>0){
            String BASE_URI = URLs.URL_READ_STUDENT_ADDRESS_BY_STUDENTID+"/"+studentVo.getId();
            System.out.println("getActivity()=> "+getActivity());
            pdScreen1 = new ProgressDialog(getActivity());
            pdScreen1.setIndeterminate(true);
            pdScreen1.setCancelable(false);
            pdScreen1.setMessage("Please wait, getting student addresses...");
            pdScreen1.show();

            //new WebserviceCallAppData(getActivity(),null,"GET",pdScreen1).execute(BASE_URI);

            //Calling REST Services using Volley
            RequestQueue requestQueue= Volley.newRequestQueue(getContext());
            JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, BASE_URI, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try{
                        if(response!=null){
                            Log.e("response=> ",response.toString());

                            studentAddressVoList.clear();
                            for (int i=0;i<response.length();i++){
                                String studentAddressJson=response.get(i).toString();
                                System.out.println("studentAddressJson=> "+studentAddressJson);
                                StudentAddressVo studentAddressVo= new Gson().fromJson(studentAddressJson,StudentAddressVo.class);
                                System.out.println("studentAddressVo=> "+studentAddressVo);
                                studentAddressVoList.add(studentAddressVo);

                            }
                            recyclerViewAdapter.notifyDataSetChanged();

                        }

                    }catch(JSONException jsonException){
                        jsonException.printStackTrace();

                    }catch (Exception exception){
                        exception.printStackTrace();
                    }
                    pdScreen1.dismiss();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Error=> ",error.toString());
                        }
                    });

            requestQueue.add(jsonArrayRequest);
            //System.out.println("studentVoList.get(0).getId()=> "+studentVoList.get(0));


        }




        //Set the data

        //Edit Address Button Click
        /*btnEditAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity().getApplicationContext(),EditAddressActivity.class);
                System.out.println("inside btnEditAddress click method");
                intent.putExtra("TAG","STUDENT");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });*/

        //Delete Address Button Click
        /*btnDeleteAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        //Add Address Button Click
        btnAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity().getApplicationContext(),AddAddressActivity.class);
                System.out.println("inside btnAddAddress click method");
                intent.putExtra("TAG","STUDENT");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });

        return rootVeiw;

    }
}