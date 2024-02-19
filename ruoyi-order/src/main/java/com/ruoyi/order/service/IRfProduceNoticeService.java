package com.ruoyi.order.service;

import java.util.List;

import com.ruoyi.order.domain.ProNoticeRequest;
import com.ruoyi.order.domain.RfOrderForProNotice;
import com.ruoyi.order.domain.RfProduceNotice;

/**
 * 生产通知单Service接口
 * 
 * @author pg
 * @date 2024-02-19
 */
public interface IRfProduceNoticeService 
{
    /**
     * 查询生产通知单
     * 
     * @param id 生产通知单主键
     * @return 生产通知单
     */
    public RfProduceNotice selectRfProduceNoticeById(Long id);

    /**
     * 查询生产通知单列表
     * 
     * @param rfProduceNotice 生产通知单
     * @return 生产通知单集合
     */
    public List<RfProduceNotice> selectRfProduceNoticeList(RfProduceNotice rfProduceNotice);

    /**
     * 新增生产通知单
     * 
     * @param rfProduceNotice 生产通知单
     * @return 结果
     */
    public int insertRfProduceNotice(RfProduceNotice rfProduceNotice);

    /**
     * 修改生产通知单
     * 
     * @param rfProduceNotice 生产通知单
     * @return 结果
     */
    public int updateRfProduceNotice(RfProduceNotice rfProduceNotice);

    /**
     * 批量删除生产通知单
     * 
     * @param ids 需要删除的生产通知单主键集合
     * @return 结果
     */
    public int deleteRfProduceNoticeByIds(String ids);

    /**
     * 删除生产通知单信息
     * 
     * @param id 生产通知单主键
     * @return 结果
     */
    public int deleteRfProduceNoticeById(Long id);

    /**
     * 新增生产通知单  保存生产通知单主表  及 生产通知单明细  需要增加事务保证一致性
     *
     */
    public int insertRfProduceNoticeAndDeatail(ProNoticeRequest request);
}
