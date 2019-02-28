package com.wwl.porxy;

public class Test {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		B b = new B(A.class,"a");//传入实现类和方法名
		InterfaceA a=(InterfaceA) b.method();  //接口接收
		a.a();
	}
}
