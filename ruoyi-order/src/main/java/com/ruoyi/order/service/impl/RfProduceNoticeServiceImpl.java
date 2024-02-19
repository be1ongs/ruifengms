package com.ruoyi.order.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.RfProduceNoticeMapper;
import com.ruoyi.order.domain.RfProduceNotice;
import com.ruoyi.order.service.IRfProduceNoticeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 生产通知单Service业务层处理
 * 
 * @author pg
 * @date 2024-02-19
 */
@Service
public class RfProduceNoticeServiceImpl implements IRfProduceNoticeService 
{
    @Autowired
    private RfProduceNoticeMapper rfProduceNoticeMapper;

    /**
     * 查询生产通知单
     * 
     * @param id 生产通知单主键
     * @return 生产通知单
     */
    @Override
    public RfProduceNotice selectRfProduceNoticeById(Long id)
    {
        return rfProduceNoticeMapper.selectRfProduceNoticeById(id);
    }

    /**
     * 查询生产通知单列表
     * 
     * @param rfProduceNotice 生产通知单
     * @return 生产通知单
     */
    @Override
    public List<RfProduceNotice> selectRfProduceNoticeList(RfProduceNotice rfProduceNotice)
    {
        return rfProduceNoticeMapper.selectRfProduceNoticeList(rfProduceNotice);
    }

    /**
     * 新增生产通知单
     * 
     * @param rfProduceNotice 生产通知单
     * @return 结果
     */
    @Override
    public int insertRfProduceNotice(RfProduceNotice rfProduceNotice)
    {
        rfProduceNotice.setCreateTime(DateUtils.getNowDate());
        return rfProduceNoticeMapper.insertRfProduceNotice(rfProduceNotice);
    }

    /**
     * 修改生产通知单
     * 
     * @param rfProduceNotice 生产通知单
     * @return 结果
     */
    @Override
    public int updateRfProduceNotice(RfProduceNotice rfProduceNotice)
    {
        rfProduceNotice.setUpdateTime(DateUtils.getNowDate());
        return rfProduceNoticeMapper.updateRfProduceNotice(rfProduceNotice);
    }

    /**
     * 批量删除生产通知单
     * 
     * @param ids 需要删除的生产通知单主键
     * @return 结果
     */
    @Override
    public int deleteRfProduceNoticeByIds(String ids)
    {
        return rfProduceNoticeMapper.deleteRfProduceNoticeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除生产通知单信息
     * 
     * @param id 生产通知单主键
     * @return 结果
     */
    @Override
    public int deleteRfProduceNoticeById(Long id)
    {
        return rfProduceNoticeMapper.deleteRfProduceNoticeById(id);
    }
}
