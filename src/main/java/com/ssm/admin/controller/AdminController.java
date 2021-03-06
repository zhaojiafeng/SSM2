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
     *
     * @param session 在session中放置adminId
     * @param admin   用户登录信息
     * @return ajax类型数据
     */
    @ResponseBody
    @RequestMapping("/login")
    public AjaxResult login(HttpSession session, Admin admin) {
        return adminService.login(session, admin);
    }


    /**
     * 生成验证码
     *
     * @param response 处理输出流
     * @param session  在session中放置验证码
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
     * 退出登录，清空session
     *
     * @param session 清空session
     * @return 返回
     */
    @ResponseBody
    @RequestMapping("/signOut")
    public AjaxResult signOut(HttpSession session) {
        session.invalidate();
        return new AjaxResult(null);
    }


    /**
     * 增加admin
     *
     * @param admin admin信息
     * @param roles admin的角色
     * @return null
     */
    @ResponseBody
    @RequestMapping("/addAdminAndRole")
    public AjaxResult addAdminAndRole(Admin admin, String roles) {
        return adminService.addAdminAndRole(admin, roles);
    }


    /**
     * 删除admin
     *
     * @param adminId admin的id
     * @return null
     */
    @ResponseBody
    @RequestMapping("/deleteAdminByAdminId")
    public AjaxResult deleteAdminByAdminId(int adminId) {
        return adminService.deleteAdminByAdminId(adminId);
    }


    /**
     * 编辑admin信息
     *
     * @param admin   根据id更改信息
     * @param roles   选中的角色
     * @param unroles 未选中的角色
     * @return 返回null
     */
    @ResponseBody
    @RequestMapping("/editAdmin")
    public AjaxResult editAdmin(Admin admin, String roles, String unroles) {
        return adminService.editAdmin(admin, roles, unroles);
    }


    /**
     * 编辑用户信息
     *
     * @param session 根据session获取adminId
     * @param admin   变更信息的存储位置
     * @return ajax
     */
    @ResponseBody
    @RequestMapping("/editAdminInfo")
    public AjaxResult editAdminInfo(HttpSession session, Admin admin) {
        admin.setAdminId((int) session.getAttribute("adminId"));
        return adminService.editAdminInfo(admin);
    }


    /**
     * 编辑admin密码
     *
     * @param session     根据session获取adminId
     * @param admin       密码存放位置
     * @param newPassword 新密码
     * @return ajax
     */
    @ResponseBody
    @RequestMapping("/alterAdminPassword")
    public AjaxResult alterAdminPassword(HttpSession session, Admin admin, String newPassword) {
        admin.setAdminId((int) session.getAttribute("adminId"));
        return adminService.alterAdminPassword(admin,newPassword);
    }


    /**
     * 重置密码
     */
    @ResponseBody
    @RequestMapping("/resetPwd")
    public AjaxResult resetPwd(String adminIds) {
        return adminService.resetPwd(adminIds);
    }


    /**
     * admin显示 admin_list
     *
     * @param currentPage 当前页
     * @param pageSize    每页数据个数
     * @return 返回ajax数据
     */
    @ResponseBody
    @RequestMapping("/findAllAdmins")
    public AjaxResult findAllAdmins(Integer currentPage, Integer pageSize) {
        return adminService.findAllAdmins(currentPage, pageSize);
    }


    /**
     * 高级搜索admin
     *
     * @param currentPage 当前页
     * @param pageSize    每页的数量
     * @return admin的ajax数据
     */
    @ResponseBody
    @RequestMapping("/advanceSearchAdmin")
    public AjaxResult advanceSearchAdmin(Integer moduleId, String rolename, Integer currentPage, Integer pageSize) {
        return adminService.advanceSearchAdmin(moduleId, rolename, currentPage, pageSize);
    }


    /**
     * 个人信息处回显
     */
    @ResponseBody
    @RequestMapping("/findAdminBySession")
    public AjaxResult findAdminBySession(HttpSession session) {
        return adminService.findAdminAndRolesByAdminId((int) session.getAttribute("adminId"));
    }


    /**
     * admin编辑回显 admin_modi
     *
     * @param adminId admin的id
     * @return 返回ajax数据
     */
    @ResponseBody
    @RequestMapping("/findAdminAndRolesByAdminId")
    public AjaxResult findAdminAndRolesByAdminId(int adminId) {
        return adminService.findAdminAndRolesByAdminId(adminId);
    }


}
