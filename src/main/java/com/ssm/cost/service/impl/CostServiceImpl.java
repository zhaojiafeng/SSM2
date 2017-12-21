package com.ssm.cost.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.cost.bean.Cost;
import com.ssm.cost.bean.CostError;
import com.ssm.cost.mapper.CostMapper;
import com.ssm.cost.service.CostService;
import com.ssm.util.AjaxResult;
import com.ssm.util.EasyMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/12/20.
 */
@Service
public class CostServiceImpl implements CostService {

    @Resource
    private CostMapper costMapper;


    @Override
    public AjaxResult addCost(Cost cost) {
        CostError error = returnException(cost);
        if (StringUtils.isEmpty(error.getBaseDurError())&&
            StringUtils.isEmpty(error.getBaseCostError())&&
            StringUtils.isEmpty(error.getUnitCostError())
        ) {
            costMapper.addCost(cost);
            return new AjaxResult(null);
        }
        return new AjaxResult("-1", error);
    }


    @Override
    public AjaxResult deleteCost(int costId) {
        costMapper.deleteCost(costId);
        return new AjaxResult(null);
    }


    @Override
    public AjaxResult editCost(Cost cost) {
        CostError error = returnException(cost);
        if (StringUtils.isEmpty(error.getBaseDurError())&&
            StringUtils.isEmpty(error.getBaseCostError())&&
            StringUtils.isEmpty(error.getUnitCostError())
        ){
            cost.setStatus(costMapper.findCostByCostId(cost.getCostId()).get(0).getStatus());
            costMapper.editCost(cost);
            return new AjaxResult(null);
        }
        return new AjaxResult("-1",error);
    }


    @Override
    public AjaxResult findAllCosts(Integer pageNum, Integer pageSize, String bcSort, String bdSort) {
        PageHelper.startPage(pageNum, pageSize);
        List<Cost> costs = costMapper.findAllCosts(bcSort, bdSort);
        PageInfo<Cost> costPageInfo = new PageInfo<>(costs);
        return new AjaxResult(costPageInfo);
    }


    @Override
    public AjaxResult findCostByCostId(int costId) {
        return new AjaxResult(costMapper.findCostByCostId(costId));
    }


    @Override
    public AjaxResult alterStatusByCostId(int costId) {
        Cost cost = costMapper.findCostByCostId(costId).get(0);
        if (cost.getStatus().equals("s")) {
            cost.setStatus("b");
        } else if (cost.getStatus().equals("b")) {
            cost.setStatus("s");
            cost.setStartime(new Date());
        }
        costMapper.editCost(cost);
        return new AjaxResult(null);
    }


    private CostError returnException(Cost cost) {
        CostError costError = new CostError();
        if (cost.getCostType().equals("m")) {
            Integer basecost = cost.getBaseCost();
            if (basecost == null || basecost < 0 || basecost > 100000) {
                costError.setBaseCostError("0-99999.99之间的数值");
                return costError;
            }
        } else if (cost.getCostType().equals("p")) {
            Integer baseDur = cost.getBaseDuration();
            if (baseDur == null || baseDur < 1 || baseDur > 600 || !EasyMethod.isInteger(Integer.toString(baseDur))) {
                costError.setBaseDurError("1-600之间的整数");
            }
            Integer basecost = cost.getBaseCost();
            if (basecost == null || basecost < 0 || basecost > 100000) {
                costError.setBaseCostError("0-99999.99之间的数值");
            }
            Integer unitCost = cost.getUnitCost();
            if (unitCost == null || unitCost < 0 || unitCost > 100000) {
                costError.setUnitCostError("0-99999.99之间的数值");
            }
            if (null != costError) {
                return costError;
            }
        } else if (cost.getCostType().equals("t")) {
            Integer unitCost = cost.getUnitCost();
            if (unitCost == null || unitCost < 0 || unitCost > 100000) {
                costError.setUnitCostError("0-99999.99之间的数值");
                return costError;
            }
        }
        return null;
    }
}
