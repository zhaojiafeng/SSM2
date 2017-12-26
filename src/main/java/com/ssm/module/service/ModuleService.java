package com.ssm.module.service;

import com.ssm.util.AjaxResult;

/**
 * Created by dllo on 17/12/20.
 */
public interface ModuleService {

    AjaxResult findAllModules();

    AjaxResult findModulesByAdminId(int adminId);

}
