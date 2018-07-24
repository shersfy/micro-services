package org.onosproject.ipool.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.onosproject.ipool.beans.IpInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpInfoProvider {
    
    private Logger log = LoggerFactory.getLogger(getClass());
    
    public String findList(Map<String, Object> map) {
        IpInfo info = (IpInfo) map.get("map");
       
        List<String> conditions = new ArrayList<>();
        conditions.add("1 = 1");
        if(info.getId()!=null) {
            conditions.add(String.format("id = %s", info.getId()));
        }
        if(info.getVersion()!=null) {
            conditions.add(String.format("version = %s", info.getVersion()));
        }
        if(info.getIpInt()!=null) {
            conditions.add(String.format("ip_int = %s", info.getIpInt()));
        }
        if(info.getIpStr()!=null) {
            conditions.add(String.format("ip_str = '%s'", info.getIpStr()));
        }
        if(info.getUsed()!=null) {
            conditions.add(String.format("used = %s", info.getUsed()));
        }
        
        SQL sql = new SQL();
        sql.SELECT("id, version, ip_int as ipInt, ip_str as ipStr, used, create_time as createTime, update_time as updateTime")
        .FROM("ip_info")
        .WHERE(conditions.toArray(new String[conditions.size()]));
        if(StringUtils.isNotBlank(info.getSort()) && StringUtils.isNotBlank(info.getOrder())) {
            sql.ORDER_BY(String.format(" %s %s ", info.getSort(), info.getOrder().toUpperCase()));
        } else {
            sql.ORDER_BY(" id ASC ");
        }
        
        StringBuffer buff = new StringBuffer(sql.toString());
        if(info.getPageNo() != null && info.getPageSize()!=null) {
            int start = info.getPageNo()<1?1:(info.getPageNo() - 1) * info.getPageSize();
            buff.append(String.format(" LIMIT %s, %s ", start, info.getPageSize()));
        }
        
        log.debug(buff.toString());
        return buff.toString();
    }
    
    public String updateById(Map<String, Object> map) {
        IpInfo info = (IpInfo) map.get("map");
        
        List<String> sets = new ArrayList<>();
        
        if(info.getVersion()!=null) {
            sets.add(String.format("version = %s", info.getVersion()));
        }
        if(info.getIpInt()!=null) {
            sets.add(String.format("ip_int = %s", info.getIpInt()));
        }
        if(info.getIpStr()!=null) {
            sets.add(String.format("ip_str = %s", info.getIpStr()));
        }
        if(info.getUsed()!=null) {
            sets.add(String.format("used = %s", info.getUsed()));
        }
        
        SQL sql = new SQL();
        sql.UPDATE("ip_info");
        sql.SET(sets.toArray(new String[sets.size()]));
        sql.WHERE(String.format("id = %s", info.getId()));
        log.debug(sql.toString());
        return sql.toString();
    }

}
