package com.ets.thcs.easythcsearch.model;

/**
 * Created by Manirul on 10/24/2016.
 */
public class RegResponseVo {
    private String verificationCode;
    private boolean isSuccess;
    private String statusMessage;
    String isAuthentic;

    public String getIsAuthentic() {
        return isAuthentic;
    }

    public void setIsAuthentic(String isAuthentic) {
        this.isAuthentic = isAuthentic;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean success) {
        isSuccess = success;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
