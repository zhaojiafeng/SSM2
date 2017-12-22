package com.ssm.role.service;

import com.ssm.role.bean.Role;
import com.ssm.util.AjaxResult;

/**
 * Created by dllo on 17/12/20.
 */
public interface RoleService {

    AjaxResult addRoleAndModules(Role role, String moduleStr);

    AjaxResult editRoleAndModules(Role role, String moduleStr, String unchmodule);

    AjaxResult deleteRole(int roleId);

    AjaxResult findAllRoles();

    AjaxResult findAllRolesByPage(Integer pageNum, Integer pageSize);

    AjaxResult findRoleAndModulesByRoleId(Role role);

}
