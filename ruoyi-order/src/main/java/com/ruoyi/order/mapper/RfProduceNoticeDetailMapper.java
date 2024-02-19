package com.ruoyi.order.mapper;

import java.util.List;
import com.ruoyi.order.domain.RfProduceNoticeDetail;

/**
 * 生产通知单明细Mapper接口
 * 
 * @author pg
 * @date 2024-02-19
 */
public interface RfProduceNoticeDetailMapper 
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
     * 删除生产通知单明细
     * 
     * @param id 生产通知单明细主键
     * @return 结果
     */
    public int deleteRfProduceNoticeDetailById(Long id);

    /**
     * 批量删除生产通知单明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRfProduceNoticeDetailByIds(String[] ids);
}
