package org.shersfy.user.service;

import java.io.Serializable;
import java.util.List;

import org.shersfy.user.beans.Page;
import org.shersfy.user.model.BaseEntity;

public interface BaseService<T extends BaseEntity, Id extends Serializable> {
	
	int deleteById(Id id);

	int insert(T entity);

	T findById(Id id);

	int updateById(T entity);
	
	long findListCount(T where);
	
	List<T> findList(T where);

	Page<T> findPage(T where, int pageNum, int pageSize);
}
