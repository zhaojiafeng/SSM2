package com.ssm.cost.mapper;

import com.ssm.cost.bean.Cost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CostMapper {

    int insert(Cost record);

    int addCost(Cost cost);

    int deleteCost(int costId);

    int editCost(Cost cost);

    int alterStatusByCostId(Cost cost);

    List<Cost> findAllCosts(@Param("bcsort") String bcsort, @Param("bdsort") String bdsort);

    List<Cost> findCostByCostId(int costId);

}