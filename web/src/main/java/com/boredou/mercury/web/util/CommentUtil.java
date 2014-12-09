package com.boredou.mercury.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.alibaba.citrus.util.StringUtil;

/**
 * 公用工具类 CopyRright (c) 2014: <般豆网络> Project: <mercury-web> File Name:
 * <CommentUtil.java> Module ID: <(模块)类编号，可以引用系统设计中的类编号> Comments:
 * <对此类的描述，可以引用系统设计中的描述> JDK version used: <JDK1.6>
 * 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-4-1 下午12:07:07
 */
public class CommentUtil {

	/**
	 * 返回时间格式后的的字符串 格式为：“yyyy-MM-dd HH:mm:ss”
	 * 
	 * @param aDate
	 *            要格式化的时间
	 * @return String 格式化后的字符串
	 */
	public static String longDateGB(Date aDate) {
		if (aDate == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(aDate);
	}
	
	/**
	 * 格式化时间
	 * @param date  要格式化的时间
	 * @param formatType 字符串格式
	 * @return String 格式化后的字符串
	 */
	public static String dateFomat(Date date, String formatType) {
		if (date == null && StringUtil.isBlank(formatType))
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		return formatter.format(date);
	}
	
	/**
	 * 将字符串转化成时间对象
	 * 
	 * @param strDate
	 *            字符串:Wed Apr 02 2014 00:00:00 GMT+0800 (中国标准时间)
	 * @return Date 时间对象
	 */
	public static Date parser(String strDate) {
		
		SimpleDateFormat formart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = formart.format(new Date(strDate));
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
        	return sf.parse(date);
		} catch (ParseException e1) {
			return null;
		}
	}

}
