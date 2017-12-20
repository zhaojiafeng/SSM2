package com.ssm.module.mapper;

import com.ssm.module.bean.Module;

import java.util.List;

public interface ModuleMapper {

    int insert(Module record);

    int insertSelective(Module record);

    List<Module> findAllModules();

    List<Module> findModulesByRoleId(int roleId);

    List<Module> findModulesByRoleIdAndModuleId(int roleId, int moduleId);

    List<Module> findModulesByModuleId(int moduleId);
}