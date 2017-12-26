package com.ssm.module.mapper;

import com.ssm.module.bean.Module;

import java.util.List;

public interface ModuleMapper {

    List<Module> findAllModules();

    List<Module> findModulesByAdminId(int adminId);

    List<Module> findModulesByRoleId(int roleId);

    List<Module> findModulesByModuleId(int moduleId);

    List<Module> findModulesByRoleIdAndModuleId(int roleId, int moduleId);

}