package com.boredou.mercury.repository.util;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.session.RowBounds;

/**
 * 提供分页所需的属性
 * CopyRright (c) 2014:   <般豆网络>
 * Project:     <mercury-repository>
 * File Name:   <Pagination.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-3-31 下午5:07:38
 */
public class Pagination implements Serializable{
	private static final long serialVersionUID = 1L;

	private static final int RECORD_LIMIT = 10;
	
	// 当前页码，从1开始
	private int pp = 1;
	
	private String p = "1";

	// 每页显示的记录数
	private int pageSize = RECORD_LIMIT;

	// 起始记录数
	private int startRow;

	// 总记录数
	private int totalCount;


    //分页无关的 传入时间的参数 如时间区间 【beginTime  ~ endTime】

    private Date beginTime;

    private Date  endTime;
    
    //行号
    private  int  lineNumber;

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获得ibatis分页查询使用的RowBounds
	 */
	public RowBounds getRowBounds() {
		return new RowBounds(startRow, pageSize);
	}

	/**
	 * 设置 当前页码
	 * 
	 * @param   pp 页码
	 */
	public void setPP(int pp) {
		pp = pp + 1;
		this.pp = pp;
	}

	/**
	 * 获得 当前页码
	 * 
	 * @return 页码
	 */
	public int getPP() {
		return pp;
	}

	/**
	 * 获得 每页记录数
	 */
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * 设置 每页记录数
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getTotalPage() {
        int totalPage = totalCount / pageSize;
        if (totalCount % pageSize != 0 || totalPage == 0) {
            totalPage++;
        }
        return totalPage;
    }
	
	/**
	 * 设置 总记录数
	 * 
	 * @param totalCount
	 *            总记录数
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		if (pp > getTotalPage()) {
			pp = getTotalPage();
		}
		
		startRow = (pp-1) * pageSize;
	}

	/**
	 * 获得 总记录数
	 * 
	 * @return 总记录数
	 */
	public int getTotalCount() {
		return totalCount;
	}

}
