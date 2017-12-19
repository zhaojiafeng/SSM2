package com.ssm.cost.mapper;

import com.ssm.cost.bean.Cost;

public interface CostMapper {
    int insert(Cost record);

    int insertSelective(Cost record);
}