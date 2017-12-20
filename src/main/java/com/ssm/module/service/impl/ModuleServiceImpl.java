package com.ssm.module.service.impl;

import com.ssm.module.mapper.ModuleMapper;
import com.ssm.module.service.ModuleService;
import com.ssm.util.AjaxResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/12/20.
 */
@Service
public class ModuleServiceImpl implements ModuleService {

    @Resource
    private ModuleMapper moduleMapper;

    @Override
    public AjaxResult findAllModules() {
        return new AjaxResult(moduleMapper.findAllModules());
    }

}
