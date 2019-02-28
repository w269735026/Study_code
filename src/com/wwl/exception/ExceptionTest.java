package com.wwl.exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.wwl.properties.PropertiesTest;

public class ExceptionTest {
	public static void main(String[] args) {
		try {
		Properties p = new Properties();
		//从类根目录上找
		InputStream is=PropertiesTest.class.getResourceAsStream("com/wwl/properties/xx.properties");
		p.load(is);
		String str=p.getProperty("hello");
		System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("路径有问题");
		} catch(NullPointerException e){
			System.out.println("处理空指针问题，下面所有代码还会继续执行下去");
		}finally{
			System.out.println("没有对空指针问题进行处理，只会执行这句话，下面代码不会执行下去");
		}
		System.out.println("try块catch的以外代码会执行");
		
		
		
	}
}
