package com.ets.thcs.easythcsearch.model;

import java.sql.Timestamp;

/**
 * Created by Manirul on 10/25/2016.
 */
public class TeacherVo {

    private String userName;
    private String userPassword;
    private String emailId;
    private String phoneNo;
    private String imei;
    private String firstName;
    private String lastName;
    private String gender;
    private String deviceLocation;
    private String loginType;
    private Timestamp registrationTime;
    private String isValid;
    private String isDeleted;

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getDeviceLocation() {
        return deviceLocation;
    }
    public void setDeviceLocation(String deviceLocation) {
        this.deviceLocation = deviceLocation;
    }
    public String getLoginType() {
        return loginType;
    }
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
    public String getImei() {
        return imei;
    }
    public void setImei(String imei) {
        this.imei = imei;
    }
    public Timestamp getRegistrationTime() {
        return registrationTime;
    }
    public void setRegistrationTime(Timestamp registrationTime) {
        this.registrationTime = registrationTime;
    }
}
