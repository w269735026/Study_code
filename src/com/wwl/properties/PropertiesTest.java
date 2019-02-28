package com.wwl.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesTest {
	public static void main(String[] args) {
		try {
		Properties p = new Properties();
		//从类根目录开始找
		InputStream is=PropertiesTest.class.getResourceAsStream("/com/wwl/properties/xx.properties");
		p.load(is);
		String str=p.getProperty("hello");
		System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("路径有问题");
		} catch(NullPointerException e){
			System.out.println("处理空指针问题");
		}
	}
	
}
