package com.ruoyi.order.domain;


public class CheckAmountAboutOrder {
    private Integer orderId;

    private Integer noticeAmount;

    private Integer finishAmount;

    private Integer produceAmount;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getNoticeAmount() {
        return noticeAmount;
    }

    public void setNoticeAmount(Integer noticeAmount) {
        this.noticeAmount = noticeAmount;
    }

    public Integer getFinishAmount() {
        return finishAmount;
    }

    public void setFinishAmount(Integer finishAmount) {
        this.finishAmount = finishAmount;
    }

    public Integer getProduceAmount() {
        return produceAmount;
    }

    public void setProduceAmount(Integer produceAmount) {
        this.produceAmount = produceAmount;
    }
}
