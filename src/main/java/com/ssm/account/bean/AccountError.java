package com.ssm.account.bean;

/**
 * Created by dllo on 17/12/25.
 */
public class AccountError {

    private String realNameError;
    private String idcardNoError;
    private String loginNameError;
    private String loginPasswdError;
    private String telephoneError;
    private String recommenderIdError;
    private String emailError;
    private String mailaddressError;
    private String zipcodeError;
    private String qqError;
    private Boolean isNoError;

    public String getRealNameError() {
        return realNameError;
    }

    public void setRealNameError(String realNameError) {
        this.realNameError = realNameError;
    }

    public String getIdcardNoError() {
        return idcardNoError;
    }

    public void setIdcardNoError(String idcardNoError) {
        this.idcardNoError = idcardNoError;
    }

    public String getLoginNameError() {
        return loginNameError;
    }

    public void setLoginNameError(String loginNameError) {
        this.loginNameError = loginNameError;
    }

    public String getLoginPasswdError() {
        return loginPasswdError;
    }

    public void setLoginPasswdError(String loginPasswdError) {
        this.loginPasswdError = loginPasswdError;
    }

    public String getTelephoneError() {
        return telephoneError;
    }

    public void setTelephoneError(String telephoneError) {
        this.telephoneError = telephoneError;
    }

    public String getRecommenderIdError() {
        return recommenderIdError;
    }

    public void setRecommenderIdError(String recommenderIdError) {
        this.recommenderIdError = recommenderIdError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getMailaddressError() {
        return mailaddressError;
    }

    public void setMailaddressError(String mailaddressError) {
        this.mailaddressError = mailaddressError;
    }

    public String getZipcodeError() {
        return zipcodeError;
    }

    public void setZipcodeError(String zipcodeError) {
        this.zipcodeError = zipcodeError;
    }

    public String getQqError() {
        return qqError;
    }

    public void setQqError(String qqError) {
        this.qqError = qqError;
    }

    public Boolean getNoError() {
        return isNoError;
    }

    public void setNoError(Boolean noError) {
        isNoError = noError;
    }
}
