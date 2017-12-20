package com.ssm.account.service.impl;

import com.ssm.account.mapper.AccountMapper;
import com.ssm.account.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/12/20.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

}
