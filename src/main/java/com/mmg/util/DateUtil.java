package com.mmg.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
	private static final Log logger = LogFactory.getLog(DateUtil.class);

	public static boolean compareTime(String str1, String str2) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		try {
			Date date1 = format.parse(str1);
			Date date2 = format.parse(str2);
			if (date1.compareTo(date2) < 0) {
				return true;
			}

		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	public static boolean isDay(String date) {
		SimpleDateFormat format = new SimpleDateFormat("HHmmss");
		try {
			Date date1 = format.parse("080000");
			Date date2 = format.parse("200000");
			if (date.length() == 4)
				date = date + "00";
			Date nowDate = format.parse(date);
			if (nowDate.compareTo(date2) < 0 && date1.compareTo(nowDate) < 0)
				return true;

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return false;
	}
	public static Timestamp getFullTime(){
		return new Timestamp(System.currentTimeMillis());
	}
}
