package com.ssm.module.mapper;

import com.ssm.module.bean.Module;

public interface ModuleMapper {
    int insert(Module record);

    int insertSelective(Module record);
}