package com.ruoyi.order.constants;

public class StatusConstants {


    /**
     * 订单已完成
     */
    public static final String FINISH = "0";
    /**
     * 订单未完成
     */
    public static final String NOT_FINISH = "1";
    /**
     * 订单状态 0：已完成  1：未完成
     */
    public enum StatusType {
        FINISH("0", "已完成"),
        NOT_FINISH("1", "未完成");
        private String key;
        private String value;

        public static String getValueBykey(String key) {
            for (StatusConstants.StatusType statusType : StatusConstants.StatusType.values()) {
                if (statusType.getKey().equals(key)) {
                    return statusType.getValue();
                }
            }
            return null;
        }

        StatusType(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
