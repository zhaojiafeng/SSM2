package com.ssm.serving.bean;

/**
 * Created by dllo on 17/12/26.
 */
public class ServingError {

    private String idcardNoError;
    private String accountIdError;
    private String unixHostError;
    private String osUsernameError;
    private String loginPasswdError;
    private boolean isNoError;

    public String getIdcardNoError() {
        return idcardNoError;
    }

    public void setIdcardNoError(String idcardNoError) {
        this.idcardNoError = idcardNoError;
    }

    public String getAccountIdError() {
        return accountIdError;
    }

    public void setAccountIdError(String accountIdError) {
        this.accountIdError = accountIdError;
    }

    public String getUnixHostError() {
        return unixHostError;
    }

    public void setUnixHostError(String unixHostError) {
        this.unixHostError = unixHostError;
    }

    public String getOsUsernameError() {
        return osUsernameError;
    }

    public void setOsUsernameError(String osUsernameError) {
        this.osUsernameError = osUsernameError;
    }

    public String getLoginPasswdError() {
        return loginPasswdError;
    }

    public void setLoginPasswdError(String loginPasswdError) {
        this.loginPasswdError = loginPasswdError;
    }

    public boolean isNoError() {
        return isNoError;
    }

    public void setNoError(boolean noError) {
        isNoError = noError;
    }
}
