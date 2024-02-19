package com.ruoyi.order.domain;

import java.util.List;

public class ProNoticeRequest {
    private String name;
    private List<RfOrderForProNotice> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RfOrderForProNotice> getData() {
        return data;
    }

    public void setData(List<RfOrderForProNotice> data) {
        this.data = data;
    }
}
