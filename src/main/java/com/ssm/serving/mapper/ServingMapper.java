package com.ssm.serving.mapper;

import com.ssm.serving.bean.Serving;

public interface ServingMapper {
    int insert(Serving record);

    int insertSelective(Serving record);
}