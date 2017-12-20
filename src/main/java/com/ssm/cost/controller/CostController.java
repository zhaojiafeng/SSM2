package com.ssm.cost.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dllo on 17/12/20.
 */
@Controller
public class CostController {



    @RequestMapping("/fee_add")
    public String fee_add() {
        return "fee/fee_add";
    }

    @RequestMapping("/fee_list")
    public String fee_list() {
        return "fee/fee_list";
    }

    @RequestMapping("/fee_modi")
    public String fee_modi() {
        return "fee/fee_modi";
    }

    @RequestMapping("/fee_detail")
    public String fee_detail() {
        return "fee/fee_detail";
    }


}
