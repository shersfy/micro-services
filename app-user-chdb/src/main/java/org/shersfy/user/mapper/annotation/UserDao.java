package org.shersfy.user.mapper.annotation;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.shersfy.user.model.User;

@Mapper
public interface UserDao {
    
    @SelectProvider(type=UserDaoProvider.class, method="findList")
    public List<User> findList(@Param("map")User where);
    
    @Select("SELECT * FROM user WHERE id = #{id}")
    public User findById(Long id);
   
    @UpdateProvider(type=UserDaoProvider.class, method="updateById")
    public int updateById(@Param("map")User entity);
    
    @Insert("INSERT INTO user (username, password, roles) VALUES (#{entity.username}, #{entity.password}, #{entity.roles})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(@Param("entity")User entity);
    
    @Delete("DELETE FROM user WHERE id = #{id}")
    public int deleteById(Long id);


}
