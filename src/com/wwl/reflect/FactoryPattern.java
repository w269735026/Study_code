package com.wwl.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 工厂模式：通过properties中的value获取对象实例
 * @author Administrator
 *
 */
public class FactoryPattern {
	public static void main(String[] args) {
		//通过properties获取类的全路径
		String string = getPropertiesValue("reflect_User");
		
		//获取对象User：使用类的全路径名称创建对象
		//可扩展：创建一个接口类(自定义的类要实现此接)：用于进行判断类型：instanceof
		User user = (User) newInstance(string);
		user.setId(1);
		user.setName("你好");
		System.out.println(user.toString());
	}
	
	/**
	 * 通过properties配置文件中key获取value
	 * @param key 
	 * @return 
	 */
	public static String getPropertiesValue(String key) {
		String path = FactoryPattern.class.getResource("Class33.properties").getPath();
		Properties p=new Properties();
		try {
			p.load(new FileInputStream(new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p.getProperty(key);
	}
	
	/**
	 * 工厂模式创建实例
	 * @param className
	 * @return
	 */
	public static Object newInstance(String className){
		try {
			Class forName = Class.forName(className);
			return forName.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

class User{
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
	
	
}