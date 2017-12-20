package com.ssm.bill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dllo on 17/12/20.
 */
@Controller
public class BillController {




    @RequestMapping("/bill_list")
    public String bill_list() {
        return "bill/bill_list";
    }


    @RequestMapping("/bill_item")
    public String bill_item() {
        return "bill/bill_item";
    }


    @RequestMapping("/bill_service_detail")
    public String bill_service_detail() {
        return "bill/bill_service_detail";
    }


}
