package org.shersfy.user.pooljmx;

import com.alibaba.fastjson.JSON;

public class HikariPoolBean {
    
    
    private int activeConnections;
    private int idleConnections;
    private int threadsAwaitingConnection;
    private int totalConnections;

    public HikariPoolBean() {}
    
    public HikariPoolBean(int activeConnections, int idleConnections, int threadsAwaitingConnection,
                          int totalConnections) {
        super();
        this.activeConnections = activeConnections;
        this.idleConnections = idleConnections;
        this.threadsAwaitingConnection = threadsAwaitingConnection;
        this.totalConnections = totalConnections;
    }

    public int getActiveConnections() {
        return activeConnections;
    }

    public void setActiveConnections(int activeConnections) {
        this.activeConnections = activeConnections;
    }

    public int getIdleConnections() {
        return idleConnections;
    }

    public void setIdleConnections(int idleConnections) {
        this.idleConnections = idleConnections;
    }

    public int getThreadsAwaitingConnection() {
        return threadsAwaitingConnection;
    }

    public void setThreadsAwaitingConnection(int threadsAwaitingConnection) {
        this.threadsAwaitingConnection = threadsAwaitingConnection;
    }

    public int getTotalConnections() {
        return totalConnections;
    }

    public void setTotalConnections(int totalConnections) {
        this.totalConnections = totalConnections;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
