package com.ssm.role.mapper;

import com.ssm.role.bean.Role;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);
}