package com.ruoyi.order.mapper;

import java.util.List;
import com.ruoyi.order.domain.ProduceNoticeDetailRecord;

/**
 * 生产通知单明细记录Mapper接口
 * 
 * @author pg
 * @date 2024-02-28
 */
public interface ProduceNoticeDetailRecordMapper 
{
    /**
     * 查询生产通知单明细记录
     * 
     * @param id 生产通知单明细记录主键
     * @return 生产通知单明细记录
     */
    public ProduceNoticeDetailRecord selectProduceNoticeDetailRecordById(Long id);

    /**
     * 查询生产通知单明细记录列表
     * 
     * @param produceNoticeDetailRecord 生产通知单明细记录
     * @return 生产通知单明细记录集合
     */
    public List<ProduceNoticeDetailRecord> selectProduceNoticeDetailRecordList(ProduceNoticeDetailRecord produceNoticeDetailRecord);

    /**
     * 新增生产通知单明细记录
     * 
     * @param produceNoticeDetailRecord 生产通知单明细记录
     * @return 结果
     */
    public int insertProduceNoticeDetailRecord(ProduceNoticeDetailRecord produceNoticeDetailRecord);

    /**
     * 修改生产通知单明细记录
     * 
     * @param produceNoticeDetailRecord 生产通知单明细记录
     * @return 结果
     */
    public int updateProduceNoticeDetailRecord(ProduceNoticeDetailRecord produceNoticeDetailRecord);

    /**
     * 删除生产通知单明细记录
     * 
     * @param id 生产通知单明细记录主键
     * @return 结果
     */
    public int deleteProduceNoticeDetailRecordById(Long id);

    /**
     * 批量删除生产通知单明细记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProduceNoticeDetailRecordByIds(String[] ids);
}
