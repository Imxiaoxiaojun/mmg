package com.mmg.service.mmg;

import com.mmg.entity.common.IpAddress;

public interface IpService {
	public IpAddress queryLocalByIp(String ip);
}
