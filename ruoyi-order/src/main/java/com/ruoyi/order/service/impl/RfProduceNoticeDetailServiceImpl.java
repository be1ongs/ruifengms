package com.ruoyi.order.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.order.constants.BaseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.RfProduceNoticeDetailMapper;
import com.ruoyi.order.domain.RfProduceNoticeDetail;
import com.ruoyi.order.service.IRfProduceNoticeDetailService;
import com.ruoyi.common.core.text.Convert;

/**
 * 生产通知单明细Service业务层处理
 * 
 * @author pg
 * @date 2024-02-19
 */
@Service
public class RfProduceNoticeDetailServiceImpl implements IRfProduceNoticeDetailService 
{
    @Autowired
    private RfProduceNoticeDetailMapper rfProduceNoticeDetailMapper;

    /**
     * 查询生产通知单明细
     * 
     * @param id 生产通知单明细主键
     * @return 生产通知单明细
     */
    @Override
    public RfProduceNoticeDetail selectRfProduceNoticeDetailById(Long id)
    {
        return rfProduceNoticeDetailMapper.selectRfProduceNoticeDetailById(id);
    }

    /**
     * 查询生产通知单明细列表
     * 
     * @param rfProduceNoticeDetail 生产通知单明细
     * @return 生产通知单明细
     */
    @Override
    public List<RfProduceNoticeDetail> selectRfProduceNoticeDetailList(RfProduceNoticeDetail rfProduceNoticeDetail)
    {
        rfProduceNoticeDetail.setDelFlag(BaseConstants.DEL_FLAG_NORMAL);
        return rfProduceNoticeDetailMapper.selectRfProduceNoticeDetailList(rfProduceNoticeDetail);
    }

    /**
     * 新增生产通知单明细
     * 
     * @param rfProduceNoticeDetail 生产通知单明细
     * @return 结果
     */
    @Override
    public int insertRfProduceNoticeDetail(RfProduceNoticeDetail rfProduceNoticeDetail)
    {
        rfProduceNoticeDetail.setCreateTime(DateUtils.getNowDate());
        return rfProduceNoticeDetailMapper.insertRfProduceNoticeDetail(rfProduceNoticeDetail);
    }

    /**
     * 修改生产通知单明细
     * 
     * @param rfProduceNoticeDetail 生产通知单明细
     * @return 结果
     */
    @Override
    public int updateRfProduceNoticeDetail(RfProduceNoticeDetail rfProduceNoticeDetail)
    {
        rfProduceNoticeDetail.setUpdateTime(DateUtils.getNowDate());
        return rfProduceNoticeDetailMapper.updateRfProduceNoticeDetail(rfProduceNoticeDetail);
    }

    /**
     * 批量删除生产通知单明细
     * 
     * @param ids 需要删除的生产通知单明细主键
     * @return 结果
     */
    @Override
    public int deleteRfProduceNoticeDetailByIds(String ids)
    {
        return rfProduceNoticeDetailMapper.deleteRfProduceNoticeDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除生产通知单明细信息
     * 
     * @param id 生产通知单明细主键
     * @return 结果
     */
    @Override
    public int deleteRfProduceNoticeDetailById(Long id)
    {
        return rfProduceNoticeDetailMapper.deleteRfProduceNoticeDetailById(id);
    }
}
