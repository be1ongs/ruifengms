package com.ruoyi.order.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ExceptionUtil;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.order.constants.BaseConstants;
import com.ruoyi.order.constants.OrderStatusConstants;
import com.ruoyi.order.constants.StatusConstants;
import com.ruoyi.order.domain.CheckAmountAboutOrder;
import com.ruoyi.order.domain.RfProduceNoticeDetail;
import com.ruoyi.order.service.IRfProduceNoticeDetailService;
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
public class RfOrderServiceImpl implements IRfOrderService {
    @Autowired
    private RfOrderMapper rfOrderMapper;

    @Autowired
    private IRfProduceNoticeDetailService iRfProduceNoticeDetailService;

    /**
     * 查询采购订单
     *
     * @param id 采购订单主键
     * @return 采购订单
     */
    @Override
    public RfOrder selectRfOrderById(Integer id) {
        return rfOrderMapper.selectRfOrderById(id);
    }

    /**
     * 查询采购订单列表
     *
     * @param rfOrder 采购订单
     * @return 采购订单
     */
    @Override
    public List<RfOrder> selectRfOrderList(RfOrder rfOrder) {
        List<RfOrder> list = rfOrderMapper.selectRfOrderList(rfOrder);
        list.forEach(order -> {
            CheckAmountAboutOrder checkAmountAboutOrder = calculateTotalNoticeAmount(order.getId());
            int remainingAmount = order.getAmount() - checkAmountAboutOrder.getNoticeAmount();
            if (remainingAmount > 0) {
                if (checkAmountAboutOrder.getProduceAmount() > 0) {
                    order.setOrderStatus(OrderStatusConstants.NOT_FINISH_WAIT_PLAN_AND_PRODUCING);
                } else if (checkAmountAboutOrder.getProduceAmount() == 0 && checkAmountAboutOrder.getNoticeAmount() == 0) {
                    order.setOrderStatus(OrderStatusConstants.NOT_FINISH_WAIT_PLAN);
                } else {
                    order.setOrderStatus(OrderStatusConstants.NOT_FINISH_WAIT_PLAN_AND_WAIT_PRODUCE);
                }
            } else if (remainingAmount == 0) {
                if (checkAmountAboutOrder.getProduceAmount() > 0) {
                    order.setOrderStatus(OrderStatusConstants.NOT_FINISH_PRODUCING);
                } else if ((order.getAmount() - checkAmountAboutOrder.getFinishAmount()) == 0 && order.getUnpaidNum() == checkAmountAboutOrder.getFinishAmount()) {
                    order.setOrderStatus(OrderStatusConstants.NOT_FINISH_WAIT_SEND);
                } else if (order.getUnpaidNum() == 0) {
                    order.setOrderStatus(OrderStatusConstants.FINISH);
                } else {
                    order.setOrderStatus(OrderStatusConstants.NOT_FINISH_WAIT_PRODUCE);
                }
            }
                    order.setFinishedProduceAmount(checkAmountAboutOrder.getFinishAmount());
                    order.setProducingAmount(checkAmountAboutOrder.getProduceAmount());
                    order.setWaitPlanAmount(order.getAmount() - checkAmountAboutOrder.getNoticeAmount());
        }


        );
        return list;
    }


    // 查询每个订单的已生产和未生产
    private CheckAmountAboutOrder calculateTotalNoticeAmount(int orderId) {

        CheckAmountAboutOrder checkAmountAboutOrder = new CheckAmountAboutOrder();
        checkAmountAboutOrder.setOrderId(orderId);
        RfProduceNoticeDetail detail = new RfProduceNoticeDetail();
        detail.setDelFlag(BaseConstants.DEL_FLAG_NORMAL);
        detail.setOrderId((long) orderId);
        List<RfProduceNoticeDetail> rfProduceNoticeDetailList = iRfProduceNoticeDetailService.selectRfProduceNoticeDetailList(detail);
        int totalNoticeAmount = 0;
        int finishAmount = 0;
        int produceAmount = 0;
        for (RfProduceNoticeDetail noticeDetail : rfProduceNoticeDetailList) {
            totalNoticeAmount += noticeDetail.getNoticeAmount();
            finishAmount += noticeDetail.getFinishAmount();
            produceAmount += noticeDetail.getProduceAmount();
        }
        checkAmountAboutOrder.setFinishAmount(finishAmount);
        checkAmountAboutOrder.setNoticeAmount(totalNoticeAmount);
        checkAmountAboutOrder.setProduceAmount(produceAmount);
        return checkAmountAboutOrder;
    }

    /**
     * 新增采购订单
     *
     * @param rfOrder 采购订单
     * @return 结果
     */
    @Override
    public int insertRfOrder(RfOrder rfOrder) {

        rfOrder.setCreateTime(DateUtils.getNowDate());
        rfOrder.setCreateBy(ShiroUtils.getLoginName());
        rfOrder.setPaidNum(0);
        rfOrder.setUnpaidNum(rfOrder.getAmount());
//        rfOrder.setFinishedProduceAmount(0);
//        rfOrder.setProducingAmount(0);
//        rfOrder.setWaitPlanAmount(rfOrder.getAmount());
        rfOrder.setOrderStatus(OrderStatusConstants.NOT_FINISH_WAIT_PLAN);
        //rfOrder.setOrderStatusDesc(StatusConstants.StatusType.getValueBykey(StatusConstants.NOT_FINISH));
        return rfOrderMapper.insertRfOrder(rfOrder);
    }

    /**
     * 修改采购订单
     *
     * @param rfOrder 采购订单
     * @return 结果
     */
    @Override
    public int updateRfOrder(RfOrder rfOrder) {
        if (rfOrder.getAmount() > (rfOrder.getUnpaidNum() + rfOrder.getPaidNum())) {
            rfOrder.setUnpaidNum(rfOrder.getAmount() - rfOrder.getPaidNum());
        } else {
            rfOrder.setUnpaidNum(null);
            rfOrder.setPaidNum(null);
        }
        rfOrder.setUpdateBy(ShiroUtils.getLoginName());
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
    public int deleteRfOrderByIds(String ids) {
        return rfOrderMapper.deleteRfOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除采购订单信息
     *
     * @param id 采购订单主键
     * @return 结果
     */
    @Override
    public int deleteRfOrderById(Integer id) {
        return rfOrderMapper.deleteRfOrderById(id);
    }
}
