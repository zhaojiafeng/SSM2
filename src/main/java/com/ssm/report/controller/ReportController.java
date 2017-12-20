package com.ssm.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dllo on 17/12/20.
 */
@Controller
public class ReportController {

    @RequestMapping("/report_list")
    public String report_list() {
        return "report/report_list";
    }


}
