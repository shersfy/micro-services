package org.shersfy.user.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.shersfy.user.model.BaseEntity;

public interface BaseMapper<T extends BaseEntity, Id extends Serializable> {
	
	int deleteById(Id id);

	int insert(T entity);

	T findById(Id id);

	int updateById(T entity);

	long findListCount(Map<String, Object> map);
	
	List<T> findList(Map<String, Object> map);
}
