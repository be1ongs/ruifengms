package com.ruoyi.order.service;

import com.ruoyi.order.domain.RfCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wangwp5
 * @Date: 2024/1/13 23:50
 */

@Service("customer")
public class RfCustomerService {

    @Autowired
    private IRfCustomerService iRfCustomerService;

    public List<RfCustomer> list(){
        return iRfCustomerService.selectRfCustomerList(null);
    }
}
