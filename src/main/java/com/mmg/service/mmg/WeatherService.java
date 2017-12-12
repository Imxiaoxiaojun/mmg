package com.mmg.service.mmg;

import com.mmg.vo.Weather;

public interface WeatherService {
	public String getWeekInfoByIp(String ip);
	public Weather get24HourInfo(String area);
}
