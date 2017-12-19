package com.ssm.account.mapper;

import com.ssm.account.bean.Account;

public interface AccountMapper {
    int insert(Account record);

    int insertSelective(Account record);
}