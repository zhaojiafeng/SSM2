package com.ssm.account.controller;

import com.ssm.account.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/12/20.
 */
@Controller
public class AccountController {

    @Resource
    private AccountService accountService;

    @RequestMapping("/account_list")
    public String account_list() {
        return "account/account_list";
    }


    @RequestMapping("/account_add")
    public String account_add() {
        return "account/account_add";
    }


    @RequestMapping("/account_modi")
    public String account_modi() {
        return "account/account_modi";
    }


    @RequestMapping("/account_detail")
    public String account_detail() {
        return "account/account_detail";
    }



}
