package com.ssm.role.controller;

import com.ssm.role.bean.Role;
import com.ssm.role.service.RoleService;
import com.ssm.util.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/12/20.
 */
@Controller
public class RoleController {

    @Resource
    private RoleService roleService;


    @RequestMapping("/role_add")
    public String role_add() {
        return "role/role_add";
    }

    @RequestMapping("/role_list")
    public String role_list() {
        return "role/role_list";
    }

    @RequestMapping("/role_modi")
    public String role_modi() {
        return "role/role_modi";
    }


    @ResponseBody
    @RequestMapping("/addRoleAndModules")
    public AjaxResult addRoleAndModules(Role role, String moduleStr) {
        return roleService.addRoleAndModules(role, moduleStr);
    }


    @ResponseBody
    @RequestMapping("/deleteRole")
    public AjaxResult deleteRole(int roleId) {
        return roleService.deleteRole(roleId);
    }


    @ResponseBody
    @RequestMapping("/editRoleAndModules")
    public AjaxResult editRoleAndModules(Role role, String moduleStr, String unchmodule) {
        return roleService.editRoleAndModules(role, moduleStr, unchmodule);
    }


    @ResponseBody
    @RequestMapping("/findAllRoles")
    public AjaxResult findAllRoles(Integer currentPage, Integer pageSize) {
        return roleService.findAllRoles(currentPage, pageSize);
    }


    @ResponseBody
    @RequestMapping("/findRoleAndModulesByRoleId")
    public AjaxResult findRoleAndModulesByRoleId(Role role) {
        return roleService.findRoleAndModulesByRoleId(role);
    }


}
