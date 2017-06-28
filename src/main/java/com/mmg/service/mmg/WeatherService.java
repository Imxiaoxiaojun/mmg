package com.mmg.service.mmg;

import com.mmg.entity.common.Weather;

public interface WeatherService {
	public String getWeekInfoByIp(String ip);
	public Weather get24HourInfo(String area);
}
