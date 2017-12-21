package com.ssm.cost.service.impl;

import com.ssm.cost.bean.Cost;
import com.ssm.cost.mapper.CostMapper;
import com.ssm.cost.service.CostService;
import com.ssm.util.AjaxResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by dllo on 17/12/20.
 */
@Service
public class CostServiceImpl implements CostService {

    @Resource
    private CostMapper costMapper;

    @Override
    public AjaxResult addCost(Cost cost) {
        costMapper.addCost(cost);
        return new AjaxResult(null);
    }

    @Override
    public AjaxResult deleteCost(int costId) {
        costMapper.deleteCost(costId);
        return new AjaxResult(null);
    }

    @Override
    public AjaxResult editCost(Cost cost) {
        cost.setStatus(costMapper.findCostByCostId(cost.getCostId()).get(0).getStatus());
        costMapper.editCost(cost);
        return new AjaxResult(null);
    }

    @Override
    public AjaxResult findAllCosts(String bcSort, String bdSort) {
        return new AjaxResult(costMapper.findAllCosts(bcSort, bdSort));
    }

    @Override
    public AjaxResult findCostByCostId(int costId) {
        return new AjaxResult(costMapper.findCostByCostId(costId));
    }

    @Override
    public AjaxResult alterStatusByCostId(int costId) {
        Cost cost = costMapper.findCostByCostId(costId).get(0);
        if (cost.getStatus().equals("s")){
            cost.setStatus("b");
        }else if (cost.getStatus().equals("b")){
            cost.setStatus("s");
            cost.setStartime(new Date());
        }
        costMapper.editCost(cost);
        return new AjaxResult(null);
    }
}
