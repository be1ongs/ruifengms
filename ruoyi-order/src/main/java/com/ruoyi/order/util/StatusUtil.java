package com.ruoyi.order.util;

import com.ruoyi.order.constants.BaseConstants;
import com.ruoyi.order.constants.OrderStatusConstants;
import com.ruoyi.order.domain.CheckAmountAboutOrder;
import com.ruoyi.order.domain.RfOrder;
import com.ruoyi.order.domain.RfProduceNoticeDetail;
import com.ruoyi.order.service.IRfOrderService;
import com.ruoyi.order.service.IRfProduceNoticeDetailService;

import java.util.List;

public class StatusUtil {

    public static String calculateOrderStatus(int orderId, IRfProduceNoticeDetailService rfProduceNoticeDetailService, IRfOrderService rfOrderService) {
        CheckAmountAboutOrder checkAmountAboutOrder = calculateTotalNoticeAmount(orderId, rfProduceNoticeDetailService);
        RfOrder rfOrder = rfOrderService.selectRfOrderById(orderId);
        int remainingAmount = rfOrder.getAmount() - checkAmountAboutOrder.getNoticeAmount();
        String orderStatus;
        if (remainingAmount > 0) {
            if (checkAmountAboutOrder.getProduceAmount() > 0) {
                orderStatus = OrderStatusConstants.NOT_FINISH_WAIT_PLAN_AND_PRODUCING;
            } else if (checkAmountAboutOrder.getProduceAmount() == 0 && checkAmountAboutOrder.getNoticeAmount() == 0) {
                orderStatus = OrderStatusConstants.NOT_FINISH_WAIT_PLAN;
            } else {
                orderStatus = OrderStatusConstants.NOT_FINISH_WAIT_PLAN_AND_WAIT_PRODUCE;
            }
        } else if (remainingAmount == 0) {
            if (checkAmountAboutOrder.getProduceAmount() > 0) {
                orderStatus = OrderStatusConstants.NOT_FINISH_PRODUCING;
            } else if ((rfOrder.getAmount() - checkAmountAboutOrder.getFinishAmount()) == 0 && rfOrder.getUnpaidNum() == checkAmountAboutOrder.getFinishAmount()) {
                orderStatus = OrderStatusConstants.NOT_FINISH_WAIT_SEND;
            } else if (rfOrder.getUnpaidNum() == 0) {
                orderStatus = OrderStatusConstants.FINISH;
            } else {
                orderStatus = OrderStatusConstants.NOT_FINISH_WAIT_PRODUCE;
            }
        }else{
            orderStatus = "-1";
        }
        return orderStatus;
    }

    private static CheckAmountAboutOrder calculateTotalNoticeAmount(int orderId, IRfProduceNoticeDetailService rfProduceNoticeDetailService) {
        CheckAmountAboutOrder checkAmountAboutOrder = new CheckAmountAboutOrder();
        checkAmountAboutOrder.setOrderId(orderId);
        RfProduceNoticeDetail detail = new RfProduceNoticeDetail();
        detail.setDelFlag(BaseConstants.DEL_FLAG_NORMAL);
        detail.setOrderId((long) orderId);
        List<RfProduceNoticeDetail> rfProduceNoticeDetailList = rfProduceNoticeDetailService.selectRfProduceNoticeDetailList(detail);
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
}
