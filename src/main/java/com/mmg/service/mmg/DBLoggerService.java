package com.mmg.service.mmg;

import java.util.List;

import com.mmg.common.Page;
import com.mmg.entity.BaseObject;

public interface DBLoggerService {
	<T extends BaseObject> T addDBLog(T entity);
	<T extends BaseObject> List<T> getLogList(Class<T> clazz,Page<T> page);
}
