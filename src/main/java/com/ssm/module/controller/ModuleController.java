package com.ssm.module.controller;

import com.ssm.module.service.ModuleService;
import com.ssm.util.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/12/20.
 */
@Controller
public class ModuleController {

    @Resource
    private ModuleService moduleService;

    @ResponseBody
    @RequestMapping("/findAllModules")
    public AjaxResult findAllModules() {
        return moduleService.findAllModules();
    }

}
