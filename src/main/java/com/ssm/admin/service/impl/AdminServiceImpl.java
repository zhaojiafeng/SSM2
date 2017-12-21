package com.ssm.admin.service.impl;

import com.ssm.admin.bean.Admin;
import com.ssm.admin.bean.LoginError;
import com.ssm.admin.mapper.AdminMapper;
import com.ssm.admin.service.AdminService;
import com.ssm.util.AjaxResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by dllo on 17/12/19.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;


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
            if (admin.getVerifyCode().isEmpty()){
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
    public AjaxResult editAdminInfo(HttpSession session, Admin admin) {
        return null;
    }

    @Override
    public AjaxResult alterAdminPassword(HttpSession session, Admin admin, String newPassword) {
        return null;
    }
}
