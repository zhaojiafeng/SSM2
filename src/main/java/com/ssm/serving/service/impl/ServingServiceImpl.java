package com.ssm.serving.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.account.bean.Account;
import com.ssm.account.mapper.AccountMapper;
import com.ssm.cost.mapper.CostMapper;
import com.ssm.serving.bean.Serving;
import com.ssm.serving.bean.ServingError;
import com.ssm.serving.mapper.ServingMapper;
import com.ssm.serving.service.ServingService;
import com.ssm.util.AjaxResult;
import com.ssm.util.EasyMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/12/26.
 */
@Service
public class ServingServiceImpl implements ServingService {

    @Resource
    private ServingMapper servingMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private CostMapper costMapper;


    @Override
    public AjaxResult addServing(Serving serving) {
        servingMapper.addServing(serving);
        return new AjaxResult(null);
    }


    @Override
    public AjaxResult deleteServing(int servingId) {
        servingMapper.deleteServing(servingId);
        return new AjaxResult(null);
    }


    @Override
    public AjaxResult editServing(Serving serving) {
        servingMapper.editServing(serving);
        return new AjaxResult(null);
    }


    @Override
    public AjaxResult alterServingStatus(Serving serving) {
        String status = servingMapper.advanceSearchService(serving).get(0).getStatus();
        if (status.equals("b")){
            serving.setStatus("s");
        } else {
            serving.setStatus("b");
        }
        servingMapper.editServing(serving);
        return new AjaxResult(null);
    }


    @Override
    public AjaxResult advanceSearchService(Serving serving, Integer pageNum, Integer pageSize) {
        if (null == pageNum && null == pageSize) {
            return new AjaxResult(servingMapper.advanceSearchService(serving));
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Serving> servingList = servingMapper.advanceSearchService(serving);
        for (Serving serv : servingList) {
            Account account = new Account();
            account.setAccountId(serv.getAccountId());
            serv.setAccount(accountMapper.advanceSearchAccount(account).get(0));
            serv.setCost(costMapper.findCostByCostId(serv.getCostId()).get(0));
        }
        PageInfo<Serving> servingPageInfo = new PageInfo<>(servingList);
        return new AjaxResult(servingPageInfo);
    }


    private ServingError ValidateServiceSrror(Serving serving){
        ServingError servingError = new ServingError();
        servingError.setNoError(true);
        String unixHost = serving.getUnixHost();
        String osUsername = serving.getOsUsername();

        if (StringUtils.isEmpty(unixHost)){
            servingError.setUnixHostError("15 长度，符合IP的地址规范");
            servingError.setNoError(false);
        } else {

        }

        if (StringUtils.isEmpty(osUsername)){
            servingError.setOsUsernameError("8长度以内的字母、数字和下划线的组合");
            servingError.setNoError(false);
        } else {
            if (osUsername.length()>8 || EasyMethod.ValidateString(osUsername)){
                servingError.setOsUsernameError("8长度以内的字母、数字和下划线的组合");
                servingError.setNoError(false);
            }
        }


        return servingError;
    }

}
