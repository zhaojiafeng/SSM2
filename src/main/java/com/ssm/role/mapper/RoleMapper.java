package com.ssm.role.mapper;

import com.ssm.role.bean.Role;

import java.util.List;

public interface RoleMapper {

    int insert(Role record);

    int addRole(Role role);

    int deleteRole(int roleId);

    int addRole_module(int roleId, int moduleId);

    int deleteRole_module(int roleId, int moduleId);

    int editRoleInfo(Role role);

    List<Role> findAllRoles();

    List<Role> findRoleBySelected(Role role);
}