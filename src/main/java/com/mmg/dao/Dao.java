package com.mmg.dao;

import java.io.Serializable;
import java.util.List;

import com.mmg.model.BaseObject;

public interface Dao {
	<T extends BaseObject> List<T> getAllObjects(Class<T> clazz);
	<T extends BaseObject> T getObject(Class<T> clazz, Serializable id);
	<T extends BaseObject> T saveObject(T entity);
	<T extends BaseObject> T removeObject(T entity);
	<T extends BaseObject> T updateObject(T entity);
	<T extends BaseObject> T addObject(T entity);
	<T extends BaseObject> int getObjectCount(Class<T> clazz);
	<T extends BaseObject> T removeObjectById(Class<T> clazz, Serializable id);
}
