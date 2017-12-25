package com.ssm.account.service;

import com.ssm.account.bean.Account;
import com.ssm.util.AjaxResult;

/**
 * Created by dllo on 17/12/20.
 */
public interface AccountService {

    AjaxResult addAccount(Account account);

    AjaxResult deleteAccount(int accountId);

    AjaxResult editAccount(Account account);

    AjaxResult alterAccountStatus(Account account);

    AjaxResult advanceSearchAccount(Account account, Integer pageNum, Integer pageSize);

}
