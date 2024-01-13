package com.ruoyi.order.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.RfCustomerMapper;
import com.ruoyi.order.domain.RfCustomer;
import com.ruoyi.order.service.IRfCustomerService;
import com.ruoyi.common.core.text.Convert;

/**
 * 客户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-13
 */
@Service
public class RfCustomerServiceImpl implements IRfCustomerService 
{
    @Autowired
    private RfCustomerMapper rfCustomerMapper;

    /**
     * 查询客户信息
     * 
     * @param customerId 客户信息主键
     * @return 客户信息
     */
    @Override
    public RfCustomer selectRfCustomerByCustomerId(Long customerId)
    {
        return rfCustomerMapper.selectRfCustomerByCustomerId(customerId);
    }

    /**
     * 查询客户信息列表
     * 
     * @param rfCustomer 客户信息
     * @return 客户信息
     */
    @Override
    public List<RfCustomer> selectRfCustomerList(RfCustomer rfCustomer)
    {
        return rfCustomerMapper.selectRfCustomerList(rfCustomer);
    }

    /**
     * 新增客户信息
     * 
     * @param rfCustomer 客户信息
     * @return 结果
     */
    @Override
    public int insertRfCustomer(RfCustomer rfCustomer)
    {
        rfCustomer.setCreateTime(DateUtils.getNowDate());
        return rfCustomerMapper.insertRfCustomer(rfCustomer);
    }

    /**
     * 修改客户信息
     * 
     * @param rfCustomer 客户信息
     * @return 结果
     */
    @Override
    public int updateRfCustomer(RfCustomer rfCustomer)
    {
        rfCustomer.setUpdateTime(DateUtils.getNowDate());
        return rfCustomerMapper.updateRfCustomer(rfCustomer);
    }

    /**
     * 批量删除客户信息
     * 
     * @param customerIds 需要删除的客户信息主键
     * @return 结果
     */
    @Override
    public int deleteRfCustomerByCustomerIds(String customerIds)
    {
        return rfCustomerMapper.deleteRfCustomerByCustomerIds(Convert.toStrArray(customerIds));
    }

    /**
     * 删除客户信息信息
     * 
     * @param customerId 客户信息主键
     * @return 结果
     */
    @Override
    public int deleteRfCustomerByCustomerId(Long customerId)
    {
        return rfCustomerMapper.deleteRfCustomerByCustomerId(customerId);
    }
}
