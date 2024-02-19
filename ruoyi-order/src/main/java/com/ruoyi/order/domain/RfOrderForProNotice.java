package com.ruoyi.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 采购订单对象 rf_order
 *
 * @author pg
 * @date 2024-01-17
 */
public class RfOrderForProNotice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Integer id;

    /** 客户Id */
    @Excel(name = "客户Id")
    private String purchaseId;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String purchaseName;

    /** 客户代号 */
    @Excel(name = "客户代号")
    private String purchaseCode;

    /** 采购日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "采购日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purchaseDate;

    /** 材质 */
    @Excel(name = "材质")
    private String material;

    /** 数量 */
    @Excel(name = "数量")
    private Integer amount;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 单价 */
    @Excel(name = "单价")
    private Long price;

    /** 采购单号 */
    @Excel(name = "采购单号")
    private String orderNum;

    /** 产品名称-中文 */
    @Excel(name = "产品名称-中文")
    private String productNameCn;

    /** 产品名称-英文 */
    @Excel(name = "产品名称-英文")
    private String productNameEn;

    /** 交货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date needTime;

    /** 已交 */
    @Excel(name = "已交")
    private Integer paidNum;

    /** 未交 */
    @Excel(name = "未交")
    private Integer unpaidNum;

    /** 请购 */
    @Excel(name = "请购")
    private String orderFrom;

    /** 0：完成  1：未完成 */
    @Excel(name = "0：完成  1：未完成")
    private String orderStatus;

    /**  */
    @Excel(name = "")
    private String orderStatusDesc;

    /** 图纸地址 */
    @Excel(name = "图纸地址")
    private String productPic;

    /** 用料规格 */
    @Excel(name = "用料规格")
    private String materialStandards;

    /** 毛坯重量 */
    @Excel(name = "毛坯重量")
    private String materialWeight;

    /** 产品净重 */
    @Excel(name = "产品净重")
    private String productWeight;

    /** 发货日期1 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发货日期1", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sendTime1;

    /** 发货数量1 */
    @Excel(name = "发货数量1")
    private Integer sendAmount1;

    /** 发货日期2 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发货日期2", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sendTime2;

    /** 发货数量2 */
    @Excel(name = "发货数量2")
    private Integer sendAmount2;

    /** 发货日期3 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发货日期3", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sendTime3;

    /** 发货日期3 */
    @Excel(name = "发货日期3")
    private Integer sendAmount3;

    /**  */
    @Excel(name = "")
    private String sendInfo;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public String getMaterialStandards() {
        return materialStandards;
    }

    public void setMaterialStandards(String materialStandards) {
        this.materialStandards = materialStandards;
    }

    public String getMaterialWeight() {
        return materialWeight;
    }

    public void setMaterialWeight(String materialWeight) {
        this.materialWeight = materialWeight;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setPurchaseId(String purchaseId)
    {
        this.purchaseId = purchaseId;
    }

    public String getPurchaseId()
    {
        return purchaseId;
    }
    public void setPurchaseName(String purchaseName)
    {
        this.purchaseName = purchaseName;
    }

    public String getPurchaseName()
    {
        return purchaseName;
    }
    public void setPurchaseCode(String purchaseCode)
    {
        this.purchaseCode = purchaseCode;
    }

    public String getPurchaseCode()
    {
        return purchaseCode;
    }
    public void setPurchaseDate(Date purchaseDate)
    {
        this.purchaseDate = purchaseDate;
    }

    public Date getPurchaseDate()
    {
        return purchaseDate;
    }
    public void setMaterial(String material)
    {
        this.material = material;
    }

    public String getMaterial()
    {
        return material;
    }
    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }

    public Integer getAmount()
    {
        return amount;
    }
    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
    }
    public void setPrice(Long price)
    {
        this.price = price;
    }

    public Long getPrice()
    {
        return price;
    }
    public void setOrderNum(String orderNum)
    {
        this.orderNum = orderNum;
    }

    public String getOrderNum()
    {
        return orderNum;
    }
    public void setProductNameCn(String productNameCn)
    {
        this.productNameCn = productNameCn;
    }

    public String getProductNameCn()
    {
        return productNameCn;
    }
    public void setProductNameEn(String productNameEn)
    {
        this.productNameEn = productNameEn;
    }

    public String getProductNameEn()
    {
        return productNameEn;
    }
    public void setNeedTime(Date needTime)
    {
        this.needTime = needTime;
    }

    public Date getNeedTime()
    {
        return needTime;
    }
    public void setPaidNum(Integer paidNum)
    {
        this.paidNum = paidNum;
    }

    public Integer getPaidNum()
    {
        return paidNum;
    }
    public void setUnpaidNum(Integer unpaidNum)
    {
        this.unpaidNum = unpaidNum;
    }

    public Integer getUnpaidNum()
    {
        return unpaidNum;
    }
    public void setOrderFrom(String orderFrom)
    {
        this.orderFrom = orderFrom;
    }

    public String getOrderFrom()
    {
        return orderFrom;
    }
    public void setOrderStatus(String orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus()
    {
        return orderStatus;
    }
    public void setOrderStatusDesc(String orderStatusDesc)
    {
        this.orderStatusDesc = orderStatusDesc;
    }

    public String getOrderStatusDesc()
    {
        return orderStatusDesc;
    }
    public void setProductPic(String productPic)
    {
        this.productPic = productPic;
    }

    public String getProductPic()
    {
        return productPic;
    }
    public void setSendTime1(Date sendTime1)
    {
        this.sendTime1 = sendTime1;
    }

    public Date getSendTime1()
    {
        return sendTime1;
    }
    public void setSendAmount1(Integer sendAmount1)
    {
        this.sendAmount1 = sendAmount1;
    }

    public Integer getSendAmount1()
    {
        return sendAmount1;
    }
    public void setSendTime2(Date sendTime2)
    {
        this.sendTime2 = sendTime2;
    }

    public Date getSendTime2()
    {
        return sendTime2;
    }
    public void setSendAmount2(Integer sendAmount2)
    {
        this.sendAmount2 = sendAmount2;
    }

    public Integer getSendAmount2()
    {
        return sendAmount2;
    }
    public void setSendTime3(Date sendTime3)
    {
        this.sendTime3 = sendTime3;
    }

    public Date getSendTime3()
    {
        return sendTime3;
    }
    public void setSendAmount3(Integer sendAmount3)
    {
        this.sendAmount3 = sendAmount3;
    }

    public Integer getSendAmount3()
    {
        return sendAmount3;
    }
    public void setSendInfo(String sendInfo)
    {
        this.sendInfo = sendInfo;
    }

    public String getSendInfo()
    {
        return sendInfo;
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
                .append("id", getId())
                .append("purchaseId", getPurchaseId())
                .append("purchaseName", getPurchaseName())
                .append("purchaseCode", getPurchaseCode())
                .append("purchaseDate", getPurchaseDate())
                .append("material", getMaterial())
                .append("amount", getAmount())
                .append("unit", getUnit())
                .append("price", getPrice())
                .append("orderNum", getOrderNum())
                .append("productNameCn", getProductNameCn())
                .append("productNameEn", getProductNameEn())
                .append("needTime", getNeedTime())
                .append("paidNum", getPaidNum())
                .append("unpaidNum", getUnpaidNum())
                .append("orderFrom", getOrderFrom())
                .append("orderStatus", getOrderStatus())
                .append("orderStatusDesc", getOrderStatusDesc())
                .append("productPic", getProductPic())
                .append("sendTime1", getSendTime1())
                .append("sendAmount1", getSendAmount1())
                .append("sendTime2", getSendTime2())
                .append("sendAmount2", getSendAmount2())
                .append("sendTime3", getSendTime3())
                .append("sendAmount3", getSendAmount3())
                .append("sendInfo", getSendInfo())
                .append("remark", getRemark())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
