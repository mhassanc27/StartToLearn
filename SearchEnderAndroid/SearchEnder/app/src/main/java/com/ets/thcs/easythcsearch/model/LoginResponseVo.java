package com.ets.thcs.easythcsearch.model;

/**
 * Created by Manirul on 10/29/2016.
 */
public class LoginResponseVo {
    String sessionId;
    String isAuthentic;
    String isSessionActive;
    String emailId;
    String phoneNo;
    String firstName;
    String lastName;
    String isSuccess;

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
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

    String gender;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIsAuthentic() {
        return isAuthentic;
    }

    public void setIsAuthentic(String isAuthentic) {
        this.isAuthentic = isAuthentic;
    }

    public String getIsSessionActive() {
        return isSessionActive;
    }

    public void setIsSessionActive(String isSessionActive) {
        this.isSessionActive = isSessionActive;
    }
}
