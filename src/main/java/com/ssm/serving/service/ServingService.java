package com.ssm.serving.service;

import com.ssm.serving.bean.Serving;
import com.ssm.util.AjaxResult;

/**
 * Created by dllo on 17/12/26.
 */
public interface ServingService {

    AjaxResult addServing(Serving serving);

    AjaxResult deleteServing(int servingId);

    AjaxResult editServing(Serving serving);

    AjaxResult alterServingStatus(Serving serving);

    AjaxResult advanceSearchService(Serving serving,Integer pageNum,Integer pageSize);

}
