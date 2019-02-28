package com;

public class Test {
	public static void main(String[] args) {
	/*	//一般都是0b开头后尾是1和0的都是二进制
		System.out.println(Integer.toBinaryString(10));//打印 1010
		//一般都是0o开头都是八进制
		System.out.println(Integer.toOctalString(10));//打印 12
		//十六进制：一般都是0x开头
		System.out.println(Integer.toHexString(10));//打印a
		System.out.println(Integer.parseInt("a", 16));*/
		int i=5;
		int a=i++ + ++i;
		System.out.println(a);//打印 12 
		System.out.println(i);//打印7
		
		//上面语法其实就是下面：
		int c=5;
		c=c+1;
		int a1=c + c;
		c=c+1;
		System.out.println(a1);
		System.out.println(c);//打印7
	}
}
