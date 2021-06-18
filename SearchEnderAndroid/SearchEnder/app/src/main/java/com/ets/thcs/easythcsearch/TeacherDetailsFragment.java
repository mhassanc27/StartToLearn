package com.ets.thcs.easythcsearch;

import android.graphics.Color;
import android.os.Bundle;
//import android.support.annotation.Nullable;
//import androidx.core.app.Fragment;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.ets.thcs.easythcsearch.model.RegResponseVo;

import java.sql.Timestamp;

public class TeacherDetailsFragment extends Fragment {
    private Button btnTeacherDetails;
    private AutoCompleteTextView   actvCountry;
    private AutoCompleteTextView   actvState;
    private AutoCompleteTextView   actvCity;
    private AutoCompleteTextView   actvBoard;
    private AutoCompleteTextView   actvSchool;
    private AutoCompleteTextView   actvClass;
    private AutoCompleteTextView   sDepartmentId;
    private AutoCompleteTextView   sClassId;
    private AutoCompleteTextView   sSubjectId;
    private AutoCompleteTextView   sCountryId;
    private AutoCompleteTextView   sStateId;
    private AutoCompleteTextView   sDistrictId;
    private AutoCompleteTextView   sCityId;
    private EditText etAddressLine1;
    private EditText etAddressLine2;
    private EditText etPinCode;
    private Timestamp dateOfBirth;


    private RadioGroup rgGender;
    private RadioGroup rgUsernameOpt;

    private String teacherJson;
    private RegResponseVo regResponseVo;
    private Button btnFbLogin;
    private String[] countryList ={"India","Bangladesh","Nepal",".UAE","USA","UK","Denmark","Indonesia"};
    private String[] stateList ={"West Bengal","Uttar Pradesh","Assam","Tamil Nadu","Maharastra","Karnataka","Orissa","Bihar"};
    private String[] cityList ={"Kolkata","Dankuni","Bardwan","Durgapur","Rampurhat","Howrah","Kalyani","Bali"};
    private String[] boardList ={"CBSE","ICSC","WBBSE","WBCHSE","ISC"};
    private String[] schoolList ={"Holy Home","Delhi Public School","New Town School"};
    private String[] classList ={"I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII"};
    ArrayAdapter<String> countryAdapter,stateAdapter,cityAdapter,boardAdapter,schoolAdapter,classAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootVeiw = inflater.inflate(R.layout.fragment_teacher_details, container, false);


        actvCountry = (AutoCompleteTextView ) rootVeiw.findViewById(R.id.actv_Country);
        actvState = (AutoCompleteTextView ) rootVeiw.findViewById(R.id.actv_State);
        actvCity = (AutoCompleteTextView ) rootVeiw.findViewById(R.id.actv_City);
        actvBoard = (AutoCompleteTextView ) rootVeiw.findViewById(R.id.actv_Board);
        actvSchool = (AutoCompleteTextView ) rootVeiw.findViewById(R.id.actv_School);
        actvClass = (AutoCompleteTextView ) rootVeiw.findViewById(R.id.actv_Class);



        countryAdapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.select_dialog_item, countryList);

        actvCountry.setThreshold(1);//will start working from first character
        actvCountry.setAdapter(countryAdapter);//setting the adapter data into the AutoCompleteTextView
        actvCountry.setTextColor(Color.RED);
       // actvCountry.setOnItemClickListener(getActivity());

        stateAdapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.select_dialog_item, stateList);

        actvState.setThreshold(1);//will start working from first character
        actvState.setAdapter(stateAdapter);//setting the adapter data into the AutoCompleteTextView
        actvState.setTextColor(Color.RED);


        cityAdapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.select_dialog_item, cityList);

        actvCity.setThreshold(1);//will start working from first character
        actvCity.setAdapter(cityAdapter);//setting the adapter data into the AutoCompleteTextView
        actvCity.setTextColor(Color.RED);

        boardAdapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.select_dialog_item, boardList);

        actvBoard.setThreshold(1);//will start working from first character
        actvBoard.setAdapter(boardAdapter);//setting the adapter data into the AutoCompleteTextView
        actvBoard.setTextColor(Color.RED);

        schoolAdapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.select_dialog_item, schoolList);

        actvSchool.setThreshold(1);//will start working from first character
        actvSchool.setAdapter(schoolAdapter);//setting the adapter data into the AutoCompleteTextView
        actvSchool.setTextColor(Color.RED);

        classAdapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.select_dialog_item, classList);

        actvClass.setThreshold(1);//will start working from first character
        actvClass.setAdapter(classAdapter);//setting the adapter data into the AutoCompleteTextView
        actvClass.setTextColor(Color.RED);

     /*   btnFbLogin = (Button) rootVeiw.findViewById(R.id.btn_fb_login);
        btnFbLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_login_frag_container,new LoginFragment()).commit();
            }
        });*/


        /*btnTeacherDetails = (Button) rootVeiw.findViewById(R.id.btn_teacher_details);
        btnTeacherDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (validateText(etFname) &&
                        validateText(etLname) &&
                        validateText(etEmail) &&
                        validateText(etPhone) &&
                        validateText(etPassword)) {
                    System.out.println("On button click if validated");
                    pdScreen1 = new ProgressDialog(getActivity());
                    pdScreen1.setIndeterminate(true);
                    pdScreen1.setCancelable(false);
                    pdScreen1.setMessage("Please wait...");

                    TeacherVo teacherVo = getTeacherObject(rootVeiw);
                    pdScreen1.show();
                    Gson gson = new Gson();
                    teacherJson = gson.toJson(teacherVo);

                    Log.d(Constants.LOG, getClass().getName().toString()+": "+teacherJson);
                    String BASE_URI = URLs.URL_REGISTRATION_TEACHER;
                    new WebserviceCallAppData().execute(BASE_URI, teacherJson);
                }
            }
        });*/

        return rootVeiw;
    }

    /*private boolean validateText(EditText text) {
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

    private TeacherVo getTeacherObject(View v) {
        TeacherVo teacherVo = new TeacherVo();
        teacherVo.setFirstName(etFname.getText().toString());
        teacherVo.setLastName(etLname.getText().toString());
        teacherVo.setEmailId(etEmail.getText().toString());
        teacherVo.setPhoneNo(etPhone.getText().toString());
        teacherVo.setUserPassword(etPassword.getText().toString());
        RadioButton gender = (RadioButton) v.findViewById(rgGender.getCheckedRadioButtonId());
        teacherVo.setGender(gender.getText().toString());
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = telephonyManager.getDeviceId();
        teacherVo.setImei(deviceId);
        teacherVo.setLoginType("normal");
        teacherVo.setRegistrationTime(Timestamp.valueOf("2016-09-16 01:24:29.721"));
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Mydata", Context.MODE_PRIVATE);

        String latitude = sharedPreferences.getString("LATITUDE","n/a");
        String longitude = sharedPreferences.getString("LONGITUDE", "n/a");
        String geoLocation = latitude + ":" + longitude;
        teacherVo.setDeviceLocation(geoLocation);
        RadioButton userNameOpt = (RadioButton) v.findViewById(rgUsernameOpt.getCheckedRadioButtonId());
        teacherVo.setUserName(userNameOpt.getId() == R.id.rb_email_as_user ? etEmail.getText().toString() : etPhone.getText().toString());
        teacherVo.setIsValid("No");
        teacherVo.setIsDeleted("No");
        return teacherVo;
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
            Log.d(Constants.LOG, getClass().getName().toString()+": "+result);
            pdScreen1.dismiss();
            if (!result.isEmpty()) {
                Gson gson = new Gson();
                regResponseVo = null;
                try {
                    regResponseVo = gson.fromJson(result, RegResponseVo.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (regResponseVo != null && regResponseVo.getIsSuccess()) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setMessage("An activation code has been sent to your given Email Id." +
                            "Please check your email");
                    alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(getActivity().getApplicationContext(), ConfirmationAcitivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.putExtra("TEACHER_JSON", teacherJson);
                            i.putExtra("CODE", regResponseVo.getVerificationCode());
                            i.putExtra("TAG","TEACHER");
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
    }*/
}
