package com.ets.thcs.easythcsearch;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
//import androidx.core.app.Fragment;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.ets.thcs.easythcsearch.helper.Constants;
import com.ets.thcs.easythcsearch.helper.URLs;


import com.ets.thcs.easythcsearch.model.LoginVo;

import com.ets.thcs.easythcsearch.model.StudentVo;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class SignInStudentFragment extends Fragment {

    private Button btnSignInStudent;
    private EditText etUname, etPass;
    private ProgressDialog pdScreen1;
    private TextView tvForgotPass;
    private String studentJson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sign_in_student, container, false);

        btnSignInStudent = (Button) rootView.findViewById(R.id.btn_signInStudent);
        etUname = (EditText) rootView.findViewById(R.id.et_user_name);
        etPass = (EditText) rootView.findViewById(R.id.et_password);
        tvForgotPass = (TextView) rootView.findViewById(R.id.tv_forgot_pass);

        btnSignInStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUname.getText().toString();
                String password = etPass.getText().toString();
                System.out.println("username=> "+username);
                System.out.println("password=> "+password);
                if (username.isEmpty()|| password.isEmpty()){
                    Toast.makeText(getActivity().getApplicationContext(),"Please enter User name and Password",Toast.LENGTH_SHORT).show();
                }else {
                    pdScreen1 = new ProgressDialog(getActivity());
                    pdScreen1.setIndeterminate(true);
                    pdScreen1.setCancelable(false);
                    pdScreen1.setMessage("Logging you in...");
                    pdScreen1.show();
                    System.out.println("before StudentLoginVo object creation");
                    LoginVo loginVo=new LoginVo();
                    loginVo.setUserName(username);
                    loginVo.setUserPassword(password);
                    loginVo.setSessionId("1234");
                    System.out.println("studentLoginVo=> "+loginVo);
                    Gson gson = new Gson();
                    String studentLoginJson = gson.toJson(loginVo);
                    System.out.println("studentLoginJson=> "+studentLoginJson);
                    Log.d(Constants.LOG, getClass().getName().toString() + ": " + studentLoginJson);
                    String BASE_URI = URLs.URL_READ_STUDENT_BY_USERNAME_AND_PASSWORD;
                    System.out.println("BASE_URI=> "+BASE_URI);

//                Uri buildUri = Uri.parse(BASE_URI).buildUpon()
//                        .appendQueryParameter("UserCred",loginJson).build();
//                Log.d("URI",BASE_URI+"?UserCred="+ URLEncoder.encode(loginJson));
//                new WebserviceCallAppData().execute(BASE_URI+"?UserCred="+ URLEncoder.encode(loginJson));
//                Log.d("URI",buildUri.toString());
                    new WebserviceCallAppData().execute(BASE_URI, studentLoginJson);
                }

            }
        });

        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setMessage("Please sent a email to wizsoftsolutiongroup@gmail.com mentioning your new password from your registered email id!");
                alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
            }
        });

        return rootView;
    }

    public class WebserviceCallAppData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String result = "";
            MediaType JSON
                   // = MediaType.parse("application/text; charset=utf-8");
                    = MediaType.parse("application/json");

            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.DAYS.SECONDS).build();

//            client = new OkHttpClient.Builder().build();
            try {
                RequestBody body = RequestBody.create(JSON, params[1]);
                Request request = new Request.Builder()
                        .url(params[0])
                        .post(body)
                        .build();
                Response response = client.newCall(request).execute();
                if (response.isSuccessful())
                    result = response.body().string();
                else result = null;
            } catch (IOException e) {
                Log.e(Constants.LOG, getClass().getName().toString() + ": " + e.toString());
            }
            return result;
        }


        @Override
        protected void onPostExecute(String result) {
            StudentVo studentVo = null;
            System.out.println("result=> "+result);

            if (result != null) {
                System.out.println("inside if result=> "+result);
                Log.d(Constants.LOG, getClass().getName().toString() + ": " + result);
                Gson gson = new Gson();

                try {
                    System.out.println("studentVo=> "+studentVo);
                    studentVo = gson.fromJson(result, StudentVo.class);
                    System.out.println("studentVo=> "+studentVo);
                    studentJson = new Gson().toJson(studentVo);
                    System.out.println("studentJson=> "+studentJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (studentVo != null) {
                    if (studentVo.getIsValid().equals("No")) {
                        /*if (studentVo.getIsSuccess().equals("false")) {
                            StudentVo studentVo = new StudentVo();
                            studentVo.setIsValid("resend");
                            studentVo.setEmailId(loginResponseVo.getEmailId());
                            studentVo.setUserName(etUname.getText().toString());
                            studentJson = new Gson().toJson(studentVo);
                            Intent i = new Intent(getActivity(), ConfirmationAcitivity.class);
                            i.putExtra("STUDENT_JSON",studentJson);
                            i.putExtra("RESEND","resend");
                            i.putExtra("TAG","STUDENT");
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            getActivity().startActivity(i);
                            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);*/

                    } else {
//                        Toast.makeText(getActivity(), "Correct Crendential", Toast.LENGTH_SHORT).show();
                            System.out.println("In Else studentVo=> "+studentVo);
                            pdScreen1.dismiss();
                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.LOGIN_INFO, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            //editor.putString(Constants.SESSION_ID, studentVo.getSessionId());
                            editor.putBoolean(Constants.SESSION_ACTIVE, true);
                            editor.putString(Constants.LOGIN_TYPE, "normal");
                            editor.putString(Constants.EMAIL, studentVo.getEmailId());
                            editor.putString(Constants.PHONE_NO, studentVo.getPhoneNo());
                            editor.putString(Constants.F_NAME, studentVo.getFirstName());
                            editor.putString(Constants.L_NAME, studentVo.getLastName());
                            editor.putString(Constants.GENDER, studentVo.getGender());
                            editor.putString(Constants.LOGIN_AS,"STUDENT");
                            editor.putString("studentJson",studentJson);
                            editor.apply();
                            Intent i = new Intent(getActivity(), TeacherOrStudentDetailsActivity.class);
                            i.putExtra("studentJson",studentJson);
                            i.putExtra("TAG","STUDENT");
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            getActivity().startActivity(i);
                            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                      }
                    } else {
                        Toast.makeText(getActivity(), "User Name or Password is incorrect!", Toast.LENGTH_SHORT).show();
                        pdScreen1.dismiss();
                    }
                } else {
                final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
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

}
