package com.ets.thcs.easythcsearch;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ets.thcs.easythcsearch.helper.Constants;
import com.ets.thcs.easythcsearch.helper.URLs;
import com.ets.thcs.easythcsearch.model.StudentAddressVo;
import com.ets.thcs.easythcsearch.model.StudentVo;
import com.ets.thcs.easythcsearch.util.WebserviceCallAppData;
import com.google.gson.Gson;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddStudentAddressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddStudentAddressFragment extends Fragment {

    private AutoCompleteTextView actvCountry;
    private AutoCompleteTextView   actvState;
    private AutoCompleteTextView   actvCity;
    private AutoCompleteTextView   actvPinCode;
    private AutoCompleteTextView   actvDistrict;
    private AutoCompleteTextView   actvArea;
    private EditText etAddress;
    private EditText   etLandMark;

    private ArrayAdapter<String> countryAdapter;
    private ArrayAdapter<String> stateAdapter;
    private ArrayAdapter<String> cityAdapter;
    private ArrayAdapter<String> pinCodeAdapter;
    private ArrayAdapter<String> districtAdapter;
    private ArrayAdapter<String> areaAdapter;

    private String country;
    private String state;
    private String city;
    private String district;
    private String pinCode;
    private String area;
    private String address;
    private String landMark;


    private Button btnAddAddressDetails;
    private ProgressDialog pdScreen1;
    private StudentAddressVo studentAddressVo;
    private String studentJson;
    private StudentVo studentVo;


    private String[] countryList ={"India","Bangladesh","Nepal",".UAE","USA","UK","Denmark","Indonesia"};
    private String[] stateList ={"West Bengal","Uttar Pradesh","Assam","Tamil Nadu","Maharastra","Karnataka","Orissa","Bihar"};
    private String[] cityList ={"Kolkata","Dankuni","Bardwan","Durgapur","Rampurhat","Howrah","Kalyani","Bali"};
    private String[] pinCodeList ={"700135","700156","731224","731242","700134","700092","700091","712304"};
    private String[] districtList ={"North 24 Parganas","South 24 Parganas","Bardwan","Birbhum","Hooghly","Howrah","Malda","Murshidabad"};
    private String[] areaList ={"New Town Action Area III","New Town Action Area II","New Town Action Area I","Salt Lake Sector 5","Esplaned New Market Area","Narkel Bagan","Chinar Park","Sealdah"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View rootVeiw = inflater.inflate(R.layout.fragment_add_student_address, container, false);
        System.out.println("In AddStudentAddressFragment");



        actvCountry= (AutoCompleteTextView) rootVeiw.findViewById(R.id.actv_country);
        actvState= (AutoCompleteTextView) rootVeiw.findViewById(R.id.actv_state);
        actvCity= (AutoCompleteTextView) rootVeiw.findViewById(R.id.actv_city);
        actvPinCode= (AutoCompleteTextView) rootVeiw.findViewById(R.id.actv_pinCode);
        actvDistrict= (AutoCompleteTextView) rootVeiw.findViewById(R.id.actv_district);
        actvArea= (AutoCompleteTextView) rootVeiw.findViewById(R.id.actv_area);
        etAddress= (EditText) rootVeiw.findViewById(R.id.et_address);
        etLandMark= (EditText) rootVeiw.findViewById(R.id.et_landMark);

        btnAddAddressDetails= (Button) rootVeiw.findViewById(R.id.btn_add_address_details);

        actvState.setEnabled(false);
        actvCity.setEnabled(false);
        actvPinCode.setEnabled(false);
        actvDistrict.setEnabled(false);
        actvArea.setEnabled(false);
        etAddress.setEnabled(false);
        etLandMark.setEnabled(false);

        //Country Input
        countryAdapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.select_dialog_item, countryList);
        actvCountry.setThreshold(1);//will start working from first character
        actvCountry.setAdapter(countryAdapter);//setting the adapter data into the AutoCompleteTextView
        actvCountry.setTextColor(Color.RED);
        actvCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                country = (String) parent.getItemAtPosition(position);
                int pos = Arrays.asList(countryList).indexOf(country);
                System.out.println("Country choosen by user is => "+country);
                System.out.println("Position is=> "+pos);
                actvState.setEnabled(true);
            }
        });

        //State Input
        stateAdapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.select_dialog_item, stateList);
        actvState.setThreshold(1);//will start working from first character
        actvState.setAdapter(stateAdapter);//setting the adapter data into the AutoCompleteTextView
        actvState.setTextColor(Color.RED);
        actvState.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                state = (String) parent.getItemAtPosition(position);
                int pos = Arrays.asList(stateList).indexOf(state);
                System.out.println("Country choosen by user is => "+state);
                System.out.println("Position is=> "+pos);
                actvCity.setEnabled(true);
            }
        });


        //City Input
        cityAdapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.select_dialog_item, cityList);
        actvCity.setThreshold(1);//will start working from first character
        actvCity.setAdapter(cityAdapter);//setting the adapter data into the AutoCompleteTextView
        actvCity.setTextColor(Color.RED);
        actvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                city=(String)parent.getItemAtPosition(position);
                int pos=Arrays.asList(cityList).indexOf(city);
                System.out.println("City choosen by user is => "+city);
                System.out.println("Position is=> "+pos);
                actvPinCode.setEnabled(true);
            }
        });

        //PinCode Input
        pinCodeAdapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.select_dialog_item, pinCodeList);
        actvPinCode.setThreshold(1);//will start working from first character
        actvPinCode.setAdapter(pinCodeAdapter);//setting the adapter data into the AutoCompleteTextView
        actvPinCode.setTextColor(Color.RED);
        actvPinCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pinCode=(String)parent.getItemAtPosition(position);
                int pos=Arrays.asList(pinCodeList).indexOf(pinCode);
                System.out.println("PinCode choosen by user is => "+pinCode);
                System.out.println("Position is=> "+pos);
                actvDistrict.setEnabled(true);
            }
        });

        //District Input
        districtAdapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.select_dialog_item, districtList);
        actvDistrict.setThreshold(1);//will start working from first character
        actvDistrict.setAdapter(districtAdapter);//setting the adapter data into the AutoCompleteTextView
        actvDistrict.setTextColor(Color.RED);
        actvDistrict.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                district=(String)parent.getItemAtPosition(position);
                int pos=Arrays.asList(districtList).indexOf(district);
                System.out.println("District choosen by user is => "+district);
                System.out.println("Position is=> "+pos);
                actvArea.setEnabled(true);
            }
        });

        //Area Input
        areaAdapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.select_dialog_item, areaList);
        actvArea.setThreshold(1);//will start working from first character
        actvArea.setAdapter(areaAdapter);//setting the adapter data into the AutoCompleteTextView
        actvArea.setTextColor(Color.RED);
        actvArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                area=(String)parent.getItemAtPosition(position);
                int pos=Arrays.asList(areaList).indexOf(area);
                System.out.println("Area choosen by user is => "+area);
                System.out.println("Position is=> "+pos);
                etAddress.setEnabled(true);
                etLandMark.setEnabled(true);

            }
        });

        //Address Input
        address=etAddress.getText().toString();

        //LandMark Input
        landMark=etLandMark.getText().toString();

        //On button Click
        btnAddAddressDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (
                        validateText(actvCountry) &&
                                validateText(actvState) &&
                                validateText(actvCity) &&
                                validateText(actvPinCode) &&
                                validateText(actvDistrict) &&
                                validateText(actvArea) &&
                                validateText(etAddress) &&
                                validateText(etLandMark)

                ) {


                    //Get the Student Data
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.LOGIN_INFO, Context.MODE_PRIVATE);
                    studentJson=sharedPreferences.getString("studentJson","");
                    //studentJson=getActivity().getIntent().getStringExtra("studentJson");
                    if(studentJson!=null && studentJson!=""){
                        System.out.println("In AddStudentAddressFragment studentJson=> "+studentJson);
                        Gson gson=new Gson();
                        studentVo=gson.fromJson(studentJson, StudentVo.class);
                        System.out.println("In AddStudentAddressFragment studentVo=>"+studentVo);
                    }


                    //Creating the studentAddressVo object

                    address=etAddress.getText().toString();
                    landMark=etLandMark.getText().toString();
                    studentAddressVo=new StudentAddressVo();

                    System.out.println("Before studentAddressVo=> "+studentAddressVo);
                    studentAddressVo.setStudentId(studentVo.getId());
                    studentAddressVo.setAddressType("studentAddress");
                    studentAddressVo.setCountry(country);
                    studentAddressVo.setState(state);
                    studentAddressVo.setCity(city);
                    studentAddressVo.setDistrict(district);
                    studentAddressVo.setPinCode(pinCode);
                    studentAddressVo.setArea(area);
                    studentAddressVo.setAddress(address);
                    studentAddressVo.setLandMark(landMark);

                    System.out.println("address=> "+address);
                    System.out.println("landMark=> "+landMark);
                    System.out.println("After studentAddressVo=> "+studentAddressVo);


                    //Have to call the Backend REST service
                    String BASE_URI = URLs.URL_CREATE_STUDENT_ADDRESS;
                    System.out.println("getActivity()=> "+getActivity());
                    pdScreen1 = new ProgressDialog(getActivity());
                    pdScreen1.setIndeterminate(true);
                    pdScreen1.setCancelable(false);
                    pdScreen1.setMessage("Please wait, processing...");
                    pdScreen1.show();
                    new WebserviceCallAppData(getActivity(),ShowAddressActivity.class,"POST",pdScreen1).execute(BASE_URI,new Gson().toJson(studentAddressVo));

                           /* AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setMessage("Address saved successfully");
                    alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(getActivity().getApplicationContext(), TeacherOrStudentDetailsActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.putExtra("TAG","STUDENT");
                            i.putExtra("MESSAGE", "AddressSaved");
                            startActivity(i);
                            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }
                    });
                    alert.show();*/
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
                case R.id.actv_country:
                    message = "Contry cannot be empty";
                    break;
                case R.id.actv_state:
                    message = "Sate cannot be empty";
                    break;
                case R.id.actv_city:
                    message = "City cannot be empty";
                    break;
                case R.id.actv_pinCode:
                    message = "PinCode cannot be empty";
                    break;
                case R.id.actv_district:
                    message = "District cannot be empty";
                    break;
                case R.id.actv_area:
                    message = "Area cannot be empty";
                    break;
                case R.id.et_address:
                    message = "Address cannot be empty";
                    break;
                case R.id.et_landMark:
                    message = "Landmark cannot be empty";
                    break;
                default:
                    break;
            }
            valid = false;
        }

        if (!valid) {
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        }
        return valid;
    }
}