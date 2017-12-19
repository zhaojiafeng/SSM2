package com.ssm.admin.controller;

import com.ssm.admin.bean.Admin;
import com.ssm.admin.service.AdminService;
import com.ssm.util.AjaxResult;
import com.ssm.util.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by dllo on 17/12/19.
 */
@Controller
public class AdminController {

    @Resource
    private AdminService adminService;


    @RequestMapping("/loginPage")
    public String login() {
        return "login";
    }


    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @RequestMapping("/user_info")
    public String user_info() {
        return "user/user_info";
    }


    @RequestMapping("/user_modi_pwd")
    public String user_modi_pwd() {
        return "user/user_modi_pwd";
    }


    @RequestMapping("/admin_add")
    public String admin_add() {
        return "admin/admin_add";
    }


    @RequestMapping("/admin_list")
    public String admin_list() {
        return "admin/admin_list";
    }


    @RequestMapping("/admin_modi")
    public String admin_modi() {
        return "admin/admin_modi";
    }


    @ResponseBody
    @RequestMapping("/login")
    public AjaxResult login(HttpSession session, Admin admin) {
        return adminService.login(session, admin);
    }


    @ResponseBody
    @RequestMapping(value = "/findVerifyCode")
    public void findVerifyCode(HttpServletResponse response, HttpSession session) throws IOException {
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        session.setAttribute("code", vc.getText());
        VerifyCode.output(image, response.getOutputStream());
    }



}
