package com.ssm.role.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.module.bean.Module;
import com.ssm.module.mapper.ModuleMapper;
import com.ssm.role.bean.Role;
import com.ssm.role.mapper.RoleMapper;
import com.ssm.role.service.RoleService;
import com.ssm.util.AjaxResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/12/20.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private ModuleMapper moduleMapper;


    @Override
    public AjaxResult addRoleAndModules(Role role, String moduleStr) {
        roleMapper.addRole(role);
        addRole_Module(role, moduleStr);
        return new AjaxResult(null);
    }


    @Override
    public AjaxResult editRoleAndModules(Role role, String moduleStr, String unchmodule) {
        roleMapper.editRoleInfo(role);
        int roleId = role.getRoleId();
        if (!StringUtils.isEmpty(moduleStr)){
            String[] moduleIdList = moduleStr.split(",");
            for (int i = 0; i < moduleIdList.length; i++) {
                int moduleId = Integer.parseInt(moduleIdList[i]);
                List<Module> modules = moduleMapper.findModulesByRoleIdAndModuleId(roleId, moduleId);
                if (modules.size() == 0) {
                    roleMapper.addRole_module(roleId, moduleId);
                }
            }
        }
        if (!StringUtils.isEmpty(unchmodule)){
            String[] uncheckedList = unchmodule.split(",");
            for (int i = 0; i < uncheckedList.length; i++) {
                int moduleId = Integer.parseInt(uncheckedList[i]);
                List<Module> modules = moduleMapper.findModulesByRoleIdAndModuleId(roleId, moduleId);
                if (modules.size() != 0) {
                    roleMapper.deleteRole_module(roleId, moduleId);
                }
            }
        }
        return new AjaxResult(null);
    }


    @Override
    public AjaxResult deleteRole(int roleId) {
        roleMapper.deleteRole(roleId);
        return new AjaxResult(null);
    }


    @Override
    public AjaxResult findAllRoles(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roles = roleMapper.findAllRoles();
        addModulesToRoles(roles);
        PageInfo<Role> rolePageInfo = new PageInfo<Role>(roles);
        return new AjaxResult(rolePageInfo);
    }


    @Override
    public AjaxResult findRoleAndModulesByRoleId(Role role) {
        List<Role> roleList = roleMapper.findRoleBySelected(role);
        List<Module> moduleList = moduleMapper.findModulesByRoleId(role.getRoleId());
        roleList.get(0).setModuleList(moduleList);
        return new AjaxResult(roleList);
    }


    private void addModulesToRoles(List<Role> roles) {
        for (Role role : roles) {
            String modules = "";
            List<Module> moduleList = moduleMapper.findModulesByRoleId(role.getRoleId());
            if (moduleList.size() > 0) {
                modules += moduleList.get(0).getName();
                if (moduleList.size() > 1) {
                    for (int i = 1; i < moduleList.size(); i++) {
                        modules += "," + moduleList.get(i).getName();
                    }
                }
            }
            role.setModules(modules);
        }
    }


    private void addRole_Module(Role role, String moduleStr) {
        int roleId = roleMapper.findRoleBySelected(role).get(0).getRoleId();
        String[] moduleIdList = moduleStr.split(",");
        for (int i = 0; i < moduleIdList.length; i++) {
            int moduleId = Integer.parseInt(moduleIdList[i]);
            roleMapper.addRole_module(roleId, moduleId);
        }
    }
}
