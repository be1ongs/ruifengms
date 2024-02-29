package com.ruoyi.order.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.order.domain.ProduceNoticeDetailRecord;
import com.ruoyi.order.service.IProduceNoticeDetailRecordService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 生产通知单明细记录Controller
 * 
 * @author pg
 * @date 2024-02-28
 */
@Controller
@RequestMapping("/pro_notice_detail_record/record")
public class ProduceNoticeDetailRecordController extends BaseController
{
    private String prefix = "pro_notice_detail_record/record";

    @Autowired
    private IProduceNoticeDetailRecordService produceNoticeDetailRecordService;

    @RequiresPermissions("pro_notice_detail_record:record:view")
    @GetMapping()
    public String record()
    {
        return prefix + "/record";
    }

    /**
     * 查询生产通知单明细记录列表
     */
    @RequiresPermissions("pro_notice_detail_record:record:list")
    @PostMapping("/list/{produceNoticeDetailId}")
    @ResponseBody
    public TableDataInfo list(@PathVariable("produceNoticeDetailId") Long produceNoticeDetailId)
    {
        startPage();
        ProduceNoticeDetailRecord produceNoticeDetailRecord = new ProduceNoticeDetailRecord();
        produceNoticeDetailRecord.setProduceNoticeDetailId(produceNoticeDetailId);
        List<ProduceNoticeDetailRecord> list = produceNoticeDetailRecordService.selectProduceNoticeDetailRecordList(produceNoticeDetailRecord);
        return getDataTable(list);
    }

    /**
     * 导出生产通知单明细记录列表
     */
    @RequiresPermissions("pro_notice_detail_record:record:export")
    @Log(title = "生产通知单明细记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProduceNoticeDetailRecord produceNoticeDetailRecord)
    {
        List<ProduceNoticeDetailRecord> list = produceNoticeDetailRecordService.selectProduceNoticeDetailRecordList(produceNoticeDetailRecord);
        ExcelUtil<ProduceNoticeDetailRecord> util = new ExcelUtil<ProduceNoticeDetailRecord>(ProduceNoticeDetailRecord.class);
        return util.exportExcel(list, "生产通知单明细记录数据");
    }

    /**
     * 新增生产通知单明细记录
     */
    @GetMapping("/add")
    public String add(@RequestParam("produceNoticeDetailId") Long produceNoticeDetailId)
    {
        return prefix + "/add";
    }

    /**
     * 新增保存生产通知单明细记录
     */
    @RequiresPermissions("pro_notice_detail_record:record:add")
    @Log(title = "生产通知单明细记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProduceNoticeDetailRecord produceNoticeDetailRecord)
    {
        return toAjax(produceNoticeDetailRecordService.insertProduceNoticeDetailRecord(produceNoticeDetailRecord));
    }

    /**
     * 修改生产通知单明细记录
     */
    @RequiresPermissions("pro_notice_detail_record:record:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceNoticeDetailRecord produceNoticeDetailRecord = produceNoticeDetailRecordService.selectProduceNoticeDetailRecordById(id);
        mmap.put("produceNoticeDetailRecord", produceNoticeDetailRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存生产通知单明细记录
     */
    @RequiresPermissions("pro_notice_detail_record:record:edit")
    @Log(title = "生产通知单明细记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProduceNoticeDetailRecord produceNoticeDetailRecord)
    {
        return toAjax(produceNoticeDetailRecordService.updateProduceNoticeDetailRecord(produceNoticeDetailRecord));
    }

    /**
     * 删除生产通知单明细记录
     */
    @RequiresPermissions("pro_notice_detail_record:record:remove")
    @Log(title = "生产通知单明细记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(produceNoticeDetailRecordService.deleteProduceNoticeDetailRecordByIds(ids));
    }
}
