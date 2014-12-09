package com.boredou.mercury.repository.util;

import java.math.*;
import java.text.DecimalFormat;

/**
 * 
 * BigDecimal常用方法
 * Project:     <wsh-j2se>
 * File Name:   <BigDecimalDemo.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-7-30 下午3:13:31
 */
public class BigDecimalUtil {
	
	static final int location = 10;
	
	/**
	 * 定义加法方法，参数为加数与被加数
	 * 
	 * @param value1
	 *            相加的第一个数
	 * @param value2
	 *            相加的第二个数
	 * @return 两数之和
	 */
	public static BigDecimal add(double value1, double value2) {
		// 实例化Decimal对象
		BigDecimal b1 = new BigDecimal(Double.toString(value1));
		BigDecimal b2 = new BigDecimal(Double.toString(value2));
		return b1.add(b2); // 调用加法方法
	}
	
	/**
	 * 定义减法方法，参数为减数与被减数
	 * 
	 * @param value1
	 *            被减数
	 * @param value2
	 *            减数
	 * @return 运算结果
	 */
	public BigDecimal sub(double value1, double value2) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1));
		BigDecimal b2 = new BigDecimal(Double.toString(value2));
		return b1.subtract(b2); // 调用减法方法
	}
	
	/**
	 * 定义乘法方法，参数为乘数与被乘数
	 * 
	 * @param value1
	 *            第一个乘数
	 * @param value2
	 *            第二个乘数
	 * @return
	 */
	public BigDecimal mul(double value1, double value2) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1));
		BigDecimal b2 = new BigDecimal(Double.toString(value2));
		return b1.multiply(b2); // 调用乘法方法
	}
	
	/**
	 * 定义除法方法，参数为除数与被除数
	 * 
	 * @param value1 被除数
	 * @param value2 除数
	 * @return
	 */
	public BigDecimal div(double value1, double value2) {
		return div(value1, value2, location); // 调用自定义除法方法
	}
	
	// 定义除法方法，参数分别为除数与被除数以及商小数点后的位数
	public BigDecimal div(double value1, double value2, int b) {
		if (b < 0) {
			System.out.println("b值必须大于等于0");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(value1));
		BigDecimal b2 = new BigDecimal(Double.toString(value2));
		// 调用除法方法，商小数点后保留b位，并将结果进行四舍五入操作
		return b1.divide(b2, b, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 比较两个double值是否相等可设置精度（不进行四舍五入）
	 * @param a double数字
	 * @param b double数字
	 * @param digits 精度（第几位）
	 * @return
	 */
	public static boolean compareDouble(double a, double b, int digits){
		DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(digits);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);
        String a1 = formater.format(a);
        String b1 = formater.format(b);
		return a1.equals(b1);
	}
	
	public static void main(String[] args) {
		BigDecimal a = add(0.0,11.2);
		System.out.println(a.doubleValue());
		
		System.out.println(compareDouble(0.021,0.025,2));
		DecimalFormat    df   = new DecimalFormat("#0.00");   
		double d1 = 3.23556;  
		double d2 = 0.0;
		double d3 = 2.0;
		System.out.println(df.format(d1) + "  " + df.format(d2) + "  " + df.format(d3));; 
		
		DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(0);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);
        String a1 = formater.format(3.123);
        String b1 = formater.format(3.789);
		System.out.println(a1 + " " + b1);
		
		
	}
	
}
