package com.ssm.module.service.impl;

import com.ssm.module.bean.Module;
import com.ssm.module.mapper.ModuleMapper;
import com.ssm.module.service.ModuleService;
import com.ssm.util.AjaxResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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


    @Override
    public AjaxResult findModulesByAdminId(int adminId) {
        List<Module> moduleList = moduleMapper.findModulesByAdminId(adminId);
        List<String> newList = new ArrayList<>();
        for (Module module : moduleList) {
            if (!newList.contains(module.getName())){
                newList.add(module.getName());
            }
        }
        return new AjaxResult(newList);
    }
}
