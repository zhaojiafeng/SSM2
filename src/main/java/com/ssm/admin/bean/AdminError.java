package com.ssm.admin.bean;

/**
 * Created by dllo on 17/12/22.
 */
public class AdminError {

    private String nameError;
    private String adminCodeError;
    private String passwordError;
    private String telephoneError;
    private String emailError;
    private boolean noError;

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getAdminCodeError() {
        return adminCodeError;
    }

    public void setAdminCodeError(String adminCodeError) {
        this.adminCodeError = adminCodeError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getTelephoneError() {
        return telephoneError;
    }

    public void setTelephoneError(String telephoneError) {
        this.telephoneError = telephoneError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public boolean isNoError() {
        return noError;
    }

    public void setNoError(boolean noError) {
        this.noError = noError;
    }
}
