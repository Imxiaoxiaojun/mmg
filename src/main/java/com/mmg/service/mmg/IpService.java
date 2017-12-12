package com.mmg.service.mmg;

import com.mmg.vo.IpAddress;

public interface IpService {
	public IpAddress queryLocalByIp(String ip);
}
