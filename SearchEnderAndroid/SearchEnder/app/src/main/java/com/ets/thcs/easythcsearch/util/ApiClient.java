package com.ets.thcs.easythcsearch.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ets.thcs.easythcsearch.helper.URLs;
import com.ets.thcs.easythcsearch.model.PostOfficeVo;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiClient {
    private String baseUri;
    private List<PostOfficeVo> postOfficeVoList;

    //Calling REST Services using Volley
    public List<PostOfficeVo> callApi(Context context,String pinCode){
        baseUri= URLs.URL_POSTAL_PINCODE+pinCode;


        RequestQueue requsetQueue= Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, baseUri, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                PostOfficeVo postOfficeVo=null;
                postOfficeVoList=new ArrayList<>();
                try{
                    Log.e("response=> ",response.toString());
                    postOfficeVoList.clear();
                    for(int i=0;i<response.length();i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        JSONArray postOfficeJsonArray=jsonObject.getJSONArray("PostOffice");
                        for (int j=0;j<postOfficeJsonArray.length();j++){
                            String postOfficeJsonString=postOfficeJsonArray.get(j).toString();
                            System.out.println("postOfficeJsonString=> "+postOfficeJsonString);
                            postOfficeVo=new Gson().fromJson(postOfficeJsonString,PostOfficeVo.class);
                            postOfficeVoList.add(postOfficeVo);

                        }
                    }
                    System.out.println("postOfficeVoList=> "+postOfficeVoList);
                    this.notify();


                }catch (Exception e){
                    e.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error=> ",error.toString());

            }
        });

        requsetQueue.add(jsonArrayRequest);
        System.out.println("before return postOfficeVoList=> "+postOfficeVoList);
        return postOfficeVoList;
    }


}
