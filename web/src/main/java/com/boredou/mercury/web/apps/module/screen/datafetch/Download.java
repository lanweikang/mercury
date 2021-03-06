package com.boredou.mercury.web.apps.module.screen.datafetch;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.fastjson.JSONObject;
import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.server.service.AmazonCategoryService;
import com.boredou.mercury.server.service.ItemDownloadService;
import com.boredou.mercury.web.base.AbstractController;
import com.boredou.mercury.web.write.JsonHttpWrite;

public class Download  extends AbstractController{
	@Autowired
	private ItemDownloadService itemDownloadService;
	@Autowired
	private AmazonCategoryService amazonCategoryService;
	
	public void doPerform(Context context) {
		System.out.println("start...");
		List<AmazonCategoryDO> categoryList = amazonCategoryService.getAmazonCategoryAll();
		context.put("categoryList", categoryList);
		System.out.println("end...");
	}
	
	public void doDownload(){
		System.out.println("lwk..."+getParametersMap());
		JSONObject obj = new JSONObject();
		obj.put("success",true);
		JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response,obj);
		jsonHttpWrite.write();
	}
	
	
	public static void main(String[] args){
		 try {

			   // 创建新的Excel 工作簿

			   HSSFWorkbook workbook = new HSSFWorkbook();

			   // 在Excel工作簿中建一工作表，其名为缺省值
			   // 如要新建一名为"效益指标"的工作表，其语句为：
			   HSSFSheet sheet = workbook.createSheet("Sheet1");
			   // HSSFSheet sheet = workbook.createSheet();

			   // 在索引0的位置创建行（最顶端的行）

			   HSSFRow row = sheet.createRow(0);
			   // 在索引0的位置创建单元格（左上端）
			   HSSFCell cell = row.createCell( 0);
			   // 定义单元格为字符串类型
			   cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			   // 在单元格中输入一些内容
			   cell.setCellValue(" number 1");
			   HSSFCell cell01 = row.createCell(1);
			   // 定义单元格为字符串类型
			   cell01.setCellType(HSSFCell.CELL_TYPE_STRING);
			   // 在单元格中输入一些内容
			   cell01.setCellValue(" 蓝伟康");   
			   
			   File outputFile = new File("C:\\Users\\Administrator\\Desktop\\df\\xxx.xls");
			   // 新建一输出文件流
			   FileOutputStream fOut = new FileOutputStream(outputFile);
			   // 把相应的Excel 工作簿存盘
			   workbook.write(fOut);
			   fOut.flush();
			   // 操作结束，关闭文件
			   fOut.close();
			   System.out.println("文件生成...");

			  } catch (Exception e) {
			   System.out.println("已运行 xlCreate() : " + e);
			  }
			 }
	}



