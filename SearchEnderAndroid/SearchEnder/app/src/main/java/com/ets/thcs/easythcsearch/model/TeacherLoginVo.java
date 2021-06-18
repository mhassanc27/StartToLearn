package com.ets.thcs.easythcsearch.model;

/**
 * Created by Manirul on 5/1/2021.
 */
public class TeacherLoginVo {

    private String userName;
    private String userPassword;
    private String sessionId;

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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "TeacherLoginVo{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
