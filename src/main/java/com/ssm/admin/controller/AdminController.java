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

    /**
     * 登录方法
     * @param session 在session中放置adminId
     * @param admin 用户登录信息
     * @return ajax类型数据
     */
    @ResponseBody
    @RequestMapping("/login")
    public AjaxResult login(HttpSession session, Admin admin) {
        return adminService.login(session, admin);
    }

    /**
     * 生成验证码
     * @param response 处理输出流
     * @param session 在session中放置验证码
     * @throws IOException 抛出异常
     */
    @ResponseBody
    @RequestMapping(value = "/findVerifyCode")
    public void findVerifyCode(HttpServletResponse response, HttpSession session) throws IOException {
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        session.setAttribute("code", vc.getText());
        VerifyCode.output(image, response.getOutputStream());
    }










    /**
     * 编辑用户信息
     * @param session 根据session获取adminId
     * @param admin 变更信息的存储位置
     * @return ajax
     */
    @ResponseBody
    @RequestMapping("/editAdminInfo")
    public AjaxResult editAdminInfo(HttpSession session, Admin admin) {
//        return adminService.editAdminInfo(session, admin);
        return null;
    }


    /**
     * 编辑admin密码
     * @param session 根据session获取adminId
     * @param admin 密码存放位置
     * @param newPassword 新密码
     * @return ajax
     */
    @ResponseBody
    @RequestMapping("/alterAdminPassword")
    public AjaxResult alterAdminPassword(HttpSession session, Admin admin,String newPassword) {
//        return adminService.editAdminInfo(session, admin);
        return null;
    }
}
