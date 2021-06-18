package com.ets.thcs.easythcsearch;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

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
import com.ets.thcs.easythcsearch.model.PostOfficeVo;
import com.ets.thcs.easythcsearch.model.StudentAddressVo;
import com.ets.thcs.easythcsearch.model.StudentVo;
import com.ets.thcs.easythcsearch.util.ApiClient;
import com.ets.thcs.easythcsearch.util.JSONParser;
import com.ets.thcs.easythcsearch.util.WebserviceCallAppData;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditStudentAddressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditStudentAddressFragment extends Fragment {

    private AutoCompleteTextView actvCountry;
    private AutoCompleteTextView   actvState;
    private AutoCompleteTextView   actvCity;
    private AutoCompleteTextView   actvPinCode;
    private AutoCompleteTextView   actvDistrict;
    private AutoCompleteTextView   actvArea;
    private EditText etAddress;
    private EditText   etLandMark;
    private Button btnSave;

    private ArrayAdapter<String> countryAdapter;
    private ArrayAdapter<String> stateAdapter;
    private ArrayAdapter<String> cityAdapter;
    private ArrayAdapter<String> pinCodeAdapter;
    private ArrayAdapter<String> districtAdapter;
    private ArrayAdapter<String> areaAdapter;

    private int id;
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
    private String studentAddressJson;
    private StudentAddressVo studentAddressVo;
    private String studentJson;
    private StudentVo studentVo;


    private JSONParser jsonParser;
    private String countryJsonString;
    private String postOfficeJsonString;
    private List<String> countriesList;
    private List<String> statesList= Collections.emptyList();
    private List<String> citiesList;
    private List<String> pincodesList;
    private List<String> districtsList;
    private String[] countryList ={"India","Bangladesh","Nepal",".UAE","USA","UK","Denmark","Indonesia"};
    private String[] stateList ={"West Bengal","Uttar Pradesh","Assam","Tamil Nadu","Maharastra","Karnataka","Orissa","Bihar"};
    private String[] cityList ={"Kolkata","Dankuni","Bardwan","Durgapur","Rampurhat","Howrah","Kalyani","Bali"};
    private String[] pinCodeList ={"700135","700156","731224","731242","700134","700092","700091","712304"};
    private String[] districtList ={"North 24 Parganas","South 24 Parganas","Bardwan","Birbhum","Hooghly","Howrah","Malda","Murshidabad"};
    private String[] areaList ={"New Town Action Area III","New Town Action Area II","New Town Action Area I","Salt Lake Sector 5","Esplaned New Market Area","Narkel Bagan","Chinar Park","Sealdah"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_edit_student_address, container, false);
        System.out.println("In EditStudentAddressFragment");
        init(rootView);


        return rootView;
    }
    public void init(View rootView){



        actvCountry= (AutoCompleteTextView) rootView.findViewById(R.id.actv_country);
        actvState= (AutoCompleteTextView) rootView.findViewById(R.id.actv_state);
        actvCity= (AutoCompleteTextView) rootView.findViewById(R.id.actv_city);
        actvPinCode= (AutoCompleteTextView) rootView.findViewById(R.id.actv_pinCode);
        actvDistrict= (AutoCompleteTextView) rootView.findViewById(R.id.actv_district);
        actvArea= (AutoCompleteTextView) rootView.findViewById(R.id.actv_area);
        etAddress= (EditText) rootView.findViewById(R.id.et_address);
        etLandMark= (EditText) rootView.findViewById(R.id.et_landMark);
        btnSave=(Button) rootView.findViewById(R.id.btn_save);

        studentAddressJson=getActivity().getIntent().getStringExtra("studentAddressJson");
        studentAddressVo=new Gson().fromJson(studentAddressJson,StudentAddressVo.class);

        id=studentAddressVo.getId();
        country=studentAddressVo.getCountry();
        state=studentAddressVo.getState();
        city=studentAddressVo.getCity();
        pinCode=studentAddressVo.getPinCode();
        district=studentAddressVo.getDistrict();
        area=studentAddressVo.getArea();
        address=studentAddressVo.getAddress();
        landMark=studentAddressVo.getLandMark();

        actvCountry.setText(country);
        actvState.setText(state);
        actvCity.setText(city);
        actvPinCode.setText(pinCode);
        actvDistrict.setText(district);
        actvArea.setText(area);
        etAddress.setText(address);
        etLandMark.setText(landMark);


        //actvState.setEnabled(false);
        //actvCity.setEnabled(false);
        //actvPinCode.setEnabled(false);
        //actvDistrict.setEnabled(false);
        //actvArea.setEnabled(false);
        //etAddress.setEnabled(false);
        //etLandMark.setEnabled(false);

        try {
            jsonParser=new JSONParser();
            countryJsonString=JSONParser.getJsonStringFromAssetJSONFile("countries_states_cities.json",getContext());
            countriesList=jsonParser.getAllCountries(countryJsonString);

            //Get country,state,district by pincode using API
            //List<PostOfficeVo> postOfficeVoList= new ApiClient().callApi(getContext(),"700135");
            //System.out.println("postOfficeVoList.get(0).getDistrict()=> "+postOfficeVoList.get(0).getDistrict());

            //Get country,state,district by pincode using json file
            postOfficeJsonString=JSONParser.getJsonStringFromAssetJSONFile("pincodes.json",getContext());
            pincodesList=jsonParser.getAllPinCode(postOfficeJsonString);

        }catch (Exception e){
            e.printStackTrace();
        }

        //Id input
        id=studentAddressVo.getId();

        //Country Input
        countryAdapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.select_dialog_item, countriesList);
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
                statesList=jsonParser.getStateByCountry(countryJsonString,country);

                stateAdapter = new ArrayAdapter<String>
                        (getActivity(),android.R.layout.select_dialog_item, statesList);
                System.out.println("after statesList=> "+statesList);
                actvState.setThreshold(1);//will start working from first character
                actvState.setAdapter(stateAdapter);//setting the adapter data into the AutoCompleteTextView
                actvState.setTextColor(Color.RED);
            }
        });

        //State Input


        actvState.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                state = (String) parent.getItemAtPosition(position);
                int pos = Arrays.asList(stateList).indexOf(state);
                System.out.println("State choosen by user is => "+state);
                System.out.println("Position is=> "+pos);

                actvCity.setEnabled(true);
                citiesList=jsonParser.getCityByState(countryJsonString,country,state);
                System.out.println("citiesList=> "+citiesList);

                cityAdapter = new ArrayAdapter<String>
                        (getActivity(),android.R.layout.select_dialog_item, citiesList);
                actvCity.setThreshold(1);//will start working from first character
                actvCity.setAdapter(cityAdapter);//setting the adapter data into the AutoCompleteTextView
                actvCity.setTextColor(Color.RED);
            }
        });


        //City Input

        actvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                city=(String)parent.getItemAtPosition(position);
                int pos=Arrays.asList(cityList).indexOf(city);
                System.out.println("City choosen by user is => "+city);
                System.out.println("Position is=> "+pos);

                actvPinCode.setEnabled(true);
                //System.out.println("state=>"+state);

                pincodesList=jsonParser.getPinCodeByState(postOfficeJsonString,state);
                System.out.println("pincodesList=> "+pincodesList);

                pinCodeAdapter = new ArrayAdapter<String>
                        (getActivity(),android.R.layout.select_dialog_item, pincodesList);
                actvPinCode.setThreshold(1);//will start working from first character
                actvPinCode.setAdapter(pinCodeAdapter);//setting the adapter data into the AutoCompleteTextView
                actvPinCode.setTextColor(Color.RED);
            }
        });

        //PinCode Input

        actvPinCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pinCode=(String)parent.getItemAtPosition(position);
                int pos=Arrays.asList(pinCodeList).indexOf(pinCode);
                System.out.println("PinCode choosen by user is => "+pinCode);
                System.out.println("Position is=> "+pos);

                actvDistrict.setEnabled(true);
                districtsList=jsonParser.getDistrictByPinCode(postOfficeJsonString,pinCode);
                System.out.println("districtsList=> "+districtsList);

                districtAdapter = new ArrayAdapter<String>
                        (getActivity(),android.R.layout.select_dialog_item, districtsList);
                actvDistrict.setThreshold(1);//will start working from first character
                actvDistrict.setAdapter(districtAdapter);//setting the adapter data into the AutoCompleteTextView
                actvDistrict.setTextColor(Color.RED);

            }
        });

        //District Input

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
        System.out.println("In address input address=> "+address);

        //LandMark Input
        landMark=etLandMark.getText().toString();
        System.out.println("In landmark input landMark=> "+landMark);

        //On button Click
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    address=etAddress.getText().toString();
                    landMark=etLandMark.getText().toString();

                    //Get the Student Data
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.LOGIN_INFO, Context.MODE_PRIVATE);
                    studentJson=sharedPreferences.getString("studentJson","");
                    //studentJson=getActivity().getIntent().getStringExtra("studentJson");
                    if(studentJson!=null && studentJson!=""){
                        System.out.println("In EditStudentAddressFragment studentJson=> "+studentJson);
                        Gson gson=new Gson();
                        studentVo=gson.fromJson(studentJson, StudentVo.class);
                        System.out.println("In EditStudentAddressFragment studentVo=>"+studentVo);
                    }


                    //Creating the studentAddressVo object

                    studentAddressVo=new StudentAddressVo();

                    System.out.println("Before studentAddressVo=> "+studentAddressVo);
                    studentAddressVo.setId(id);
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
                    String BASE_URI = URLs.URL_UPDATE_STUDENT_ADDRESS;
                    System.out.println("getActivity()=> "+getActivity());
                    pdScreen1 = new ProgressDialog(getActivity());
                    pdScreen1.setIndeterminate(true);
                    pdScreen1.setCancelable(false);
                    pdScreen1.setMessage("Please wait, processing...");
                    pdScreen1.show();
                    new WebserviceCallAppData(getActivity(),ShowAddressActivity.class,"PUT",pdScreen1).execute(BASE_URI,new Gson().toJson(studentAddressVo));

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