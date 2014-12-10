package com.boredou.mercury.web.write;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	
	public static void main(String[] args){
		String s = "lanweikang,\\\"蓝伟康";
//		writeToFile(s,"C:/Users/Administrator/Desktop/sk/log/log.txt");
		System.out.println(s);
	}
}
