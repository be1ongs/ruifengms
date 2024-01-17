package com.ruoyi.order.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
}
