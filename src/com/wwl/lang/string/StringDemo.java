package com.wwl.lang.string;

public class StringDemo {
	public static void main(String[] args) {
		
		String str="物料拦截";
		String str1="abcd";
		int a=str.indexOf("物料拦截");
		int indexLas=str.lastIndexOf("物料拦截");
		if(indexLas>-1) {
			System.out.println(a);
		}
		
		String str2="a.java";
		boolean b = str2.endsWith(".java");
		if(b) {
			System.out.println("它是true");
		}
		String ends="A.java";
		System.out.println(ends.endsWith("java"));
	}
}
