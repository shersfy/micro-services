package org.shersfy.user.kafka;

import java.util.concurrent.atomic.AtomicBoolean;

import com.alibaba.fastjson.JSON;

public class SubscribeKey {
    
    private String topics;
    private AtomicBoolean closed;
    
    /**
     * 
     * @param topics 逗号分隔
     */
    public SubscribeKey(String topics) {
        super();
        this.topics = topics;
        this.closed = new AtomicBoolean(false);
    }
    
    public String getTopics() {
        return topics;
    }
    public void setTopics(String topics) {
        this.topics = topics;
    }
    public AtomicBoolean getClosed() {
        return closed;
    }
    public void setClosed(AtomicBoolean closed) {
        this.closed = closed;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
