package com.ssm.cost.controller;

import com.ssm.cost.bean.Cost;
import com.ssm.cost.service.CostService;
import com.ssm.util.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by dllo on 17/12/20.
 */
@Controller
public class CostController {

    @Resource
    private CostService costService;

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


    @ResponseBody
    @RequestMapping("/addCost")
    public AjaxResult addCost(Cost cost) {
        cost.setStatus("b");
        cost.setCreatime(new Date());
        return costService.addCost(cost);
    }


    @ResponseBody
    @RequestMapping("/deleteCost")
    public AjaxResult deleteCost(int costId) {
        return costService.deleteCost(costId);
    }


    @ResponseBody
    @RequestMapping("/editCost")
    public AjaxResult editCost(Cost cost) {
        return costService.editCost(cost);
    }


    @ResponseBody
    @RequestMapping("/findAllCost")
    public AjaxResult findAllCost(Integer currentPage,Integer pageSize,String bcSort,String bdSort) {
        return costService.findAllCosts(currentPage,pageSize,bcSort,bdSort);
    }


    @ResponseBody
    @RequestMapping("/findCostByCostId")
    public AjaxResult findCostByCostId(int costId) {
        return costService.findCostByCostId(costId);
    }

    @ResponseBody
    @RequestMapping("/alterStatusByCostId")
    public AjaxResult alterStatusByCostId(int costId) {
        return costService.alterStatusByCostId(costId);
    }

}
