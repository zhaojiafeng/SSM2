package com.ssm.serving.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dllo on 17/12/20.
 */
@Controller
public class ServingController {



    @RequestMapping("/service_list")
    public String service_list() {
        return "service/service_list";
    }


    @RequestMapping("/service_add")
    public String service_add() {
        return "service/service_add";
    }


    @RequestMapping("/service_modi")
    public String service_modi() {
        return "service/service_modi";
    }


    @RequestMapping("/service_detail")
    public String service_detail() {
        return "service/service_detail";
    }


}
