package com.ruoyi.order.service;

import java.util.List;
import com.ruoyi.order.domain.RfProduceNoticeDetail;

/**
 * 生产通知单明细Service接口
 * 
 * @author pg
 * @date 2024-02-19
 */
public interface IRfProduceNoticeDetailService 
{
    /**
     * 查询生产通知单明细
     * 
     * @param id 生产通知单明细主键
     * @return 生产通知单明细
     */
    public RfProduceNoticeDetail selectRfProduceNoticeDetailById(Long id);

    /**
     * 查询生产通知单明细列表
     * 
     * @param rfProduceNoticeDetail 生产通知单明细
     * @return 生产通知单明细集合
     */
    public List<RfProduceNoticeDetail> selectRfProduceNoticeDetailList(RfProduceNoticeDetail rfProduceNoticeDetail);

    /**
     * 新增生产通知单明细
     * 
     * @param rfProduceNoticeDetail 生产通知单明细
     * @return 结果
     */
    public int insertRfProduceNoticeDetail(RfProduceNoticeDetail rfProduceNoticeDetail);

    /**
     * 修改生产通知单明细
     * 
     * @param rfProduceNoticeDetail 生产通知单明细
     * @return 结果
     */
    public int updateRfProduceNoticeDetail(RfProduceNoticeDetail rfProduceNoticeDetail);

    /**
     * 批量删除生产通知单明细
     * 
     * @param ids 需要删除的生产通知单明细主键集合
     * @return 结果
     */
    public int deleteRfProduceNoticeDetailByIds(String ids);

    /**
     * 删除生产通知单明细信息
     * 
     * @param id 生产通知单明细主键
     * @return 结果
     */
    public int deleteRfProduceNoticeDetailById(Long id);
}
