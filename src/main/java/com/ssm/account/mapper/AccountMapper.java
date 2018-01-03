package com.ssm.account.mapper;

import com.ssm.account.bean.Account;

import java.util.List;

public interface AccountMapper {

    int addAccount(Account account);

    int deleteAccount(int accountId);

    int editAccount(Account account);

    List<Account> advanceSearchAccount(Account account);

}