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
import com.ruoyi.order.domain.RfCustomer;
import com.ruoyi.order.service.IRfCustomerService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户信息Controller
 * 
 * @author ruoyi
 * @date 2024-01-13
 */
@Controller
@RequestMapping("/order/customer")
public class RfCustomerController extends BaseController
{
    private String prefix = "order/customer";

    @Autowired
    private IRfCustomerService rfCustomerService;

    @RequiresPermissions("order:customer:view")
    @GetMapping()
    public String customer()
    {
        return prefix + "/customer";
    }

    /**
     * 查询客户信息列表
     */
    @RequiresPermissions("order:customer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RfCustomer rfCustomer)
    {
        startPage();
        List<RfCustomer> list = rfCustomerService.selectRfCustomerList(rfCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户信息列表
     */
    @RequiresPermissions("order:customer:export")
    @Log(title = "客户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RfCustomer rfCustomer)
    {
        List<RfCustomer> list = rfCustomerService.selectRfCustomerList(rfCustomer);
        ExcelUtil<RfCustomer> util = new ExcelUtil<RfCustomer>(RfCustomer.class);
        return util.exportExcel(list, "客户信息数据");
    }

    /**
     * 新增客户信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存客户信息
     */
    @RequiresPermissions("order:customer:add")
    @Log(title = "客户信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RfCustomer rfCustomer)
    {
        return toAjax(rfCustomerService.insertRfCustomer(rfCustomer));
    }

    /**
     * 修改客户信息
     */
    @RequiresPermissions("order:customer:edit")
    @GetMapping("/edit/{customerId}")
    public String edit(@PathVariable("customerId") Long customerId, ModelMap mmap)
    {
        RfCustomer rfCustomer = rfCustomerService.selectRfCustomerByCustomerId(customerId);
        mmap.put("rfCustomer", rfCustomer);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户信息
     */
    @RequiresPermissions("order:customer:edit")
    @Log(title = "客户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RfCustomer rfCustomer)
    {
        return toAjax(rfCustomerService.updateRfCustomer(rfCustomer));
    }

    /**
     * 删除客户信息
     */
    @RequiresPermissions("order:customer:remove")
    @Log(title = "客户信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(rfCustomerService.deleteRfCustomerByCustomerIds(ids));
    }
}
