package com.ruoyi.order.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.order.constants.StatusConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.RfOrderMapper;
import com.ruoyi.order.domain.RfOrder;
import com.ruoyi.order.service.IRfOrderService;
import com.ruoyi.common.core.text.Convert;

/**
 * 采购订单Service业务层处理
 * 
 * @author pg
 * @date 2024-01-13
 */
@Service
public class RfOrderServiceImpl implements IRfOrderService 
{
    @Autowired
    private RfOrderMapper rfOrderMapper;

    /**
     * 查询采购订单
     * 
     * @param id 采购订单主键
     * @return 采购订单
     */
    @Override
    public RfOrder selectRfOrderById(Long id)
    {
        return rfOrderMapper.selectRfOrderById(id);
    }

    /**
     * 查询采购订单列表
     * 
     * @param rfOrder 采购订单
     * @return 采购订单
     */
    @Override
    public List<RfOrder> selectRfOrderList(RfOrder rfOrder)
    {
        return rfOrderMapper.selectRfOrderList(rfOrder);
    }

    /**
     * 新增采购订单
     * 
     * @param rfOrder 采购订单
     * @return 结果
     */
    @Override
    public int insertRfOrder(RfOrder rfOrder)
    {

        rfOrder.setCreateTime(DateUtils.getNowDate());
        rfOrder.setCreateBy(ShiroUtils.getLoginName());
        rfOrder.setPaidNum(0);
        rfOrder.setUnpaidNum(rfOrder.getAmount());
        rfOrder.setOrderStatus(StatusConstants.NOT_FINISH);
        rfOrder.setOrderStatusDesc(StatusConstants.StatusType.getValueBykey(StatusConstants.NOT_FINISH));
        return rfOrderMapper.insertRfOrder(rfOrder);
    }

    /**
     * 修改采购订单
     * 
     * @param rfOrder 采购订单
     * @return 结果
     */
    @Override
    public int updateRfOrder(RfOrder rfOrder)
    {
        rfOrder.setUpdateTime(DateUtils.getNowDate());
        return rfOrderMapper.updateRfOrder(rfOrder);
    }

    /**
     * 批量删除采购订单
     * 
     * @param ids 需要删除的采购订单主键
     * @return 结果
     */
    @Override
    public int deleteRfOrderByIds(String ids)
    {
        return rfOrderMapper.deleteRfOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除采购订单信息
     * 
     * @param id 采购订单主键
     * @return 结果
     */
    @Override
    public int deleteRfOrderById(Long id)
    {
        return rfOrderMapper.deleteRfOrderById(id);
    }
}
