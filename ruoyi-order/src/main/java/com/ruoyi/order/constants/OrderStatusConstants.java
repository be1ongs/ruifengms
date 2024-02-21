package com.ruoyi.order.constants;

public class OrderStatusConstants {


    /**
     * 订单已完成
     */
    public static final String FINISH = "0";
    /**
     * 未完成(待计划)
     */
    public static final String NOT_FINISH_WAIT_PLAN = "1";

    /**
     * 未完成(生产中,待计划)
     */
    public static final String NOT_FINISH_WAIT_PLAN_AND_PRODUCING = "2";
    /**
     * 	未完成(生产中)
     */
    public static final String NOT_FINISH_PRODUCING = "3";

    /**
     * 	未完成(待发货)
     */
    public static final String NOT_FINISH_WAIT_SEND = "4";

    /**
     *未完成(待生产)
     */
    public static final String NOT_FINISH_WAIT_PRODUCE = "5";

    /**
     * 未完成(待生产,待计划)
     */
    public static final String NOT_FINISH_WAIT_PLAN_AND_WAIT_PRODUCE = "6";

}
