package com.ssm.module.mapper;

import com.ssm.module.bean.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModuleMapper {

    List<Module> findAllModules();

    List<Module> findModulesByRoleId(int roleId);

    List<Module> findModulesByRoleIdAndModuleId(int roleId, int moduleId);

    List<Module> findModulesByModuleId(int moduleId);

    }