package com.boredou.mercury.web.write.session.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alibaba.fastjson.JSONObject;




public class JDBCTest {
	
	public static void main(String[] args) {
		String content="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动类!");
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost/web_data_fetch";
//		String url = "jdbc:mysql://115.159.31.27:3306/web_data_fetch";
		String user = "root";
		String password = "0906";
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			Statement stmt = con.createStatement();
			String sql = "select * from amazon_item where name is not null limit 1";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				content = rs.getString("whole_content");
			}
			if(rs!=null){
				rs.close();
			}
			if(stmt !=null){
				stmt.close();
			}
			if(con != null){
				con.close();
			}
			
		} catch (SQLException e) {
			System.out.println("数据库连接失败！");
			e.printStackTrace();
		}
		System.out.println(content);
		JSONObject jsonObject = JSONObject.parseObject(content);
		for(String key:jsonObject.keySet()){
			System.out.println("key: "+key+" ,value: "+jsonObject.getString(key));
		}
		System.out.println("------------------------------");
		System.out.println(jsonObject.getString("key"));
		if(jsonObject.getString("key")==null){
			System.out.println("==null");
		}
		
		
	}

}
