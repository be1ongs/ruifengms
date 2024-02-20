package com.ruoyi.order.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产通知单对象 rf_produce_notice
 *
 * @author pg
 * @date 2024-02-20
 */
public class RfProduceNotice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 生产通知单编码 */
    @Excel(name = "生产通知单编码")
    private String produceNoticeCode;

    /** 生产通知单名称 */
    @Excel(name = "生产通知单名称")
    private String produceNoticeName;

    /** 关联订单数量 */
    @Excel(name = "关联订单数量")
    private Integer produceNum;

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
    public void setProduceNoticeCode(String produceNoticeCode)
    {
        this.produceNoticeCode = produceNoticeCode;
    }

    public String getProduceNoticeCode()
    {
        return produceNoticeCode;
    }
    public void setProduceNoticeName(String produceNoticeName)
    {
        this.produceNoticeName = produceNoticeName;
    }

    public String getProduceNoticeName()
    {
        return produceNoticeName;
    }
    public void setProduceNum(Integer produceNum)
    {
        this.produceNum = produceNum;
    }

    public Integer getProduceNum()
    {
        return produceNum;
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
                .append("produceNoticeCode", getProduceNoticeCode())
                .append("produceNoticeName", getProduceNoticeName())
                .append("produceNum", getProduceNum())
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
