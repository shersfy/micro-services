package org.onosproject.ipool.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.onosproject.ipool.beans.IpInfo;

@Mapper
public interface IpInfoDaoMapper {

    @SelectProvider(type=IpInfoProvider.class, method="findList")
    public List<IpInfo> findList(@Param("map")IpInfo where);
    
    @Select("SELECT id, version, ip_int as ipInt, ip_str as ipStr, used, create_time as createTime, update_time as updateTime "
        + "FROM ip_info "
        + "WHERE id = #{id}")
    public IpInfo findById(Long id);
   
    @UpdateProvider(type=IpInfoProvider.class, method="updateById")
    public int updateById(@Param("map")IpInfo entity);
    
    @Update("UPDATE ip_info SET used = #{used} WHERE id = #{id} AND used != #{used}")
    public int updateStatus(@Param("id")Long id, @Param("used")boolean used);
    
    @Insert("INSERT INTO ip_info (version, ip_int, ip_str, used) "
        + "VALUES (#{entity.version}, #{entity.ipInt}, #{entity.ipStr}, #{entity.used})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(@Param("entity")IpInfo entity);
    
    @Delete("DELETE FROM ip_info WHERE id = #{id}")
    public int deleteById(Long id);
}
