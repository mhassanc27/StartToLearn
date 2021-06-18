package com.ets.thcs.easythcsearch;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ets.thcs.easythcsearch.helper.Constants;
import com.ets.thcs.easythcsearch.helper.URLs;
import com.ets.thcs.easythcsearch.model.RegResponseVo;
import com.ets.thcs.easythcsearch.model.StudentVo;
import com.ets.thcs.easythcsearch.model.TeacherVo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConfirmationAcitivity extends Activity {


    private Button btnVerify;
    private EditText etVerificationCode;
    private TextView textResend;
    private String code;
    private String teacherJson;
    private String studentJson;
    private boolean resendFlag;
    private ProgressDialog pdScreen1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        pdScreen1 = new ProgressDialog(this);
        pdScreen1.setIndeterminate(true);
        pdScreen1.setCancelable(false);
        pdScreen1.setMessage("Please wait...");
        code = getIntent().getStringExtra("CODE");
        btnVerify = (Button) findViewById(R.id.btn_verify_code);
        etVerificationCode = (EditText) findViewById(R.id.et_verification_code);
        textResend = (TextView) findViewById(R.id.txt_resend);

        if (getIntent().hasExtra("RESEND")){
            AlertDialog.Builder alert = new AlertDialog.Builder(ConfirmationAcitivity.this);
            alert.setMessage("You have not verified your email Id! Click OK to verify");
            alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!pdScreen1.isShowing())
                        pdScreen1.show();
                    resendFlag = true;

                    if (getIntent().getStringExtra("TAG").equals("TEACHER")) {
                        teacherJson = getIntent().getStringExtra("TEACHER_JSON");
                        TeacherVo teacherVo = new Gson().fromJson(teacherJson, TeacherVo.class);
                        teacherVo.setIsValid("Resend");
                        teacherJson = new Gson().toJson(teacherVo);
                        new WebserviceCallAppData().execute(URLs.URL_REGISTRATION_TEACHER, teacherJson);
                    }
                    if (getIntent().getStringExtra("TAG").equals("STUDENT")) {
                        studentJson = getIntent().getStringExtra("STUDENT_JSON");
                        StudentVo studentVo = new Gson().fromJson(studentJson, StudentVo.class);
                        studentVo.setIsValid("Resend");
                        studentJson = new Gson().toJson(studentVo);
                        new WebserviceCallAppData().execute(URLs.URL_REGISTRATION_STUDENT, studentJson);
                    }


                }
            });
            alert.show();
        }


        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etVerificationCode.getText().toString().isEmpty())
                    Toast.makeText(ConfirmationAcitivity.this,"Please enter the verification code!",Toast.LENGTH_SHORT).show();
                else {
                    if (!pdScreen1.isShowing())
                        pdScreen1.show();
                    verifyCode(code);
                }
            }
        });

        textResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pdScreen1.isShowing())
                    pdScreen1.show();
                resendFlag = true;

                if (getIntent().getStringExtra("TAG").equals("TEACHER")) {
                    teacherJson = getIntent().getStringExtra("TEACHER_JSON");
                    TeacherVo teacherVo = new Gson().fromJson(teacherJson, TeacherVo.class);
                    teacherVo.setIsValid("Resend");
                    teacherJson = new Gson().toJson(teacherVo);
                    new WebserviceCallAppData().execute(URLs.URL_REGISTRATION_TEACHER, teacherJson);
                }
                if (getIntent().getStringExtra("TAG").equals("STUDENT")) {
                    studentJson = getIntent().getStringExtra("STUDENT_JSON");
                    StudentVo studentVo = new Gson().fromJson(studentJson, StudentVo.class);
                    studentVo.setIsValid("Resend");
                    teacherJson = new Gson().toJson(studentVo);
                    new WebserviceCallAppData().execute(URLs.URL_REGISTRATION_STUDENT, studentJson);
                }


                //userJson = getIntent().getStringExtra("USER_JSON");
                //UserVo userVo = new Gson().fromJson(userJson, UserVo.class);
                //userVo.setIsValid("Resend");
                //userJson = new Gson().toJson(userVo);
                //new WebserviceCallAppData().execute(URLs.URL_REGISTRATION, userJson);
            }
        });
    }

    public class WebserviceCallAppData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String result = "";
            MediaType JSON
                    //= MediaType.parse("application/text; charset=utf-8");
                    =MediaType.parse("application/json");

            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.DAYS.SECONDS).build();

            try {
                RequestBody body = RequestBody.create(JSON, params[1]);
                Request request = new Request.Builder()
                        .url(params[0])
                        .put(body)
                        .build();
                Response response = client.newCall(request).execute();
                result = response.body().string();
            } catch (IOException e) {
                Log.e(Constants.LOG, getClass().getName().toString() + ": " + e.toString());
            }
            return result;
        }


        @Override
        protected void onPostExecute(String result) {
            System.out.println("result=> "+result);
            if (pdScreen1.isShowing())
                pdScreen1.dismiss();
            if (!resendFlag) {
                String message = "";
                final AlertDialog.Builder alert = new AlertDialog.Builder(ConfirmationAcitivity.this);
                alert.setCancelable(false);
                if (result != null) {
                    //RegResponseVo regResponseVo = new Gson().fromJson(result, RegResponseVo.class);
                    StudentVo studentVo = new Gson().fromJson(result, StudentVo.class);
                    System.out.println("studentVo.getId()=> "+studentVo.getId());
                    if (studentVo.getId()!=0) {
                        message = "Your registration is successfull! Please login now";
                        alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (getIntent().getStringExtra("TAG").equals("TEACHER")) {
                                    Intent i = new Intent(getApplicationContext(), TeacherOrStudentSignInActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    i.putExtra("TAG", "TEACHER");
                                    startActivity(i);
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                                }
                                if (getIntent().getStringExtra("TAG").equals("STUDENT")) {
                                    Intent i = new Intent(getApplicationContext(), TeacherOrStudentSignInActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    i.putExtra("TAG", "STUDENT");
                                    startActivity(i);
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                                }

                            }
                        });

                    } else {
                        message = "Something went wrong! Please try again";
                        alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                    }
                }
                alert.setMessage(message);
                alert.show();
            } else {
                RegResponseVo regResponseVo = new Gson().fromJson(result, RegResponseVo.class);
                code = regResponseVo.getVerificationCode();
                AlertDialog.Builder alert = new AlertDialog.Builder(ConfirmationAcitivity.this);
                alert.setMessage("Activation code has been resent to your registered Email ID!" +
                        "Please check your email");
                alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();

            }
        }
    }

    private void verifyCode(String code) {

        if (etVerificationCode.getText().toString().equals(code)) {


            resendFlag = false;

            if (getIntent().getStringExtra("TAG").equals("TEACHER")) {
                teacherJson = getIntent().getStringExtra("TEACHER_JSON");
                TeacherVo teacherVo = new Gson().fromJson(teacherJson, TeacherVo.class);
                teacherVo.setIsValid("Yes");
                teacherJson = new Gson().toJson(teacherVo);
                new WebserviceCallAppData().execute(URLs.URL_REGISTRATION_TEACHER, teacherJson);
            }
            if (getIntent().getStringExtra("TAG").equals("STUDENT")) {
                studentJson = getIntent().getStringExtra("STUDENT_JSON");
                System.out.println("1 in verifyCode Method: studentJson=> "+studentJson);
                StudentVo studentVo = new Gson().fromJson(studentJson, StudentVo.class);

                studentVo.setIsValid("Yes");
                System.out.println("studentVo=> "+studentVo);
                studentJson = new Gson().toJson(studentVo);
                System.out.println("2 in verifyCode Method: studentJson=> "+studentJson);

                new WebserviceCallAppData().execute(URLs.URL_UPDATE_STUDENT, studentJson);
            }


            //userJson = getIntent().getStringExtra("USER_JSON");
            //UserVo userVo = new Gson().fromJson(userJson, UserVo.class);
            //userVo.setIsValid("Yes");
            //userJson = new Gson().toJson(userVo);
            //new WebserviceCallAppData().execute(URLs.URL_REGISTRATION, userJson);
        } else
            Toast.makeText(ConfirmationAcitivity.this, "Code did not match!", Toast.LENGTH_LONG).show();

    }

   /* @Override
    protected void onDestroy() {
        if (isMyServiceRunning(GPSTracker.class)) {
            Intent i = new Intent(getApplicationContext(), GPSTracker.class);
            stopService(i);
        }
        super.onDestroy();
    }*/

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
