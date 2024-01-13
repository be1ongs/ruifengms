package com.ruoyi.order.service;

import java.util.List;
import com.ruoyi.order.domain.RfCustomer;

/**
 * 客户信息Service接口
 * 
 * @author ruoyi
 * @date 2024-01-13
 */
public interface IRfCustomerService 
{
    /**
     * 查询客户信息
     * 
     * @param customerId 客户信息主键
     * @return 客户信息
     */
    public RfCustomer selectRfCustomerByCustomerId(Long customerId);

    /**
     * 查询客户信息列表
     * 
     * @param rfCustomer 客户信息
     * @return 客户信息集合
     */
    public List<RfCustomer> selectRfCustomerList(RfCustomer rfCustomer);

    /**
     * 新增客户信息
     * 
     * @param rfCustomer 客户信息
     * @return 结果
     */
    public int insertRfCustomer(RfCustomer rfCustomer);

    /**
     * 修改客户信息
     * 
     * @param rfCustomer 客户信息
     * @return 结果
     */
    public int updateRfCustomer(RfCustomer rfCustomer);

    /**
     * 批量删除客户信息
     * 
     * @param customerIds 需要删除的客户信息主键集合
     * @return 结果
     */
    public int deleteRfCustomerByCustomerIds(String customerIds);

    /**
     * 删除客户信息信息
     * 
     * @param customerId 客户信息主键
     * @return 结果
     */
    public int deleteRfCustomerByCustomerId(Long customerId);
}
