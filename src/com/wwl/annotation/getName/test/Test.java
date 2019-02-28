package com.wwl.annotation.getName.test;


import com.wwl.annotation.getName.entity.A;
import com.wwl.annotation.getName.processor.GetNameProcessor;

public class Test {
	public static void main(String[] args) throws Exception {
		GetNameProcessor a = new GetNameProcessor();
		A c= (A) a.processor(A.class);
		
		c.a();
	}
}
