package org.shersfy.user.pooljmx;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariPoolMXBean;

@Component
public class HikariMonitor {
    
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    @Value("${spring.datasource.hikari.pool-name}")
    private String poolName;
    
    private HikariPoolMXBean poolProxy;

    /**
     * 获取MBean<br/>
     * <a href="https://github.com/brettwooldridge/HikariCP/wiki/MBean-(JMX)-Monitoring-and-Management">
     * https://github.com/brettwooldridge/HikariCP/wiki/MBean-(JMX)-Monitoring-and-Management</a>
     * 1. registerMbeans=true<br/>
     * 2. com.zaxxer.hikari:type=Pool (poolName)<br/>
     * 3. 必须至少执行过一次getConnection()<br/>
     * configuration set on the HikariDataSource before the first call to getConnection() will be honored
     * @return
     */
    public HikariPoolMXBean getPoolMXBean() {
        if(poolProxy !=null) {
            return poolProxy;
        }
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            ManagementFactory.getPlatformMXBean(MemoryMXBean.class);
            ObjectName objectName   = new ObjectName(String.format("com.zaxxer.hikari:type=Pool (%s)", poolName));
            poolProxy = JMX.newMXBeanProxy(mBeanServer, objectName, HikariPoolMXBean.class);
            
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return poolProxy;
    }
}
