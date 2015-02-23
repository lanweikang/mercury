package com.boredou.mercury.web.write;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
	
	public static void writeToFile(String content,String filePath,boolean overWrite){
		File file = new File(filePath);
		if(file.exists()){
		}else{
			new File(file.getParent()).mkdirs();
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file,overWrite);
			byte[] bi = content.getBytes();
			fos.write(bi);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("finally")
	public static String readFile(String filePath){
		File file = new File(filePath);
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine())!= null) {
				sb.append(tempString);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
			return sb.toString();	
		}
	}
	public static void main(String[] args){
		String s = "lanweikang,\\\"蓝伟康";
//		writeToFile(s,"C:/Users/Administrator/Desktop/sk/log/log.txt");
		System.out.println(s);
	}
}
