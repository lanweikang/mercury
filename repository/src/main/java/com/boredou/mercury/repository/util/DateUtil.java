package com.boredou.mercury.repository.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {

	/**
	 * 获取昨天 00:00:00
	 * @return yyyy-MM-dd 00:00:00
	 */
	public static Date getYesterday0(){
		// 得到当前时间的前一天
		Date d = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 1);
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		String yesterday = sp.format(d) + " 00:00:00";// 获取昨天日期
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = null;
		try {
			beginDate = sf.parse(yesterday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return beginDate;
	}
	
	/**
	 * 获取昨天时间   23:59:59
	 * @return yyyy-MM-dd 23:59:59
	 */
	public static Date getYesterday9(){
		// 得到当前时间的前一天
		Date d = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 1);
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		String yesterday = sp.format(d) + " 23:59:59";// 获取昨天日期
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date endDate = null;
		try {
			endDate = sf.parse(yesterday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return endDate;
	}
	
	public static Date getDate0(String date){
		String dateStr = date + " 00:00:00";
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = null;
		try {
			beginDate = sf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return beginDate;
	}
	
	public static Date getDate9(String date){
		String dateStr = date + " 23:59:59";
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date endDate = null;
		try {
			endDate = sf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return endDate;
	}
	
	/**
	 * 几天后
	 * @param date
	 * @return
	 */
	public static String addDays(Date date, int num){
		// 得到当前时间的前一天
		Date d = new Date(date.getTime() + 1000 * 60 * 60 * 24 * num);
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		return sp.format(d);
	}
	
	/**
	 * 日期转换(String转为Date)
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date parseDate(String dateStr, String format) {
		if (StringUtils.isBlank(dateStr))
			return null;
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 日期转换(Date转为String)
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static String parseDate(Date date, String format) {
		if (StringUtils.isBlank(format) || date == null)
			return null;
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public static void main(String[] args) {
		System.out.println(getYesterday0());
		System.out.println(getYesterday9());
		// 得到当前时间的前一天
		Date d = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 30);
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		String yesterday = sp.format(d) + " 00:00:00";// 获取昨天日期
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = null;
		try {
			beginDate = sf.parse(yesterday);
			System.out.println(beginDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String date = "2014-08-03";
		System.out.println(getDate0(date));;
		System.out.println(parseDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
		
		System.out.println(parseDate("2014-8-7 0:00:05", "yyyy-MM-dd HH:mm:ss"));
		System.out.println(addDays(new Date(), 25));
	}

}
