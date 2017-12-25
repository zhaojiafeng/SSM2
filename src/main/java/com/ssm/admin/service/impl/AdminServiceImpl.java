package com.ssm.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.admin.bean.Admin;
import com.ssm.admin.bean.AdminError;
import com.ssm.admin.bean.LoginError;
import com.ssm.admin.mapper.AdminMapper;
import com.ssm.admin.service.AdminService;
import com.ssm.module.mapper.ModuleMapper;
import com.ssm.role.bean.Role;
import com.ssm.role.mapper.RoleMapper;
import com.ssm.util.AjaxResult;
import com.ssm.util.EasyMethod;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by dllo on 17/12/19.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private ModuleMapper moduleMapper;


    @Override
    public AjaxResult login(HttpSession session, Admin admin) {
        LoginError loginError = new LoginError();
        List<Admin> adminList = adminMapper.findAdminByAdminCode(admin.getAdminCode());

        if (admin.getAdminCode().isEmpty() || admin.getPassword().isEmpty() || admin.getVerifyCode().isEmpty()) {
            if (admin.getAdminCode().isEmpty()) {
                loginError.setAdminCodeError("用户名不能为空");
            }
            if (admin.getPassword().isEmpty()) {
                loginError.setPasswordError("密码不能为空");
            }
            if (admin.getVerifyCode().isEmpty()) {
                loginError.setVerifyCodeError("验证码不能为空");
            }
        } else {
            if (adminList.size() > 0) {
                if (admin.getPassword().equals(adminList.get(0).getPassword())) {
                    String code = (String) session.getAttribute("code");
                    if (code.equals(admin.getVerifyCode())) {
                        session.setAttribute("adminId", adminList.get(0).getAdminId());
                        session.setAttribute("isLogin", true);
                        return new AjaxResult(null);
                    } else {
                        loginError.setVerifyCodeError("验证码有误，请重新输入");
                    }
                } else {
                    loginError.setPasswordError("密码有误，请重新输入");
                }
            } else {
                loginError.setAdminCodeError("该用户不存在，请重新输入");
            }
        }
        return new AjaxResult("-1", loginError);
    }


    @Override
    public AjaxResult addAdminAndRole(Admin admin, String roles) {
        AdminError adminError = ValidateAdminInAddError(admin);
        if (adminError.isNoError()) {
            admin.setEnrolldate(new Date());
            adminMapper.addAdminSelect(admin);
            addAdmin_role(adminMapper.findLastAdmin().getAdminId(), roles);
            return new AjaxResult(null);
        } else {
            return new AjaxResult("-1", adminError);
        }
    }


    @Override
    public AjaxResult deleteAdminByAdminId(int adminId) {
        adminMapper.deleteAdmin(adminId);
        return new AjaxResult(null);
    }


    @Override
    public AjaxResult editAdmin(Admin admin, String roles, String unroles) {
        AdminError adminError = ValidateAdminInEditError(admin);
        if (adminError.isNoError()) {
            adminMapper.editAdminInfo(admin);
            addAdmin_role(admin.getAdminId(), roles);
            deleteAdmin_role(admin.getAdminId(), unroles);
            return new AjaxResult(null);
        } else {
            return new AjaxResult("-1", adminError);
        }
    }


    @Override
    public AjaxResult editAdminInfo(Admin admin) {
        AdminError adminError = ValidateAdminInEditError(admin);
        if (adminError.isNoError()) {
            adminMapper.editAdminInfo(admin);
            return new AjaxResult(null);
        } else {
            return new AjaxResult("-1", adminError);
        }
    }


    @Override
    public AjaxResult alterAdminPassword(Admin admin, String newPassword) {
        List<Admin> adminList = adminMapper.findAdminByAdminId(admin.getAdminId());
        if (!adminList.isEmpty()) {
            AdminError adminError = new AdminError();
            if (EasyMethod.ValidateString(admin.getPassword()) && EasyMethod.ValidateString(newPassword)) {
                if (admin.getPassword().equals(adminList.get(0).getPassword())) {
                    admin.setPassword(newPassword);
                    adminMapper.editAdminInfo(admin);
                    return new AjaxResult(null);
                } else {
                    adminError.setPasswordError("原密码输入有误，请确认");
                    return new AjaxResult("-1", adminError);
                }
            } else {
                if (!EasyMethod.ValidateString(admin.getPassword())) {
                    adminError.setPasswordError("30长度以内的字母、数字和下划线的组合");
                }
                if (!EasyMethod.ValidateString(newPassword)) {
                    adminError.setNewPasswordError("30长度以内的字母、数字和下划线的组合");
                }
                return new AjaxResult("-1", adminError);
            }
        }
        return new AjaxResult("-2", "你可长点心把，你登录了么？？？");
    }


    @Override
    public AjaxResult resetPwd(String adminIds) {
        String[] adIds = adminIds.split(",");
        List adminIdList = new ArrayList();
        for (String adminId : adIds) {
            adminIdList.add(adminId);
        }
        adminMapper.resetPwd(adminIdList);
        return new AjaxResult(null);
    }


    @Override
    public AjaxResult findAllAdmins(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> adminList = adminMapper.findAdminsBySelected(null);
        for (Admin admin : adminList) {
            List<Role> roleList = roleMapper.findRolesByAdminId(admin.getAdminId());
            admin.setRoleList(roleList);
        }
        PageInfo<Admin> adminPageInfo = new PageInfo<>(adminList);
        return new AjaxResult(adminPageInfo);
    }


    @Override
    public AjaxResult advanceSearchAdmin(Integer moduleId, String rolename, Integer pageNum, Integer pageSize) {
        List<Role> roles = roleMapper.findFuzzyRolesByRolename(rolename);
        List roleIdlist = new ArrayList();
        for (Role role : roles) {
            roleIdlist.add(role.getRoleId());
        }
        List<Admin> temp = adminMapper.findAdminIdByRoleIdModuleId(roleIdlist, moduleId);
        List list = new ArrayList();
        for (Admin admin: temp) {
            list.add(admin.getAdminId());
        }
        List adminIdList = new ArrayList(new HashSet(list));
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> adminList = adminMapper.findAdminsInAdminIds(adminIdList);

        for (Admin admin : adminList) {
            List<Role> roleList = roleMapper.findRolesByAdminId(admin.getAdminId());
            admin.setRoleList(roleList);
        }
        PageInfo<Admin> adminPageInfo = new PageInfo<>(adminList);
        return new AjaxResult(adminPageInfo);
    }


    @Override
    public AjaxResult findAdminAndRolesByAdminId(int adminId) {
        List<Admin> adminList = adminMapper.findAdminByAdminId(adminId);
        for (Admin ad : adminList) {
            ad.setRoleList(roleMapper.findRolesByAdminId(adminId));
        }
        return new AjaxResult(adminList);
    }


    private AdminError ValidateAdminInEditError(Admin admin) {
        String name = admin.getName();
        String telephone = admin.getTelephone();
        String email = admin.getEmail();
        AdminError adminError = new AdminError();
        if ((!EasyMethod.ValidateString(name) || name.length() < 1 || name.length() > 20) ||
                (!telephone.equals("") && !EasyMethod.ValidateTel(telephone)) ||
                (!email.equals("") && !EasyMethod.ValidateEmail(email))
                ) {
            if (!EasyMethod.ValidateString(name) || name.length() < 1 || name.length() > 20) {
                adminError.setNameError("20长度以内的汉字、字母、数字的组合");
            }
            if (telephone != null && !EasyMethod.ValidateTel(telephone)) {
                adminError.setTelephoneError("正确的电话号码格式：手机或固话");
            }
            if (email != "" && !EasyMethod.ValidateEmail(email)) {
                adminError.setEmailError("50长度以内，正确的 email 格式");
            }
            adminError.setNoError(false);
        } else {
            adminError.setNoError(true);
        }
        return adminError;
    }


    private AdminError ValidateAdminInAddError(Admin admin) {
        String name = admin.getName();
        String adminCode = admin.getAdminCode();
        String password = admin.getPassword();
        String telephone = admin.getTelephone();
        String email = admin.getEmail();
        AdminError adminError = new AdminError();
        if ((!EasyMethod.ValidateString(name) || name.length() < 1 || name.length() > 20) ||
                (!EasyMethod.ValidateString(adminCode) || adminCode.length() < 1 || adminCode.length() > 30) ||
                (!EasyMethod.ValidateString(password) || password.length() < 1 || password.length() > 30) ||
                (!telephone.equals("") && !EasyMethod.ValidateTel(telephone)) ||
                (!email.equals("") && !EasyMethod.ValidateEmail(email))
                ) {
            if (!EasyMethod.ValidateString(name) || name.length() < 1 || name.length() > 20) {
                adminError.setNameError("20长度以内的汉字、字母、数字的组合");
            }
            if (!EasyMethod.ValidateString(adminCode) || adminCode.length() < 1 || adminCode.length() > 30) {
                adminError.setAdminCodeError("30长度以内的字母、数字和下划线的组合");
            }
            if (!EasyMethod.ValidateString(password) || password.length() < 1 || password.length() > 30) {
                adminError.setPasswordError("30长度以内的字母、数字和下划线的组合");
            }
            if (telephone != null && !EasyMethod.ValidateTel(telephone)) {
                adminError.setTelephoneError("正确的电话号码格式：手机或固话");
            }
            if (email != "" && !EasyMethod.ValidateEmail(email)) {
                adminError.setEmailError("50长度以内，正确的 email 格式");
            }
            adminError.setNoError(false);
        } else {
            adminError.setNoError(true);
        }
        return adminError;
    }


    private void addAdmin_role(int adminId, String roles) {
        String[] roleIdList = roles.split(",");
        for (String roleId : roleIdList) {
            int count = adminMapper.findad_rByAdminIdRoleId(adminId, Integer.parseInt(roleId));
            if (count == 0) {
                adminMapper.addAdmin_Role(adminId, Integer.parseInt(roleId));
            }
        }
    }


    private void deleteAdmin_role(int adminId, String roles) {
        String[] roleIdList = roles.split(",");
        for (String roleId : roleIdList) {
            int count = adminMapper.findad_rByAdminIdRoleId(adminId, Integer.parseInt(roleId));
            if (count != 0) {
                adminMapper.deleteAdmin_Role(adminId, Integer.parseInt(roleId));
            }
        }
    }
}
