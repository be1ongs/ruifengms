package com.ruoyi.order.controller;

import java.util.Arrays;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.order.constants.BaseConstants;
import com.ruoyi.order.domain.RfOrder;
import com.ruoyi.order.domain.RfProduceNoticeDetail;
import com.ruoyi.order.service.IRfProduceNoticeDetailService;
import org.apache.commons.compress.utils.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.order.domain.RfProduceNotice;
import com.ruoyi.order.service.IRfProduceNoticeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 生产通知单Controller
 *
 * @author pg
 * @date 2024-02-19
 */
@Controller
@RequestMapping("/pro_notice/notice")
public class RfProduceNoticeController extends BaseController {
    private String prefix = "pro_notice/notice";

    @Autowired
    private IRfProduceNoticeService rfProduceNoticeService;

    @Autowired
    private IRfProduceNoticeDetailService rfProduceNoticeDetailService;

    @RequiresPermissions("pro_notice:notice:view")
    @GetMapping()
    public String notice() {
        return prefix + "/notice";
    }

    /**
     * 查询生产通知单列表
     */
    @RequiresPermissions("pro_notice:notice:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RfProduceNotice rfProduceNotice) {
        startPage();
        List<RfProduceNotice> list = rfProduceNoticeService.selectRfProduceNoticeList(rfProduceNotice);
        return getDataTable(list);
    }

    /**
     * 导出生产通知单列表
     */
    @RequiresPermissions("pro_notice:notice:export")
    @Log(title = "生产通知单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RfProduceNotice rfProduceNotice) {
        List<RfProduceNotice> list = rfProduceNoticeService.selectRfProduceNoticeList(rfProduceNotice);
        ExcelUtil<RfProduceNotice> util = new ExcelUtil<RfProduceNotice>(RfProduceNotice.class);
        return util.exportExcel(list, "生产通知单数据");
    }

    /**
     * 新增生产通知单
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 生产通知单详情
     */
    @GetMapping("/detail")
    public String detail(@RequestParam("id") String id) {
        return prefix + "/detail";
    }

    /**
     * 新增保存生产通知单
     */
    @RequiresPermissions("pro_notice:notice:add")
    @Log(title = "生产通知单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RfProduceNotice rfProduceNotice) {
        return toAjax(rfProduceNoticeService.insertRfProduceNotice(rfProduceNotice));
    }

    /**
     * 修改生产通知单
     */
    @RequiresPermissions("pro_notice:notice:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        RfProduceNotice rfProduceNotice = rfProduceNoticeService.selectRfProduceNoticeById(id);
        mmap.put("rfProduceNotice", rfProduceNotice);
        return prefix + "/edit";
    }

    /**
     * 修改保存生产通知单
     */
    @RequiresPermissions("pro_notice:notice:edit")
    @Log(title = "生产通知单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RfProduceNotice rfProduceNotice) {
        return toAjax(rfProduceNoticeService.updateRfProduceNotice(rfProduceNotice));
    }

    /**
     * 撤销生产通知单
     */
    @RequiresPermissions("pro_notice:notice:revoke")
    @Log(title = "生产通知单", businessType = BusinessType.REVOKE)
    @PostMapping("/revoke")
    @ResponseBody
    public AjaxResult revokeProduceNotice(RfProduceNotice rfProduceNotice) {

        return toAjax(rfProduceNoticeService.revokeRfProduceNoticeAndDetail(rfProduceNotice));
    }

    /**
     * 生成生产通知单EXCEL
     */
    @RequiresPermissions("pro_notice:notice:generate")
    @Log(title = "生产通知单", businessType = BusinessType.GENERATE_EXCEL)
    @PostMapping("/generateExcel")
    @ResponseBody
    public AjaxResult generateExcel(RfProduceNotice rfProduceNotice) {

        return toAjax(rfProduceNoticeService.generateRfProduceNoticeAndDetailExcel(rfProduceNotice));
    }

    /**
     * 删除生产通知单
     */
    @RequiresPermissions("pro_notice:notice:remove")
    @Log(title = "生产通知单", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(rfProduceNoticeService.deleteRfProduceNoticeByIds(ids));
    }



    /**
     * 生成生产通知单
     */
    @PostMapping("/detailList")
    @ResponseBody
    public TableDataInfo proNoticeDetailList(@RequestParam("id") String id) {
        startPage();  // 此方法配合前端完成自动分页
        List<RfProduceNoticeDetail> list = Lists.newArrayList();
        if (StringUtils.isNotEmpty(id)) {
            String[] strings = id.split(",");

            String noticeId = strings[0];
            RfProduceNoticeDetail rfProduceNoticeDetail = new RfProduceNoticeDetail();
            rfProduceNoticeDetail.setProduceNoticeId(Long.parseLong(noticeId));
            rfProduceNoticeDetail.setDelFlag(BaseConstants.DEL_FLAG_NORMAL);
            list = rfProduceNoticeDetailService.selectRfProduceNoticeDetailList(rfProduceNoticeDetail);

        }
        return getDataTable(list);
    }
}
