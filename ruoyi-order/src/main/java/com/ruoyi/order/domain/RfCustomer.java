package com.ruoyi.order.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户信息对象 rf_customer
 * 
 * @author pg
 * @date 2024-01-13
 */
public class RfCustomer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long customerId;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customerName;

    /** 客户代号 */
    @Excel(name = "客户代号")
    private String customerCode;

    /** 送货地址 */
    @Excel(name = "送货地址")
    private String shippingAddress;

    /** 采购来源 */
    @Excel(name = "采购来源")
    private String purchaseFrom;

    /** 客户状态:0正常,1禁用 */
    private String status;

    /** 拒绝登录描述 */
    private String delFlag;

    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }
    public void setCustomerCode(String customerCode) 
    {
        this.customerCode = customerCode;
    }

    public String getCustomerCode() 
    {
        return customerCode;
    }
    public void setShippingAddress(String shippingAddress) 
    {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingAddress() 
    {
        return shippingAddress;
    }
    public void setPurchaseFrom(String purchaseFrom) 
    {
        this.purchaseFrom = purchaseFrom;
    }

    public String getPurchaseFrom() 
    {
        return purchaseFrom;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("customerId", getCustomerId())
            .append("customerName", getCustomerName())
            .append("customerCode", getCustomerCode())
            .append("shippingAddress", getShippingAddress())
            .append("purchaseFrom", getPurchaseFrom())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
