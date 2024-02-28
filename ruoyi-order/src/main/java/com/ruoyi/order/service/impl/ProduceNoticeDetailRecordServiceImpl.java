package com.ruoyi.order.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.ProduceNoticeDetailRecordMapper;
import com.ruoyi.order.domain.ProduceNoticeDetailRecord;
import com.ruoyi.order.service.IProduceNoticeDetailRecordService;
import com.ruoyi.common.core.text.Convert;

/**
 * 生产通知单明细记录Service业务层处理
 * 
 * @author pg
 * @date 2024-02-28
 */
@Service
public class ProduceNoticeDetailRecordServiceImpl implements IProduceNoticeDetailRecordService 
{
    @Autowired
    private ProduceNoticeDetailRecordMapper produceNoticeDetailRecordMapper;

    /**
     * 查询生产通知单明细记录
     * 
     * @param id 生产通知单明细记录主键
     * @return 生产通知单明细记录
     */
    @Override
    public ProduceNoticeDetailRecord selectProduceNoticeDetailRecordById(Long id)
    {
        return produceNoticeDetailRecordMapper.selectProduceNoticeDetailRecordById(id);
    }

    /**
     * 查询生产通知单明细记录列表
     * 
     * @param produceNoticeDetailRecord 生产通知单明细记录
     * @return 生产通知单明细记录
     */
    @Override
    public List<ProduceNoticeDetailRecord> selectProduceNoticeDetailRecordList(ProduceNoticeDetailRecord produceNoticeDetailRecord)
    {
        return produceNoticeDetailRecordMapper.selectProduceNoticeDetailRecordList(produceNoticeDetailRecord);
    }

    /**
     * 新增生产通知单明细记录
     * 
     * @param produceNoticeDetailRecord 生产通知单明细记录
     * @return 结果
     */
    @Override
    public int insertProduceNoticeDetailRecord(ProduceNoticeDetailRecord produceNoticeDetailRecord)
    {
        produceNoticeDetailRecord.setCreateTime(DateUtils.getNowDate());
        return produceNoticeDetailRecordMapper.insertProduceNoticeDetailRecord(produceNoticeDetailRecord);
    }

    /**
     * 修改生产通知单明细记录
     * 
     * @param produceNoticeDetailRecord 生产通知单明细记录
     * @return 结果
     */
    @Override
    public int updateProduceNoticeDetailRecord(ProduceNoticeDetailRecord produceNoticeDetailRecord)
    {
        produceNoticeDetailRecord.setUpdateTime(DateUtils.getNowDate());
        return produceNoticeDetailRecordMapper.updateProduceNoticeDetailRecord(produceNoticeDetailRecord);
    }

    /**
     * 批量删除生产通知单明细记录
     * 
     * @param ids 需要删除的生产通知单明细记录主键
     * @return 结果
     */
    @Override
    public int deleteProduceNoticeDetailRecordByIds(String ids)
    {
        return produceNoticeDetailRecordMapper.deleteProduceNoticeDetailRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除生产通知单明细记录信息
     * 
     * @param id 生产通知单明细记录主键
     * @return 结果
     */
    @Override
    public int deleteProduceNoticeDetailRecordById(Long id)
    {
        return produceNoticeDetailRecordMapper.deleteProduceNoticeDetailRecordById(id);
    }
}
