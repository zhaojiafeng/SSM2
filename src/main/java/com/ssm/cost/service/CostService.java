package com.ssm.cost.service;

import com.ssm.cost.bean.Cost;
import com.ssm.util.AjaxResult;

/**
 * Created by dllo on 17/12/20.
 */
public interface CostService {

    AjaxResult addCost(Cost cost);

    AjaxResult deleteCost(int costId);

    AjaxResult editCost(Cost cost);

    AjaxResult findAllCosts(String bcSort,String bdSort);

    AjaxResult findCostByCostId(int costId);

    AjaxResult alterStatusByCostId(int costId);
}
