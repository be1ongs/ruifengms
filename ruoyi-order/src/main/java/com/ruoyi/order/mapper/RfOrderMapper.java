package com.ruoyi.order.mapper;

import java.util.List;
import com.ruoyi.order.domain.RfOrder;

/**
 * 采购订单Mapper接口
 *
 * @author pg
 * @date 2024-01-17
 */
public interface RfOrderMapper
{
    /**
     * 查询采购订单
     *
     * @param id 采购订单主键
     * @return 采购订单
     */
    public RfOrder selectRfOrderById(Integer id);

    /**
     * 查询采购订单列表
     *
     * @param rfOrder 采购订单
     * @return 采购订单集合
     */
    public List<RfOrder> selectRfOrderList(RfOrder rfOrder);

    /**
     * 新增采购订单
     *
     * @param rfOrder 采购订单
     * @return 结果
     */
    public int insertRfOrder(RfOrder rfOrder);

    /**
     * 修改采购订单
     *
     * @param rfOrder 采购订单
     * @return 结果
     */
    public int updateRfOrder(RfOrder rfOrder);

    /**
     * 删除采购订单
     *
     * @param id 采购订单主键
     * @return 结果
     */
    public int deleteRfOrderById(Integer id);

    /**
     * 批量删除采购订单
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRfOrderByIds(String[] ids);
}
