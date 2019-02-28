package com.wwl.annotation.jdbc.util;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.wwl.annotation.jdbc.JDBCInfo;

public class JdbcUtil {
	@JDBCInfo   //此注解属性全部都有默认的数据
	public static Connection getConnection() throws NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException{
		//第一步 获取有注解类的字节码文件
		Class clazz=JdbcUtil.class;
		
		//第二步 获取此类中的单个方法名：参数：方法名，形参：没有可以null也可以不用写
		Method method=clazz.getMethod("getConnection", null);
		
		//第三步 判断方法上是否有jdbcinfo注解(反射中的构造对象、属性字段对象、方法对象都有共同的获取注解的方法)
		if(method.isAnnotationPresent(JDBCInfo.class)){
			JDBCInfo jdbc=method.getAnnotation(JDBCInfo.class);
		//第四步 获取注解中的四个属性
			String url=jdbc.url();
			String driver=jdbc.driverClass();
			String password=jdbc.password();
			String user= jdbc.user();
		
		//第五步  注册驱动
			Class.forName(driver);
		//第六步 获取连接
			return DriverManager.getConnection(url, user, password);
		}
		return null;
	}
	
}
