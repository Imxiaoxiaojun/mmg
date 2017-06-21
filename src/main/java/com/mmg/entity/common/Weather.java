package com.mmg.entity.common;

import java.util.List;

public class Weather {

	private String area;
	private String ret_code;
	private String areaid;
	private List<Weather24H> hourList;

	public String getRet_code() {
		return ret_code;
	}

	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public List<Weather24H> getHourList() {
		return hourList;
	}

	public void setHourList(List<Weather24H> hourList) {
		this.hourList = hourList;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}
