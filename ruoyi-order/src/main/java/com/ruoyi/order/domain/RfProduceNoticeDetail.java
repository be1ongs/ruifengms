package com.ruoyi.order.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产通知单明细对象 rf_produce_notice_detail
 * 
 * @author pg
 * @date 2024-02-19
 */
public class RfProduceNoticeDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 订单id */
    private Long orderId;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNum;

    /** 主表id */
    private Long produceNoticeId;

    /** 客户id */
    private String purchaseId;

    /** 客户名称 */
    private String purchaseName;

    /** 客户代号 */
    @Excel(name = "客户代号")
    private String purchaseCode;

    /** 产品名称-中文 */
    @Excel(name = "产品名称-中文")
    private String productNameCn;

    /** 产品名称-英文 */
    @Excel(name = "产品名称-英文")
    private String productNameEn;

    /** 材质 */
    @Excel(name = "材质")
    private String material;

    /** 通知生产数量 */
    @Excel(name = "通知生产数量")
    private Integer noticeAmount;

    /** 用料规格 */
    @Excel(name = "用料规格")
    private String materialStandards;

    /** 毛坯重量 */
    @Excel(name = "毛坯重量")
    private String materialWeight;

    /** 产品净重 */
    @Excel(name = "产品净重")
    private String productWeight;

    /** 已生产 */
    @Excel(name = "已生产")
    private Integer finishAmount;

    /** 生产中 */
    @Excel(name = "生产中")
    private Integer produceAmount;

    /** 图纸 */
    private String productPic;

    /** 交货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date needTime;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 完成状态:0 已完成,1 未完成 2进行中 */
    @Excel(name = "完成状态:0 已完成,1 未完成 2进行中")
    private String status;

    /** 删除标记 0 正常 1 已删除 */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setOrderNum(String orderNum) 
    {
        this.orderNum = orderNum;
    }

    public String getOrderNum() 
    {
        return orderNum;
    }
    public void setProduceNoticeId(Long produceNoticeId) 
    {
        this.produceNoticeId = produceNoticeId;
    }

    public Long getProduceNoticeId() 
    {
        return produceNoticeId;
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
    public void setMaterial(String material) 
    {
        this.material = material;
    }

    public String getMaterial() 
    {
        return material;
    }
    public void setNoticeAmount(Integer noticeAmount) 
    {
        this.noticeAmount = noticeAmount;
    }

    public Integer getNoticeAmount() 
    {
        return noticeAmount;
    }
    public void setMaterialStandards(String materialStandards) 
    {
        this.materialStandards = materialStandards;
    }

    public String getMaterialStandards() 
    {
        return materialStandards;
    }
    public void setMaterialWeight(String materialWeight) 
    {
        this.materialWeight = materialWeight;
    }

    public String getMaterialWeight() 
    {
        return materialWeight;
    }
    public void setProductWeight(String productWeight) 
    {
        this.productWeight = productWeight;
    }

    public String getProductWeight() 
    {
        return productWeight;
    }
    public void setFinishAmount(Integer finishAmount) 
    {
        this.finishAmount = finishAmount;
    }

    public Integer getFinishAmount() 
    {
        return finishAmount;
    }
    public void setProduceAmount(Integer produceAmount) 
    {
        this.produceAmount = produceAmount;
    }

    public Integer getProduceAmount() 
    {
        return produceAmount;
    }
    public void setProductPic(String productPic) 
    {
        this.productPic = productPic;
    }

    public String getProductPic() 
    {
        return productPic;
    }
    public void setNeedTime(Date needTime) 
    {
        this.needTime = needTime;
    }

    public Date getNeedTime() 
    {
        return needTime;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
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
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("orderNum", getOrderNum())
            .append("produceNoticeId", getProduceNoticeId())
            .append("purchaseId", getPurchaseId())
            .append("purchaseName", getPurchaseName())
            .append("purchaseCode", getPurchaseCode())
            .append("productNameCn", getProductNameCn())
            .append("productNameEn", getProductNameEn())
            .append("material", getMaterial())
            .append("noticeAmount", getNoticeAmount())
            .append("materialStandards", getMaterialStandards())
            .append("materialWeight", getMaterialWeight())
            .append("productWeight", getProductWeight())
            .append("finishAmount", getFinishAmount())
            .append("produceAmount", getProduceAmount())
            .append("productPic", getProductPic())
            .append("needTime", getNeedTime())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
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
