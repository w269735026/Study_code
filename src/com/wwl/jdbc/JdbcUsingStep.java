package com.wwl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class JdbcUsingStep {
	public static void main(String[] args) {
		String url="jdbc:mysql:///wwl";
		String user="root";
		String password="123456";
		//1、2步在方法中
		Connection connection = getConnection(url, user, password);
		try {
			//3、把sql语句发送给mysql来执行语句
			PreparedStatement prepareStatement = connection.prepareStatement("select * from user where user_id=?");
			//4、防sql注入参数
			prepareStatement.setString(1, "35");
			//5、是查询语句所以返回resultSet
			ResultSet resultSet = prepareStatement.executeQuery();
			if(resultSet.next()) {
				//6、获取参数并打印
				System.out.println(resultSet.getString("user_phone"));
			}
			//7、关闭资源
			resultSet.close();
			prepareStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(connection);
	}
	//获取connection
	public static Connection getConnection(String url,String user,String password) {
		try {
			//1、加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//Driver d=new Driver();直接这样创建对象也行：不懂看源码：有静态代码块直接加载或者看资源网络文章：https://www.cnblogs.com/visec479/p/3909246.html
			//2、获取连接
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
