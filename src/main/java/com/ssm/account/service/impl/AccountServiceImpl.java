package com.ssm.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.account.bean.Account;
import com.ssm.account.bean.AccountError;
import com.ssm.account.mapper.AccountMapper;
import com.ssm.account.service.AccountService;
import com.ssm.util.AjaxResult;
import com.ssm.util.EasyMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/12/20.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;


    @Override
    public AjaxResult addAccount(Account account) {
        AccountError accountError = ValidateAccountError(account);
        if (accountError.getNoError()) {
            account.setCreateDate(new Date());
            account.setBirthdate(new Date());
            account.setStatus("b");
            accountMapper.addAccount(account);
            return new AjaxResult(null);
        }
        return new AjaxResult("-1", accountError);
    }


    @Override
    public AjaxResult deleteAccount(int accountId) {
        accountMapper.deleteAccount(accountId);
        return new AjaxResult(null);
    }


    @Override
    public AjaxResult editAccount(Account account) {
        AccountError accountError = ValidateAccountError(account);
        if (accountError.getNoError()) {
            accountMapper.editAccount(account);
            return new AjaxResult(null);
        }
        return new AjaxResult("-1", accountError);
    }


    @Override
    public AjaxResult alterAccountStatus(Account account) {
        String status = accountMapper.advanceSearchAccount(account).get(0).getStatus();
        if (status.equals("b")) {
            account.setCreateDate(new Date());
            account.setStatus("s");
        } else {
            account.setPauseDate(new Date());
            if (null != account.getCreateDate()) {
                account.setLastLoginTime(account.getCreateDate());
            }
            account.setStatus("b");
        }
        accountMapper.editAccount(account);
        return new AjaxResult(null);
    }


    @Override
    public AjaxResult advanceSearchAccount(Account account, Integer pageNum, Integer pageSize) {
        if (null == pageNum && null == pageSize) {
            return new AjaxResult(accountMapper.advanceSearchAccount(account));
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Account> accountList = accountMapper.advanceSearchAccount(account);
        PageInfo<Account> accountPageInfo = new PageInfo<>(accountList);
        return new AjaxResult(accountPageInfo);
    }


    private Account reviseAccount(Account account) {
        if (StringUtils.isEmpty(account.getRealName())) {
            account.setRealName(null);
        }
        if (StringUtils.isEmpty(account.getIdcardNo())) {
            account.setIdcardNo(null);
        }
        if (StringUtils.isEmpty(account.getLoginName())) {
            account.setLoginName(null);
        }
        if (StringUtils.isEmpty(account.getStatus())) {
            account.setStatus(null);
        }
        return account;
    }

    private AccountError ValidateAccountError(Account account) {
        AccountError accountError = new AccountError();
        accountError.setNoError(true);

        String realName = account.getRealName();
        String idcardNo = account.getIdcardNo();
        String loginName = account.getLoginName();
        String loginPasswd = account.getLoginPasswd();
        String telephone = account.getTelephone();
        int recommenderId;
        String email = account.getEmail();
        String mailaddress = account.getMailaddress();
        String zipcode = account.getZipcode();
        String qq = account.getQq();

        if (StringUtils.isEmpty(realName) || realName.length() > 20 || !EasyMethod.ValidateString(realName)) {
            accountError.setRealNameError("20长度以内的汉字、字母和数字的组合");
            accountError.setNoError(false);
        }
//        if (idcardNo.equals("") || !EasyMethod.ValidateIdCard(idcardNo)) {
//            accountError.setIdcardNoError("正确的身份证号码格式");
//            accountError.setNoError(false);
//        }
        if (!StringUtils.isEmpty(loginName)) {
            if (loginName.length() > 30 || !EasyMethod.ValidateString(loginName)) {
                accountError.setLoginNameError("30长度以内的字母、数字和下划线的组合");
                accountError.setNoError(false);
            }
        }
        if (!StringUtils.isEmpty(loginPasswd)) {
            if (loginPasswd.length() > 30 || !EasyMethod.ValidateString(loginPasswd)) {
                accountError.setLoginPasswdError("30长度以内的字母、数字和下划线的组合");
                accountError.setNoError(false);
            }
        }
        if (telephone != null || !StringUtils.isEmpty(telephone)) {
            if (!EasyMethod.ValidateTel(telephone)) {
                accountError.setTelephoneError("正确的电话号码格式：手机或固话");
                accountError.setNoError(false);
            }
        }
        if (account.getRecommenderId() != null) {
            recommenderId = account.getRecommenderId();
            Account acc = new Account();
            acc.setRecommenderId(recommenderId);
            List<Account> accountList = accountMapper.advanceSearchAccount(acc);
            if (accountList.size() == 0) {
                accountError.setRecommenderIdError("请确认推荐人账务账号ID");
                accountError.setNoError(false);
            }
        }
        if (email != "" && email != null) {
            if (!EasyMethod.ValidateEmail(email)) {
                accountError.setEmailError("50长度以内，合法的 Email 格式");
                accountError.setNoError(false);
            }
        }
        if (mailaddress.length() > 50) {
            accountError.setMailaddressError("50长度以内");
            accountError.setNoError(false);
        }
        if (zipcode != "" && zipcode != null) {
            if (zipcode.length() != 6) {
                accountError.setZipcodeError("6位数字");
                accountError.setNoError(false);
            }
        }
        if (qq != "" && qq != null) {
            if (qq.length() < 5 || qq.length() > 13) {
                accountError.setQqError("5到13位数字");
                accountError.setNoError(false);
            }
        }
        return accountError;
    }

}
