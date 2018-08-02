package org.shersfy.user.mapper.annotation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.ibatis.jdbc.SQL;
import org.shersfy.user.model.User;

public class UserDaoProvider {
    
    
    public String findList(Map<String, Object> map) {
        User info = (User) map.get("map");

        List<String> conditions = new ArrayList<>();
        conditions.add("1 = 1");
        if(info.getId()!=null) {
            conditions.add(String.format("id = %s", info.getId()));
        }
        if(info.getUsername()!=null) {
            conditions.add(String.format("username = '%s'", info.getUsername()));
        }
        if(info.getPassword()!=null) {
            conditions.add(String.format("password = '%s'", info.getPassword()));
        }
        if(info.getRoles()!=null) {
            conditions.add(String.format("roles = '%s'", info.getRoles()));
        }

        SQL sql = new SQL();
        sql.SELECT("id, username, password, roles, create_time as createTime, update_time as updateTime")
        .FROM("user")
        .WHERE(conditions.toArray(new String[conditions.size()]));
        
        if(StringUtils.isNotBlank(info.getSort()) && StringUtils.isNotBlank(info.getOrder())) {
            sql.ORDER_BY(String.format(" %s %s ", info.getSort(), info.getOrder().toUpperCase()));
        } else {
            sql.ORDER_BY(" id ASC ");
        }

        return sql.toString();
    }
    
    
    public String updateById(Map<String, Object> map) {
        User info = (User) map.get("map");
        
        List<String> sets = new ArrayList<>();
        
        if(info.getUsername()!=null) {
            sets.add(String.format("username = '%s'", info.getUsername()));
        }
        if(info.getPassword()!=null) {
            sets.add(String.format("password = '%s'", info.getPassword()));
        }
        if(info.getRoles()!=null) {
            sets.add(String.format("roles = '%s'", info.getRoles()));
        }
        
        sets.add(String.format("update_time = '%s'", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS")));
        
        SQL sql = new SQL();
        sql.UPDATE("user");
        sql.SET(sets.toArray(new String[sets.size()]));
        sql.WHERE(String.format("id = %s", info.getId()));
        
        return sql.toString();
    }


}
