package com.ruoyi.order.controller;

import java.util.Arrays;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.order.domain.ProNoticeRequest;
import com.ruoyi.order.domain.RfOrderForProNotice;
import com.ruoyi.order.service.IRfProduceNoticeService;
import org.apache.commons.compress.utils.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.order.domain.RfOrder;
import com.ruoyi.order.service.IRfOrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 采购订单Controller
 * 
 * @author pg
 * @date 2024-01-13
 */
@Controller
@RequestMapping("/order/orderinfo")
public class RfOrderController extends BaseController
{
    private String prefix = "order/orderinfo";

    @Autowired
    private IRfOrderService rfOrderService;
    @Autowired
    private IRfProduceNoticeService iRfProduceNoticeService;

    @RequiresPermissions("order:orderinfo:view")
    @GetMapping()
    public String orderinfo()
    {
        return prefix + "/orderinfo";
    }

    /**
     * 查询采购订单列表
     */
    @RequiresPermissions("order:orderinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RfOrder rfOrder)
    {
        startPage();
        List<RfOrder> list = rfOrderService.selectRfOrderList(rfOrder);
        return getDataTable(list);
    }

    /**
     * 导出采购订单列表
     */
    @RequiresPermissions("order:orderinfo:export")
    @Log(title = "采购订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RfOrder rfOrder)
    {
        List<RfOrder> list = rfOrderService.selectRfOrderList(rfOrder);
        ExcelUtil<RfOrder> util = new ExcelUtil<RfOrder>(RfOrder.class);
        return util.exportExcel(list, "采购订单数据");
    }

    /**
     * 新增采购订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存采购订单
     */
    @RequiresPermissions("order:orderinfo:add")
    @Log(title = "采购订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RfOrder rfOrder)
    {
        return toAjax(rfOrderService.insertRfOrder(rfOrder));
    }

    /**
     * 修改采购订单
     */
    @RequiresPermissions("order:orderinfo:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        RfOrder rfOrder = rfOrderService.selectRfOrderById(id);
        mmap.put("rfOrder", rfOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存采购订单
     */
    @RequiresPermissions("order:orderinfo:edit")
    @Log(title = "采购订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RfOrder rfOrder)
    {
        return toAjax(rfOrderService.updateRfOrder(rfOrder));
    }

    /**
     * 删除采购订单
     */
    @RequiresPermissions("order:orderinfo:remove")
    @Log(title = "采购订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(rfOrderService.deleteRfOrderByIds(ids));
    }

    /**
     * 生成生产通知单
     */
    @GetMapping("/pro_notice_confirm")
    public String proNoticeConfirm(@RequestParam("ids") String ids)
    {

        return prefix + "/pro_notice_confirm"; // 返回视图的逻辑名称
    }


    /**
     * 输入生产通知单名称
     */
    @GetMapping("/input_pro_notice_name")
    public String inputProNoticeName()
    {

        return prefix + "/input_pro_notice_name"; // 返回视图的逻辑名称
    }

    /**
     * 生成生产通知单
     */
    @PostMapping("/pro_notice_confirm_list")
    @ResponseBody
    public TableDataInfo  proNoticeConfirmList(@RequestParam("ids") String ids)
    {
        startPage();  // 此方法配合前端完成自动分页
        List<RfOrder> list = Lists.newArrayList();
        if (StringUtils.isNotEmpty(ids)){
            String[] strings = ids.split(",");
            Arrays.stream(strings).forEach(s->{
                RfOrder rfOrder = rfOrderService.selectRfOrderById(Integer.parseInt(s));
                if (rfOrder!=null){
                    list.add(rfOrder);
                }

            });
        }
        return getDataTable(list);
    }

    /**
     * 生成生产通知单
     */
    @PostMapping("/generate_pro_notice")
    @ResponseBody
    public Integer  generateProNotice(@RequestBody ProNoticeRequest request) throws Exception {
        String name = request.getName();
        List<RfOrderForProNotice> rfOrderList  = request.getData();

        iRfProduceNoticeService.insertRfProduceNoticeAndDeatail(request);
        // 打印请求数据
        System.out.println("Name: " + name);
        System.out.println("Data: " + rfOrderList);
        return 0;
    }


}
