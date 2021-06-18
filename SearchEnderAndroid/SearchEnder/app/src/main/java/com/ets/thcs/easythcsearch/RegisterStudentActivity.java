package com.ets.thcs.easythcsearch;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
/*import android.widget.TextView;
import android.widget.Toast;

import com.ets.thcs.easythcsearch.model.LoginVo;*/
import com.ets.thcs.easythcsearch.model.RegResponseVo;
import com.ets.thcs.easythcsearch.helper.Constants;
import com.ets.thcs.easythcsearch.helper.URLs;
import com.ets.thcs.easythcsearch.model.StudentVo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//import androidx.core.app.Fragment;


public class RegisterStudentActivity extends Activity {
    private EditText etFirstName;
    private EditText etLastName;
    private RadioGroup rgGender;
    private EditText etEmail;
    private EditText etMobileNo;
    private RadioGroup rgUsernameOpt;
    private EditText etPassword;
    private Button btnSignUp;
    private ProgressDialog pdScreen1;
    private String studentJson;
    private StudentVo studentVo;
    private boolean resendFlag;
    private String verificationCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        etFirstName= (EditText) findViewById(R.id.et_first_name);
        etLastName= (EditText) findViewById(R.id.et_last_name);
        rgGender= (RadioGroup) findViewById(R.id.rg_gender);
        etEmail= (EditText) findViewById(R.id.et_email);
        etMobileNo= (EditText) findViewById(R.id.et_mobile_no);
        rgUsernameOpt= (RadioGroup) findViewById(R.id.rg_UsernameOpt);
        etPassword= (EditText) findViewById(R.id.et_password);

        btnSignUp= (Button) findViewById(R.id.btn_sign_up);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateText(etFirstName) &&
                        validateText(etLastName) &&
                        validateText(etEmail) &&
                        validateText(etMobileNo) &&
                        validateText(etPassword)) {
                    System.out.println("On button click if validated");
                    pdScreen1 = new ProgressDialog(RegisterStudentActivity.this);
                    pdScreen1.setIndeterminate(true);
                    pdScreen1.setCancelable(false);
                    pdScreen1.setMessage("Please wait...");

                    StudentVo studentVo = getStudentObject();
                    pdScreen1.show();
                    Gson gson = new Gson();
                    studentJson = gson.toJson(studentVo);
                    System.out.println("studentJson=> "+studentJson);

                    Log.d(Constants.LOG, getClass().getName().toString()+": "+studentJson);
                    String BASE_URI = URLs.URL_REGISTRATION_STUDENT;
                    new WebserviceCallAppData().execute(BASE_URI, studentJson);
                }
            }
        });



    }

    private boolean validateText(EditText editText) {
        boolean valid = true;
        String message = "";
        if (editText.getText().toString().isEmpty()) {
            switch (editText.getId()) {
                case R.id.et_first_name:
                    message = "First Name can not be empty";
                    break;
                case R.id.et_last_name:
                    message = "Last Name can not be empty";
                    break;
                case R.id.et_email:
                    message = "Email Id can not be empty";
                    break;
                case R.id.et_mobile_no:
                    message = "Mobile No can not be empty";
                    break;
                case R.id.et_password:
                    message = "Password can not be empty";
                    break;
                default:
                    break;
            }
            valid = false;
        }
        if (editText.getId() == R.id.et_email) {
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            if (!editText.getText().toString().matches(emailPattern)) {
                message = "Please enter a valid Email Address";
                valid = false;
            }
        }

        if (editText.getId() == R.id.et_mobile_no) {
            String mobilePattern = "\\d{10}";
            if (!editText.getText().toString().matches(mobilePattern)) {
                message = "Please enter a 10 digit mobile no.";
                valid = false;
            }
        }

        if (!valid) {
            Toast.makeText(RegisterStudentActivity.this, message, Toast.LENGTH_LONG).show();
        }
        return valid;
    }

    private StudentVo getStudentObject(){

        StudentVo studentVo=new StudentVo();
        studentVo.setFirstName(etFirstName.getText().toString());
        studentVo.setLastName(etLastName.getText().toString());
        studentVo.setGender(rgGender.toString());
        studentVo.setEmailId(etEmail.getText().toString());
        studentVo.setPhoneNo(etMobileNo.getText().toString());

        RadioButton gender = (RadioButton) findViewById(rgGender.getCheckedRadioButtonId());
        studentVo.setGender(gender.getText().toString());

        //TelephonyManager telephonyManager = (TelephonyManager) RegisterStudentActivity.this.getSystemService(Context.TELEPHONY_SERVICE);
        //String deviceId = telephonyManager.getDeviceId();
        studentVo.setImei("deviceId");
        studentVo.setLoginType("normal");
        //studentVo.setRegistrationTime(Timestamp.valueOf("2016-09-16 01:24:29.721"));

        SharedPreferences sharedPreferences = RegisterStudentActivity.this.getSharedPreferences("Mydata", Context.MODE_PRIVATE);

        String latitude = sharedPreferences.getString("LATITUDE","n/a");
        String longitude = sharedPreferences.getString("LONGITUDE", "n/a");
        String geoLocation = latitude + ":" + longitude;
        studentVo.setDeviceLocation(geoLocation);

        RadioButton userNameOpt = (RadioButton) findViewById(rgUsernameOpt.getCheckedRadioButtonId());
        studentVo.setUserName(userNameOpt.getId() == R.id.rb_email_as_user ? etEmail.getText().toString() : etMobileNo.getText().toString());

        return studentVo;

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
                final AlertDialog.Builder alert = new AlertDialog.Builder(RegisterStudentActivity.this);
                alert.setCancelable(false);
                if (result != null) {
                    RegResponseVo regResponseVo = new Gson().fromJson(result, RegResponseVo.class);
                    if (regResponseVo.getIsSuccess()) {
                        message = "Your registration is successfull! Please login now";
                        alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(getApplicationContext(), SignInStudentActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.putExtra("TAG", "LOGIN");
                                startActivity(i);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
                verificationCode = regResponseVo.getVerificationCode();
                AlertDialog.Builder alert = new AlertDialog.Builder(RegisterStudentActivity.this);
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




}
