package com.wwl.annotation.jdbc.test;

import com.wwl.annotation.jdbc.util.JdbcUtil;

public class Test {
	public static void main(String[] args) throws Exception{
		//测试连接操作
		System.out.println(JdbcUtil.getConnection());
	}

}
