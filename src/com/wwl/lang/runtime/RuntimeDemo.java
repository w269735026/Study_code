package com.wwl.lang.runtime;

import java.io.IOException;

public class RuntimeDemo {
	public static void main(String[] args) {
		//使用单例Runtime类让windows定时关机
		Runtime runtime = Runtime.getRuntime();
		try {
			//runtime.exec("shutdown -s -t 600");
			runtime.exec("shutdown -a");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
