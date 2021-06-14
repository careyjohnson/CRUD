package com.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtils {
	public static Connection getConnection() throws SQLException {
		String hostName="localhost";
		String databaseName="n0622";
		String userName="root";
		String password="Mai@12012000";
		return getMysqlConnection(hostName,databaseName, userName,password);
		
	}
	public static Connection getMysqlConnection(String hostName, String databaseName, String userName,
			String password) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String connectionURL="jdbc:mysql://"+hostName+":3306/"+databaseName;
		Connection conn=DriverManager.getConnection(connectionURL,userName,password);
		return conn;
	}
	public  void executeSQL(String sql) throws Exception{
		Connection connect =getConnection();
		Statement stmt = connect.createStatement();
		stmt.executeUpdate(sql);
	}
}
