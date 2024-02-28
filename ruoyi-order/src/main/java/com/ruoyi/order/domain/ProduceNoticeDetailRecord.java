package com.ruoyi.order.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产通知单明细记录对象 produce_notice_detail_record
 * 
 * @author pg
 * @date 2024-02-28
 */
public class ProduceNoticeDetailRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 明细表id */
    @Excel(name = "明细表id")
    private Long produceNoticeDetailId;

    /** 生产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date produceTime;

    /** 生产员工id */
    @Excel(name = "生产员工id")
    private String produceEmployeeId;

    /** 生产员工 */
    @Excel(name = "生产员工")
    private String produceEmployee;

    /** 生产成品数量 */
    @Excel(name = "生产成品数量")
    private Integer produceNum;

    /** 半成品数量 */
    @Excel(name = "半成品数量")
    private Integer produceHalfNum;

    /** 废品数量 */
    @Excel(name = "废品数量")
    private Integer produceWasteNum;

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
    public void setProduceNoticeDetailId(Long produceNoticeDetailId) 
    {
        this.produceNoticeDetailId = produceNoticeDetailId;
    }

    public Long getProduceNoticeDetailId() 
    {
        return produceNoticeDetailId;
    }
    public void setProduceTime(Date produceTime) 
    {
        this.produceTime = produceTime;
    }

    public Date getProduceTime() 
    {
        return produceTime;
    }
    public void setProduceEmployeeId(String produceEmployeeId) 
    {
        this.produceEmployeeId = produceEmployeeId;
    }

    public String getProduceEmployeeId() 
    {
        return produceEmployeeId;
    }
    public void setProduceEmployee(String produceEmployee) 
    {
        this.produceEmployee = produceEmployee;
    }

    public String getProduceEmployee() 
    {
        return produceEmployee;
    }
    public void setProduceNum(Integer produceNum) 
    {
        this.produceNum = produceNum;
    }

    public Integer getProduceNum() 
    {
        return produceNum;
    }
    public void setProduceHalfNum(Integer produceHalfNum) 
    {
        this.produceHalfNum = produceHalfNum;
    }

    public Integer getProduceHalfNum() 
    {
        return produceHalfNum;
    }
    public void setProduceWasteNum(Integer produceWasteNum) 
    {
        this.produceWasteNum = produceWasteNum;
    }

    public Integer getProduceWasteNum() 
    {
        return produceWasteNum;
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
            .append("produceNoticeDetailId", getProduceNoticeDetailId())
            .append("produceTime", getProduceTime())
            .append("produceEmployeeId", getProduceEmployeeId())
            .append("produceEmployee", getProduceEmployee())
            .append("produceNum", getProduceNum())
            .append("produceHalfNum", getProduceHalfNum())
            .append("produceWasteNum", getProduceWasteNum())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
