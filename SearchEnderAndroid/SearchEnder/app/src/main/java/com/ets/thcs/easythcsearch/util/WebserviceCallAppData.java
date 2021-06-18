package com.ets.thcs.easythcsearch.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.ets.thcs.easythcsearch.R;
import com.ets.thcs.easythcsearch.helper.Constants;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebserviceCallAppData extends AsyncTask<String, Void, String> {

    private Activity currentactivity;
    private Class destinationActivityClass;
    private String requestMethod;
    private ProgressDialog pdScreen1;
    private String result;
    public WebserviceCallAppData(Activity currentactivity,Class destinationActivityClass,String requestMethod,ProgressDialog pdScreen1){
        System.out.println("currentactivity=> "+currentactivity);
        System.out.println("destinationActivityClass=> "+destinationActivityClass);
        this.currentactivity=currentactivity;
        this.destinationActivityClass=destinationActivityClass;
        this.requestMethod=requestMethod;
        this.pdScreen1=pdScreen1;
    }
    public String getResult(){
        return result;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected String doInBackground(String... params) {

        System.out.println("Inside WebserviceCallAppData, Before pdScreen1 currentactivity=> "+currentactivity);
        //pdScreen1 = new ProgressDialog(currentactivity.getApplicationContext());
        //pdScreen1.setIndeterminate(true);
        //pdScreen1.setCancelable(false);
        //pdScreen1.setMessage("Please wait, processing...");
        //pdScreen1.show();
        System.out.println("Inside WebserviceCallAppData, After pdScreen1");


        String result = "";
        MediaType JSON = MediaType.parse("application/json");

        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.DAYS.SECONDS).build();

        RequestBody body=null;
        Request request=null;
        Response response=null;
        try {
            switch (requestMethod){
                case "POST":
                    body = RequestBody.create(JSON, params[1]);
                    request = new Request.Builder()
                            .url(params[0])
                            .post(body)
                            .build();
                    response = client.newCall(request).execute();
                    if (response.isSuccessful())
                        result = response.body().string();
                    else result =null;
                    break;

                case "GET":
                    request = new Request.Builder()
                            .url(params[0])
                            .get()
                            .build();
                    response = client.newCall(request).execute();
                    if (response.isSuccessful())
                        result = response.body().string();
                    else result =null;
                    break;

                case "PUT":
                    body = RequestBody.create(JSON, params[1]);
                    request = new Request.Builder()
                            .url(params[0])
                            .put(body)
                            .build();
                    response = client.newCall(request).execute();
                    if (response.isSuccessful())
                        result = response.body().string();
                    else result =null;
                    break;
                case "DELETE":
                    body = RequestBody.create(JSON, params[1]);
                    request = new Request.Builder()
                            .url(params[0])
                            .delete(body)
                            .build();
                    response = client.newCall(request).execute();
                    if (response.isSuccessful())
                        result = response.body().string();
                    else result =null;
                    break;
            }


        } catch (IOException e) {
            Log.e(Constants.LOG, getClass().getName().toString()+": "+e.toString());
        }
        return result;
    }


    @Override
    protected void onPostExecute(String result) {
        this.result=result;
        System.out.println("result=> "+result);
        Log.d(Constants.LOG, getClass().getName().toString()+": "+result);
        pdScreen1.dismiss();
        if (!result.isEmpty()) {
            //Gson gson = new Gson();
            //regResponseVo = null;
            ArrayList arrayList=null;
            try {
                arrayList = new Gson().fromJson(result, ArrayList.class);
                System.out.println("Inside WebserviceCallAppData, After covertion from json to ArrayList object=> "+arrayList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (arrayList != null && arrayList.size()>0) {
                if(destinationActivityClass!=null){
                    AlertDialog.Builder alert = new AlertDialog.Builder(currentactivity);
                    alert.setMessage("Success");
                    alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(currentactivity.getApplicationContext(), destinationActivityClass);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.putExtra(Constants.RESULT_JSON, result);
                            i.putExtra(Constants.TAG,"STUDENT");
                            currentactivity.startActivity(i);
                            currentactivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }
                    });
                    alert.show();
                }
            }
            else {
                final AlertDialog.Builder alert = new AlertDialog.Builder(currentactivity);
                alert.setMessage("Something went wrong! Please try again");
                alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
            }
        } else {
            final AlertDialog.Builder alert = new AlertDialog.Builder(currentactivity);
            alert.setMessage("Something went wrong! Please try again");
            alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alert.show();
        }

    }
}
