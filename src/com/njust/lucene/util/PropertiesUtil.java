package com.njust.lucene.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;


public class PropertiesUtil {
	private static Properties properties=null;
	
	
	static{
		try {
			properties=new Properties();
			File file = new File(PropertiesUtil.class.getResource("/").getPath());
			File[] fs=file.listFiles();
			for (File f : fs) {
				if(f.isFile()&&f.getName().endsWith("properties")){
					properties.load(new FileInputStream(f));
				}
			}
			System.out.println("properties start reading...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String key){
		return properties.getProperty(key);
	}
	public  static String get(String file, String key){
		ResourceBundle resource = ResourceBundle.getBundle(file);
		String value = resource.getString(key);
		return value;
	}

//	public static void main(String[] args) {
//		System.out.println(PropertiesUtil.get("indexDir","indexDir"));
//	}

}
