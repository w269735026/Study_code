package com.wwl.annotation.getName.entity;

import com.wwl.annotation.getName.GetName;
import com.wwl.annotation.getName.processor.GetNameProcessor;

public class A extends GetNameProcessor{
	@GetName
	public void a(){
		System.out.println(this.getId());
		System.out.println(this.getName());
	}
}
