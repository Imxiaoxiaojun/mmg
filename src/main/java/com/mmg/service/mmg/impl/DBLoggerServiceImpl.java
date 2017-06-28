package com.mmg.service.mmg.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.mmg.common.Page;
import com.mmg.entity.BaseObject;
import com.mmg.service.mmg.DBLoggerService;

@Service("dBLoggerService")
public class DBLoggerServiceImpl extends BaseServiceImpl implements DBLoggerService{

	@Override
	public <T extends BaseObject> T addDBLog(T entity) {
		return super.addObject(entity);
	}

	@Override
	public <T extends BaseObject> List<T> getLogList(Class<T> clazz, Page<T> page) {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		List<T> ruleList = super.findByCriteria(criteria,page.getStartRow(),page.getPageSize());
		return ruleList;
	}
	
}
