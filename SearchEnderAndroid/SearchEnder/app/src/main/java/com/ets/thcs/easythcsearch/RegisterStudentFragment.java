package com.ets.thcs.easythcsearch;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.support.annotation.Nullable;
//import androidx.core.app.Fragment;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;



import com.ets.thcs.easythcsearch.helper.Constants;
import com.ets.thcs.easythcsearch.helper.URLs;
import com.ets.thcs.easythcsearch.model.RegResponseVo;
import com.ets.thcs.easythcsearch.model.StudentVo;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterStudentFragment extends Fragment {

    private Button btnRegisterStudent;
    private EditText etFname;
    private EditText etLname;
    private EditText etEmail;
    private ProgressDialog pdScreen1;
    private EditText etPhone;
    private EditText etPassword;
    private RadioGroup rgGender;
    private RadioGroup rgUsernameOpt;

    private String studentJson;private StudentVo studentVo;
    private RegResponseVo regResponseVo;
    private Button btnFbLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootVeiw = inflater.inflate(R.layout.fragment_register_student, container, false);

        etFname = (EditText) rootVeiw.findViewById(R.id.et_first_name);
        etLname = (EditText) rootVeiw.findViewById(R.id.et_last_name);
        etEmail = (EditText) rootVeiw.findViewById(R.id.et_email);
        etPhone = (EditText) rootVeiw.findViewById(R.id.et_mobile_no);
        etPassword = (EditText) rootVeiw.findViewById(R.id.et_password);
        rgGender = (RadioGroup) rootVeiw.findViewById(R.id.rg_gender);
        rgUsernameOpt = (RadioGroup) rootVeiw.findViewById(R.id.rg_UsernameOpt);

     /*   btnFbLogin = (Button) rootVeiw.findViewById(R.id.btn_fb_login);
        btnFbLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_login_frag_container,new LoginFragment()).commit();
            }
        });*/

        btnRegisterStudent = (Button) rootVeiw.findViewById(R.id.btn_signUpStudent);
        btnRegisterStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateText(etFname) &&
                        validateText(etLname) &&
                        validateText(etEmail) &&
                        validateText(etPhone) &&
                        validateText(etPassword)) {
                    pdScreen1 = new ProgressDialog(getActivity());
                    pdScreen1.setIndeterminate(true);
                    pdScreen1.setCancelable(false);
                    pdScreen1.setMessage("Please wait...");

                    StudentVo studentVo = getStudentObject(rootVeiw);
                    pdScreen1.show();
                    Gson gson = new Gson();
                    studentJson = gson.toJson(studentVo);

                    Log.d(Constants.LOG, getClass().getName().toString()+": "+studentJson);
                    String BASE_URI = URLs.URL_REGISTRATION_STUDENT;
                    new WebserviceCallAppData().execute(BASE_URI, studentJson);
                }
            }
        });

        return rootVeiw;

    }
    private boolean validateText(EditText text) {
        boolean valid = true;
        String message = "";
        if (text.getText().toString().isEmpty()) {
            switch (text.getId()) {
                case R.id.et_first_name:
                    message = "First name can not be empty";
                    break;
                case R.id.et_last_name:
                    message = "Last name can not be empty";
                    break;
                case R.id.et_email:
                    message = "Email Id can not be empty";
                    break;
                case R.id.et_mobile_no:
                    message = "Mobile no can not be empty";
                    break;
                case R.id.et_password:
                    message = "Password can not be empty";
                    break;
                default:
                    break;
            }
            valid = false;
        }
        if (text.getId() == R.id.et_email) {
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            if (!text.getText().toString().matches(emailPattern)) {
                message = "Please enter a valid Email Address";
                valid = false;
            }
        }

        if (text.getId() == R.id.et_mobile_no) {
            String mobilePattern = "\\d{10}";
            if (!text.getText().toString().matches(mobilePattern)) {
                message = "Please enter a 10 digit mobile no.";
                valid = false;
            }
        }

        if (!valid) {
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        }
        return valid;
    }

    private StudentVo getStudentObject(View v) {
        StudentVo studentVo = new StudentVo();
        studentVo.setFirstName(etFname.getText().toString());
        studentVo.setLastName(etLname.getText().toString());
        studentVo.setEmailId(etEmail.getText().toString());
        studentVo.setPhoneNo(etPhone.getText().toString());
        studentVo.setUserPassword(etPassword.getText().toString());
        RadioButton gender = (RadioButton) v.findViewById(rgGender.getCheckedRadioButtonId());
        studentVo.setGender(gender.getText().toString());
        //TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        //String deviceId = telephonyManager.getDeviceId();
        studentVo.setImei("deviceId");
        studentVo.setLoginType("normal");

        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        System.out.println(formatter.format(ts));
        studentVo.setRegistrationTime(formatter.format(ts));
        //studentVo.setRegistrationTime(Timestamp.valueOf("2011-11-02T02:50:12.208Z"));
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Mydata", Context.MODE_PRIVATE);

        String latitude = sharedPreferences.getString("LATITUDE","n/a");
        String longitude = sharedPreferences.getString("LONGITUDE", "n/a");
        String geoLocation = latitude + ":" + longitude;
        studentVo.setDeviceLocation(geoLocation);
        RadioButton userNameOpt = (RadioButton) v.findViewById(rgUsernameOpt.getCheckedRadioButtonId());
        studentVo.setUserName(userNameOpt.getId() == R.id.rb_email_as_user ? etEmail.getText().toString() : etPhone.getText().toString());
        studentVo.setIsValid("No");
        studentVo.setIsDeleted("No");
        return studentVo;
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
                    //= MediaType.parse("application/text; charset=utf-8");
                    = MediaType.parse("application/json");

            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.DAYS.SECONDS).build();

            try {
                RequestBody body = RequestBody.create(JSON, params[1]);
                Request request = new Request.Builder()
                        .url(params[0])
                        .post(body)
                        .build();
                Response response = client.newCall(request).execute();
                if (response.isSuccessful())
                    result = response.body().string();
                else result =null;
            } catch (IOException e) {
                Log.e(Constants.LOG, getClass().getName().toString()+": "+e.toString());
            }
            return result;
        }


        @Override
        protected void onPostExecute(String result) {
            studentJson=result;
            System.out.println("studentJson=> "+studentJson);
            Log.d(Constants.LOG, getClass().getName().toString()+": "+studentJson);
            pdScreen1.dismiss();
            if (!studentJson.isEmpty()) {
                Gson gson = new Gson();
                //regResponseVo = null;
                studentVo=null;
                try {
                    studentVo = gson.fromJson(studentJson, StudentVo.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (studentVo != null && studentVo.getId()!=0) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setMessage("An activation code has been sent to your given Email Id." +
                            "Please check your email");
                    alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(getActivity().getApplicationContext(), ConfirmationAcitivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.putExtra("STUDENT_JSON", studentJson);
                            i.putExtra("CODE", "1234");
                            i.putExtra("TAG","STUDENT");
                            startActivity(i);
                            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }
                    });
                    alert.show();
                } else if (regResponseVo!=null){
                    final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    String errorMessage=regResponseVo.getStatusMessage();
                    if (errorMessage.contains("IMEI")){
                        errorMessage = "A registarion has already been done from this device!" +
                                " Please register with a different device";
                    }
                    alert.setMessage(errorMessage);
                    alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alert.show();
                }
                else {
                    final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setMessage("Something went wrong! Please try again");
                    alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alert.show();
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
