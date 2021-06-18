package com.ets.thcs.easythcsearch.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JSONParser {

    public static String getJsonStringFromAssetJSONFile (String filename, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();
        String jsonString=new String(formArray);
        //System.out.println("jsonString=> "+jsonString);
        return jsonString;
    }
    public List<JSONObject> getAllCountryJsonObjects(String jsonString){
        List<JSONObject> countryJsonObjectList=null;

        try {
            countryJsonObjectList=new ArrayList<>();
            JSONArray countryJsonArray=new JSONArray(jsonString);
            for(int i=0;i<countryJsonArray.length();i++){

                JSONObject countryJsonObject=countryJsonArray.getJSONObject(i);
                countryJsonObjectList.add(countryJsonObject);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return countryJsonObjectList;
    }
    public List<JSONObject> getStateJsonObjectByCountry(JSONObject countryJsonObject){
        List<JSONObject> stateJsonObjectList=null;
        try {
            stateJsonObjectList=new ArrayList<>();
            JSONArray stateJsonArray=countryJsonObject.getJSONArray("states");
            for (int i=0;i<stateJsonArray.length();i++){
                JSONObject stateJsonObject=stateJsonArray.getJSONObject(i);
                stateJsonObjectList.add(stateJsonObject);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return stateJsonObjectList;
    }
    public List<JSONObject> getCityJsonObjectByState(JSONObject stateJsonObject){
        List<JSONObject> cityJsonObjectList=null;
        try{
            cityJsonObjectList=new ArrayList<>();
            JSONArray cityJsonArray=stateJsonObject.getJSONArray("cities");
            for (int i=0;i<cityJsonArray.length();i++){
                JSONObject cityJsonObject=cityJsonArray.getJSONObject(i);
                cityJsonObjectList.add(cityJsonObject);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return cityJsonObjectList;
    }
    public List<String> getAllCountries(String jsonString){
        List<String> countryList=null;

        try{
            countryList=new ArrayList<>();
            JSONArray jsonArray=new JSONArray(jsonString);
            for(int i=0;i<jsonArray.length();i++){

                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String countryName=jsonObject.getString("name");
                countryList.add(countryName);
            }

        }catch(Exception e){

            e.printStackTrace();
        }
        return countryList;
    }

    public List<String> getStateByCountry(String jsonString,String countryName){
        List<String> stateList=null;

        try {
            stateList=new ArrayList<>();
            JSONArray countryJsonArray=new JSONArray(jsonString);
            for(int i=0;i<countryJsonArray.length();i++){
                JSONObject countryJsonObject=countryJsonArray.getJSONObject(i);
                if(countryJsonObject.getString("name").equals(countryName)){
                    JSONArray stateJsonArray=countryJsonObject.getJSONArray("states");
                    for (int j=0;j<stateJsonArray.length();j++){
                        JSONObject stateJsonObject=stateJsonArray.getJSONObject(j);
                        String stateName=stateJsonObject.getString("name");
                        stateList.add(stateName);
                    }
                    break;
                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return stateList;
    }
    public List<String> getCityByState(String jsonString,String countryName,String stateName){
        List<String> cityList=null;

        try{

            cityList=new ArrayList<>();
            JSONArray countryJsonArray=new JSONArray(jsonString);
            for(int i=0;i<countryJsonArray.length();i++){
                JSONObject countryJsonObject=countryJsonArray.getJSONObject(i);
                if(countryJsonObject.getString("name").equals(countryName)){
                    JSONArray stateJsonArray=countryJsonObject.getJSONArray("states");
                    for (int j=0;j<stateJsonArray.length();j++){
                        JSONObject stateJsonObject=stateJsonArray.getJSONObject(j);
                        if(stateJsonObject.getString("name").equals(stateName)){
                            JSONArray cityJsonArray=stateJsonObject.getJSONArray("cities");
                            for(int k=0;k<cityJsonArray.length();k++){
                                JSONObject cityJsonObject=cityJsonArray.getJSONObject(k);
                                String cityName=cityJsonObject.getString("name");
                                cityList.add(cityName);
                            }
                        }

                    }
                    break;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return cityList;
    }

    public List<String> getAllPinCode(String jsonString){
        List<String> pinCodeList=null;
        try {
            pinCodeList=new ArrayList<>();
            JSONArray postOfficeJsonArray=new JSONArray(jsonString);
            for (int i=0;i<postOfficeJsonArray.length();i++){
                JSONObject postOfficeJsonObject=postOfficeJsonArray.getJSONObject(i);
                String pinCode=postOfficeJsonObject.getString("pincode");
                pinCodeList.add(pinCode);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return pinCodeList;
    }

    public List<String> getPinCodeByState(String jsonString,String stateName){
        List<String> pinCodeList=null;
        Set<String> pinCodeSet=null;
        try {
            pinCodeList=new ArrayList<>();
            pinCodeSet=new HashSet<>();
            JSONArray postOfficeJsonArray=new JSONArray(jsonString);
            pinCodeSet.clear();
            pinCodeList.clear();
            for (int i=0;i<postOfficeJsonArray.length();i++){
                JSONObject postOfficeJsonObject=postOfficeJsonArray.getJSONObject(i);
                if (postOfficeJsonObject.getString("stateName").equalsIgnoreCase(stateName)){
                    String pinCode=postOfficeJsonObject.getString("pincode");
                    pinCodeSet.add(pinCode);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        //pinCodeSet.addAll(pinCodeList);
        System.out.println("In getPinCodeByState pinCodeSet=> "+pinCodeSet);
        System.out.println("In getPinCodeByState pinCodeSet.size()=> "+pinCodeSet.size());
        //pinCodeList.clear();
        pinCodeList.addAll(pinCodeSet);
        System.out.println("In getPinCodeByState pinCodeList=> "+pinCodeList);
        System.out.println("In getPinCodeByState pinCodeList.size()=> "+pinCodeList.size());
        return pinCodeList;
    }

    public List<String> getDistrictByPinCode(String jsonString,String pinCode){
        List<String> districtList=null;
        Set<String> districtSet=null;
        try {
            districtList=new ArrayList<>();
            districtSet=new HashSet<>();
            JSONArray postOfficeJsonArray=new JSONArray(jsonString);
            districtList.clear();
            districtSet.clear();
            for (int i=0;i<postOfficeJsonArray.length();i++){
                JSONObject postOfficeJsonObject=postOfficeJsonArray.getJSONObject(i);
                if (postOfficeJsonObject.getString("pincode").equals(pinCode)){
                    String district=postOfficeJsonObject.getString("districtName");
                    districtSet.add(district);
                }

            }
            districtList.addAll(districtSet);

        }catch (Exception e){
            e.printStackTrace();
        }
        return districtList;
    }

    /*public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("yourfilename.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }*/
}
