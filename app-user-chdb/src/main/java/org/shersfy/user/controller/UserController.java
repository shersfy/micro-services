package org.shersfy.user.controller;

import javax.annotation.Resource;

import org.shersfy.user.model.User;
import org.shersfy.user.pooljmx.HikariMonitor;
import org.shersfy.user.pooljmx.HikariPoolBean;
import org.shersfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class UserController {

    @Value("${version}")
    private String version;
    @Resource
    private UserService userService;
    @Resource
    private HikariMonitor monitor;

    @GetMapping("/")
    public Object index() {
        return "Welcome User Application";
    }

    @GetMapping("/version")
    public Object version() {
        return version;
    }

    @GetMapping("/user/list")
    public Object list(User where){
        return userService.findPage(where, 1, 10);
    }

    @GetMapping("/hikari/monitor")
    public Object getTotalConnections() {
        HikariPoolBean bean = new HikariPoolBean();
        bean.setActiveConnections(monitor.getPoolMXBean().getActiveConnections());
        bean.setIdleConnections(monitor.getPoolMXBean().getIdleConnections());
        bean.setThreadsAwaitingConnection(monitor.getPoolMXBean().getThreadsAwaitingConnection());
        bean.setTotalConnections(monitor.getPoolMXBean().getTotalConnections());
        return bean;
    }

}
