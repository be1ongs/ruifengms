package com.ruoyi.order.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.order.domain.RfProduceNoticeDetail;
import com.ruoyi.order.service.IRfProduceNoticeDetailService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 生产通知单明细Controller
 * 
 * @author pg
 * @date 2024-02-19
 */
@Controller
@RequestMapping("/pro_notice_detail/detail")
public class RfProduceNoticeDetailController extends BaseController
{
    private String prefix = "pro_notice_detail/detail";

    @Autowired
    private IRfProduceNoticeDetailService rfProduceNoticeDetailService;

    @RequiresPermissions("pro_notice_detail:detail:view")
    @GetMapping()
    public String detail()
    {
        return prefix + "/detail";
    }

    /**
     * 查询生产通知单明细列表
     */
    @RequiresPermissions("pro_notice_detail:detail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RfProduceNoticeDetail rfProduceNoticeDetail)
    {
        startPage();
        List<RfProduceNoticeDetail> list = rfProduceNoticeDetailService.selectRfProduceNoticeDetailList(rfProduceNoticeDetail);
        return getDataTable(list);
    }

    /**
     * 导出生产通知单明细列表
     */
    @RequiresPermissions("pro_notice_detail:detail:export")
    @Log(title = "生产通知单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RfProduceNoticeDetail rfProduceNoticeDetail)
    {
        List<RfProduceNoticeDetail> list = rfProduceNoticeDetailService.selectRfProduceNoticeDetailList(rfProduceNoticeDetail);
        ExcelUtil<RfProduceNoticeDetail> util = new ExcelUtil<RfProduceNoticeDetail>(RfProduceNoticeDetail.class);
        return util.exportExcel(list, "生产通知单明细数据");
    }

    /**
     * 新增生产通知单明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存生产通知单明细
     */
    @RequiresPermissions("pro_notice_detail:detail:add")
    @Log(title = "生产通知单明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RfProduceNoticeDetail rfProduceNoticeDetail)
    {
        return toAjax(rfProduceNoticeDetailService.insertRfProduceNoticeDetail(rfProduceNoticeDetail));
    }

    /**
     * 修改生产通知单明细
     */
    @RequiresPermissions("pro_notice_detail:detail:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        RfProduceNoticeDetail rfProduceNoticeDetail = rfProduceNoticeDetailService.selectRfProduceNoticeDetailById(id);
        mmap.put("rfProduceNoticeDetail", rfProduceNoticeDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存生产通知单明细
     */
    @RequiresPermissions("pro_notice_detail:detail:edit")
    @Log(title = "生产通知单明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RfProduceNoticeDetail rfProduceNoticeDetail)
    {
        return toAjax(rfProduceNoticeDetailService.updateRfProduceNoticeDetail(rfProduceNoticeDetail));
    }

    /**
     * 删除生产通知单明细
     */
    @RequiresPermissions("pro_notice_detail:detail:remove")
    @Log(title = "生产通知单明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(rfProduceNoticeDetailService.deleteRfProduceNoticeDetailByIds(ids));
    }

    /**
     * 录入生产信息
     */
    @GetMapping("/inputproduceinfo")
    public String inputproduceinfo(@RequestParam("id") String id) {
        return prefix + "/input_produce_info";
    }
}
