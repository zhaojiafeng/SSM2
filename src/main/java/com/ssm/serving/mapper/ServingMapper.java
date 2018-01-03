package com.ssm.serving.mapper;

import com.ssm.serving.bean.Serving;

import java.util.List;

public interface ServingMapper {

    int addServing(Serving serving);

    int deleteServing(int servingId);

    int editServing(Serving serving);

    List<Serving> advanceSearchService(Serving serving);

}