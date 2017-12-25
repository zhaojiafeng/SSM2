package com.ssm.account.controller;

import com.ssm.account.bean.Account;
import com.ssm.account.service.AccountService;
import com.ssm.util.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


    /**
     * 增加account
     * @param account 增加的account信息
     * @return 返回code
     */
    @ResponseBody
    @RequestMapping("/addAccount")
    public AjaxResult addAccount(Account account) {
        return accountService.addAccount(account);
    }


    /**
     * 删除account
     * @param accountId 根据accouId删除数据
     * @return 返回code
     */
    @ResponseBody
    @RequestMapping("/deleteAccount")
    public AjaxResult deleteAccount(int accountId) {
        return accountService.deleteAccount(accountId);
    }


    /**
     * 编辑account
     * @param account 编辑后的account
     * @return 返回code
     */
    @ResponseBody
    @RequestMapping("/editAccount")
    public AjaxResult editAccount(Account account) {
        return accountService.editAccount(account);
    }


    @ResponseBody
    @RequestMapping("/alterAccountStatus")
    public AjaxResult alterAccountStatus(Account account) {
        return accountService.alterAccountStatus(account);
    }


    /**
     * 发现所有的account
     * @param currentPage 当前页
     * @param pageSize 每页数量
     * @return account的ajax数据
     */
    @ResponseBody
    @RequestMapping("/findAllAccount")
    public AjaxResult findAllAccount(Integer currentPage, Integer pageSize) {
        return accountService.advanceSearchAccount(null,currentPage,pageSize);
    }


    /**
     * 根据id查询account
     * @param account 只有accountId
     * @return account的ajax数据
     */
    @ResponseBody
    @RequestMapping("/findAccountByAccountId")
    public AjaxResult findAccountByAccountId(Account account) {
        return accountService.advanceSearchAccount(account,null,null);
    }


    /**
     * 高级搜索account
     * @param account 搜索条件
     * @param currentPage 当前页
     * @param pageSize 每页数据数量
     * @return account的ajax
     */
    @ResponseBody
    @RequestMapping("/advanceSearchAccount")
    public AjaxResult advanceSearchAccount(Account account, Integer currentPage, Integer pageSize) {
        return accountService.advanceSearchAccount(account,currentPage,pageSize);
    }

}
