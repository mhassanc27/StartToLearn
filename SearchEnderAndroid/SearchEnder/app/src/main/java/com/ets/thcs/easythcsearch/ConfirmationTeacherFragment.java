package com.ets.thcs.easythcsearch;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.ets.thcs.easythcsearch.model.RegResponseVo;
import com.ets.thcs.easythcsearch.model.TeacherVo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConfirmationTeacherFragment extends Fragment {

    private Button btnVerify;
    private EditText etVerificationCode;
    private TextView textResend;
    private String code;
    private String teacherJson;
    private boolean resendFlag;
    private ProgressDialog pdScreen1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootVeiw = inflater.inflate(R.layout.fragment_confirmation_teacher, container, false);

        pdScreen1 = new ProgressDialog(getActivity());
        pdScreen1.setIndeterminate(true);
        pdScreen1.setCancelable(false);
        pdScreen1.setMessage("Please wait...");
        code = getArguments().getString("CODE");//getIntent().getStringExtra("CODE");
        btnVerify = (Button) rootVeiw.findViewById(R.id.btn_verify_code);
        etVerificationCode = (EditText) rootVeiw.findViewById(R.id.et_verification_code);
        textResend = (TextView) rootVeiw.findViewById(R.id.txt_resend);

        if (getArguments().containsKey("RESEND")){
            AlertDialog.Builder alert = new AlertDialog.Builder(new TeacherOrStudentConfirmationAcitivity());
            alert.setMessage("You have not verified your email Id! Click OK to verify");
            alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!pdScreen1.isShowing())
                        pdScreen1.show();
                    resendFlag = true;
                    teacherJson = getArguments().getString("TEACHER_JSON");
                    TeacherVo teacherVo = new Gson().fromJson(teacherJson, TeacherVo.class);
                    teacherVo.setIsValid("Resend");
                    teacherJson = new Gson().toJson(teacherVo);
                    new WebserviceCallAppData().execute(URLs.URL_REGISTRATION_TEACHER, teacherJson);
                }
            });
            alert.show();
        }


        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etVerificationCode.getText().toString().isEmpty())
                    Toast.makeText(new TeacherOrStudentConfirmationAcitivity(),"Please enter the verification code!",Toast.LENGTH_SHORT).show();
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
                teacherJson =getArguments().getString("TEACHER_JSON");
                TeacherVo teacherVo = new Gson().fromJson(teacherJson, TeacherVo.class);
                teacherVo.setIsValid("Resend");
                teacherJson = new Gson().toJson(teacherVo);
                new WebserviceCallAppData().execute(URLs.URL_REGISTRATION_TEACHER, teacherJson);
            }
        });



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation_teacher, container, false);
    }

    public class WebserviceCallAppData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String result = "";
            MediaType JSON
                    = MediaType.parse("application/text; charset=utf-8");

            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.DAYS.SECONDS).build();

            try {
                RequestBody body = RequestBody.create(JSON, params[1]);
                Request request = new Request.Builder()
                        .url(params[0])
                        .post(body)
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

            if (pdScreen1.isShowing())
                pdScreen1.dismiss();
            if (!resendFlag) {
                String message = "";
                final AlertDialog.Builder alert = new AlertDialog.Builder(new TeacherOrStudentConfirmationAcitivity());
                alert.setCancelable(false);
                if (result != null) {
                    RegResponseVo regResponseVo = new Gson().fromJson(result, RegResponseVo.class);
                    if (regResponseVo.getIsSuccess()) {
                        message = "Your registration is successfull! Please login now";
                        alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent i = new Intent(getContext().getApplicationContext(), TeacherOrStudentRegisterActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.putExtra("TAG", "LOGIN");
                                startActivity(i);
                                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
                AlertDialog.Builder alert = new AlertDialog.Builder(new TeacherOrStudentConfirmationAcitivity());
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


            teacherJson =getArguments().getString("TEACHER_JSON");
            TeacherVo teacherVo = new Gson().fromJson(teacherJson, TeacherVo.class);
            teacherVo.setIsValid("Yes");
            teacherJson = new Gson().toJson(teacherVo);
            new WebserviceCallAppData().execute(URLs.URL_REGISTRATION_TEACHER, teacherJson);
        } else
            Toast.makeText(new TeacherOrStudentConfirmationAcitivity(), "Code did not match!", Toast.LENGTH_LONG).show();

    }

/*    @Override
    protected void onDestroy() {
        if (isMyServiceRunning(GPSTracker.class)) {
            Intent i = new Intent(getApplicationContext(), GPSTracker.class);
            stopService(i);
        }
        super.onDestroy();
    }*/

    private boolean isMyServiceRunning(Class<?> serviceClass) {
       /* ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }*/
        return false;
    }
}
