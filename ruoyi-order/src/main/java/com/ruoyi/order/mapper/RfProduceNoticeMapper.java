package com.ruoyi.order.mapper;

import java.util.List;
import com.ruoyi.order.domain.RfProduceNotice;

/**
 * 生产通知单Mapper接口
 * 
 * @author pg
 * @date 2024-02-19
 */
public interface RfProduceNoticeMapper 
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
     * 删除生产通知单
     * 
     * @param id 生产通知单主键
     * @return 结果
     */
    public int deleteRfProduceNoticeById(Long id);

    /**
     * 批量删除生产通知单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRfProduceNoticeByIds(String[] ids);
}
