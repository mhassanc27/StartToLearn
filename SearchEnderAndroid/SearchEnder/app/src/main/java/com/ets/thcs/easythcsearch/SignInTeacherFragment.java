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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ets.thcs.easythcsearch.helper.Constants;
import com.ets.thcs.easythcsearch.helper.URLs;
import com.ets.thcs.easythcsearch.model.LoginResponseVo;
import com.ets.thcs.easythcsearch.model.LoginVo;
import com.ets.thcs.easythcsearch.model.TeacherVo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignInTeacherFragment extends Fragment {

    private Button btnSignInTeacher;
    private EditText etUname, etPass;
    private ProgressDialog pdScreen1;
    private TextView tvForgotPass;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sign_in_teacher, container, false);

        btnSignInTeacher = (Button) rootView.findViewById(R.id.btn_signInTeacher);
        etUname = (EditText) rootView.findViewById(R.id.et_user_name);
        etPass = (EditText) rootView.findViewById(R.id.et_password);
        tvForgotPass = (TextView) rootView.findViewById(R.id.tv_forgot_pass);

        btnSignInTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUname.getText().toString();
                String password = etPass.getText().toString();
                if (username.isEmpty()|| password.isEmpty()){
                    Toast.makeText(getActivity().getApplicationContext(),"Please enter User name and Password",Toast.LENGTH_SHORT).show();
                }else {
                    System.out.println("Inside SignInTeacherFragment Else");
                    pdScreen1 = new ProgressDialog(getActivity());
                    pdScreen1.setIndeterminate(true);
                    pdScreen1.setCancelable(false);
                    pdScreen1.setMessage("Logging you in...");
                    pdScreen1.show();
                    LoginVo loginVo = new LoginVo();
                    loginVo.setUserName(etUname.getText().toString());
                    loginVo.setUserPassword(etPass.getText().toString());
                    loginVo.setSessionId("");
                    Gson gson = new Gson();
                    String loginJson = gson.toJson(loginVo);
                    Log.d(Constants.LOG, getClass().getName().toString() + ": " + loginJson);
                    String BASE_URI = URLs.URL_SIGNIN_TEACHER;

//                Uri buildUri = Uri.parse(BASE_URI).buildUpon()
//                        .appendQueryParameter("UserCred",loginJson).build();
//                Log.d("URI",BASE_URI+"?UserCred="+ URLEncoder.encode(loginJson));
//                new WebserviceCallAppData().execute(BASE_URI+"?UserCred="+ URLEncoder.encode(loginJson));
//                Log.d("URI",buildUri.toString());
                    //new WebserviceCallAppData().execute(BASE_URI, loginJson);
                    Intent i = new Intent(getActivity(), TeacherOrStudentDetailsActivity.class);
                    i.putExtra("TAG","TEACHER");
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    getActivity().startActivity(i);
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
                    = MediaType.parse("application/text; charset=utf-8");

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
            LoginResponseVo loginResponseVo = null;

            if (result != null) {
                Log.d(Constants.LOG, getClass().getName().toString() + ": " + result);
                Gson gson = new Gson();
                loginResponseVo = new LoginResponseVo();
                try {
                    loginResponseVo = gson.fromJson(result, LoginResponseVo.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (loginResponseVo != null) {
                    if (loginResponseVo.getIsAuthentic().equals("true")) {
                        if (loginResponseVo.getIsSuccess().equals("false")) {
                            TeacherVo teacherVo = new TeacherVo();
                            teacherVo.setIsValid("resend");
                            teacherVo.setEmailId(loginResponseVo.getEmailId());
                            teacherVo.setUserName(etUname.getText().toString());
                            String teacherJson = new Gson().toJson(teacherVo);
                            Intent i = new Intent(getActivity(), ConfirmationAcitivity.class);
                            i.putExtra("TEACHER_JSON",teacherJson);
                            i.putExtra("RESEND","resend");
                            i.putExtra("TAG","TEACHER");
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            getActivity().startActivity(i);
                            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                        } else {
//                        Toast.makeText(getActivity(), "Correct Crendential", Toast.LENGTH_SHORT).show();
                            pdScreen1.dismiss();
                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.LOGIN_INFO, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(Constants.SESSION_ID, loginResponseVo.getSessionId());
                            editor.putBoolean(Constants.SESSION_ACTIVE, true);
                            editor.putString(Constants.LOGIN_TYPE, "normal");
                            editor.putString(Constants.EMAIL, loginResponseVo.getEmailId());
                            editor.putString(Constants.PHONE_NO, loginResponseVo.getPhoneNo());
                            editor.putString(Constants.F_NAME, loginResponseVo.getFirstName());
                            editor.putString(Constants.L_NAME, loginResponseVo.getLastName());
                            editor.putString(Constants.GENDER, loginResponseVo.getGender());
                            editor.apply();
                            /*Intent i = new Intent(getActivity(), HomeMapActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            getActivity().startActivity(i);
                            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);*/
                        }
                    } else {
                        Toast.makeText(getActivity(), "User Name or Password is incorrect!", Toast.LENGTH_SHORT).show();
                        pdScreen1.dismiss();
                    }
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
