package com.wwl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * jdbc事务步骤
 * @author Administrator
 *
 */
public class TransactionStep {
	
	public static void main(String[] args) throws ClassNotFoundException {
		String url="jdbc:mysql:///wwl";
		String user="root";
		String password="123456";
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			//1、开启手动事务的关键是con.setAutoCommit(false)，JDBC事务默认是开启的，并且是自动提交
			connection.setAutoCommit(false);//关闭自动提交
			prepareStatement = connection.prepareStatement("insert into test(test_id,test_name) values(?,?)");
			prepareStatement.setString(1, "4");
			prepareStatement.setString(2, "sdfsd");
			int i = prepareStatement.executeUpdate();
			//int ii=10/0;	//抛出异常 让其
		} catch (Exception e) {
			e.printStackTrace();
			try {
				//2、出现异常进行回滚
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		try {
			//3、关闭了自动自交所以必须最后要提交操作
			connection.commit();
			prepareStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
