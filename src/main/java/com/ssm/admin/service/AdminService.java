package com.ssm.admin.service;

import com.ssm.admin.bean.Admin;
import com.ssm.util.AjaxResult;

import javax.servlet.http.HttpSession;

/**
 * Created by dllo on 17/12/19.
 */
public interface AdminService {

    AjaxResult login(HttpSession session, Admin admin);






    AjaxResult editAdminInfo(HttpSession session, Admin admin);

    AjaxResult alterAdminPassword(HttpSession session, Admin admin,String newPassword);
}
