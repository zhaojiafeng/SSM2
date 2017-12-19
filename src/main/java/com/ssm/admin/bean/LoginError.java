package com.ssm.admin.bean;

/**
 * Created by dllo on 17/12/19.
 */
public class LoginError {

    private String adminCodeError;
    private String passwordError;
    private String verifyCodeError;
    private String loginError;


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

    public String getVerifyCodeError() {
        return verifyCodeError;
    }

    public void setVerifyCodeError(String verifyCodeError) {
        this.verifyCodeError = verifyCodeError;
    }

    public String getLoginError() {
        return loginError;
    }

    public void setLoginError(String loginError) {
        this.loginError = loginError;
    }
}
