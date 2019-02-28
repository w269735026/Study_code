package com.wwl.reflect;

public class A {
	Integer id;
	String name;
	String value;
	
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	//构造方法
	//public Reflect() {}//无参
	
	public A(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public A(Integer id, String name, String value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}
	
	//method方法
	public void a(){
		System.out.println("调用了无参无返回值的方法a()");
	}
	
	//带参数method方法
	public void a(int id){
		System.out.println("调用了带参书id无返回值的方法a(int id)：id值是："+id);	
	}
	//有返回值无参数method方法
	public String b(){
		System.out.println("调用了无参、有返回值的方法b()");
		return "学习reflect反射技术";
	}
	//有返回值有参数method方法
	public String b(String name){
		System.out.println("调用了带参数name有返回值的方法b(String name)：name值是："+name);
		return name;
	}
}
