package com.mmg.service;

import com.mmg.entity.common.IpAddress;

public interface IpService {
	public IpAddress queryLocalByIp(String ip);
}
