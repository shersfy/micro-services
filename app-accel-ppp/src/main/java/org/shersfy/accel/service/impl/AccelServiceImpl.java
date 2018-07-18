package org.shersfy.accel.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.shersfy.accel.service.AccelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AccelServiceImpl implements AccelService {
    
    static final Logger LOGGER = LoggerFactory.getLogger(AccelServiceImpl.class);
        
    @Resource
    private DataSource dataSource;
    
    @Override
    public String findIp() {
        String sql = "select ip from ip_pool";
        String ip  = "";
        
        Connection conn = null;
        Statement st = null;
        ResultSet res = null;
        try {
            conn = dataSource.getConnection();
            st   = conn.createStatement();
            res  = st.executeQuery(sql);
            while(res.next()) {
                ip = res.getString("ip");
                break;
            }
            if(!StringUtils.isEmpty(ip)) {
                st.executeUpdate(String.format("delete ip_info where ip='%s'", ip));
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("", e);
            }
        }
        return ip;
    }

}
