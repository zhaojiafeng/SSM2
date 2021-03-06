package com.ssm.admin.service;

import com.ssm.admin.bean.Admin;
import com.ssm.util.AjaxResult;

import javax.servlet.http.HttpSession;

/**
 * Created by dllo on 17/12/19.
 */
public interface AdminService {

    AjaxResult login(HttpSession session, Admin admin);

    AjaxResult addAdminAndRole(Admin admin, String roles);

    AjaxResult deleteAdminByAdminId(int adminId);

    AjaxResult editAdmin(Admin admin, String roles, String unroles);

    AjaxResult editAdminInfo(Admin admin);

    AjaxResult alterAdminPassword(Admin admin,String newPassword);

    AjaxResult resetPwd(String adminIds);

    AjaxResult findAllAdmins(Integer pageNum, Integer pageSize);

    AjaxResult advanceSearchAdmin(Integer moduleId, String rolename, Integer pageNum, Integer pageSize);

    AjaxResult findAdminAndRolesByAdminId(int adminId);
}
