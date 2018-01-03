package com.ssm.serving.controller;

import com.ssm.serving.bean.Serving;
import com.ssm.serving.service.ServingService;
import com.ssm.util.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/12/20.
 */
@Controller
public class ServingController {

    @Resource
    private ServingService servingService;

    @RequestMapping("/service_list")
    public String service_list() {
        return "service/service_list";
    }


    @RequestMapping("/service_add")
    public String service_add() {
        return "service/service_add";
    }


    @RequestMapping("/service_modi")
    public String service_modi() {
        return "service/service_modi";
    }


    @RequestMapping("/service_detail")
    public String service_detail() {
        return "service/service_detail";
    }


    /**
     * 添加serving
     *
     * @param serving serving信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/addServing")
    public AjaxResult addServing(Serving serving) {
        return servingService.addServing(serving);
    }


    /**
     * 删除serving
     *
     * @param servingId 根据servingId删除
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteServing")
    public AjaxResult deleteServing(int servingId) {
        return servingService.deleteServing(servingId);
    }


    /**
     * 编辑serving
     *
     * @param serving serving信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/editServing")
    public AjaxResult editServing(Serving serving) {
        return servingService.editServing(serving);
    }


    /**
     * 变更serving的status
     *
     * @param serving
     * @return
     */
    @ResponseBody
    @RequestMapping("/alterServingStatus")
    public AjaxResult alterServingStatus(Serving serving) {
        return servingService.alterServingStatus(serving);
    }


    /**
     * 发现所有的service
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findAllService")
    public AjaxResult findAllService(Integer currentPage, Integer pageSize) {
        return servingService.advanceSearchService(null, currentPage, pageSize);
    }


    /**
     * 根据servingId获取serving
     *
     * @param serving 只有一个servingId
     * @return
     */
    @ResponseBody
    @RequestMapping("/findServiceByserviceId")
    public AjaxResult findServiceByserviceId(Serving serving) {
        return servingService.advanceSearchService(serving, null, null);
    }


    /**
     * 检索serving
     *
     * @param serving     搜索条件
     * @param currentPage 当前页
     * @param pageSize    每页数量
     * @return
     */
    @ResponseBody
    @RequestMapping("/advanceSearchService")
    public AjaxResult advanceSearchService(Serving serving, Integer currentPage, Integer pageSize) {
        return servingService.advanceSearchService(serving, currentPage, pageSize);
    }

}