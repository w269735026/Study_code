package com.wwl.annotation.getName.processor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.wwl.annotation.getName.GetName;

public class GetNameProcessor {
	//第一种方法：继承此类、或者属性变静态用类去调用，，web中可以存放到request域中
	private static int id;
	private static  String value;
	private static String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public <T>T processor(Class<T> T) throws Exception{   //第一步 需要被注解的类传入
		T t=T.newInstance();
		Method[] method=T.getDeclaredMethods();             //第二步  获取传入的类的所有方法
		for(Method m : method){
		if(m.isAnnotationPresent(GetName.class)){            //第三步  判断方法中有没有标记GetName注解
			GetName getName=m.getAnnotation(GetName.class);       //第四步  得到注解对象，获取注解中的值
			 id =getName.id();
			 value =getName.value();
			 name =getName.name();
		}
	}
		
		return t;
	}
}
