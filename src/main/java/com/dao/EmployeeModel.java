package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Employee;
import com.bean.Position;


public class EmployeeModel {
	private String jdbcURL = "jdbc:mysql://localhost:3306/n0622?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Mai@12012000";

	private static final String SELECT_ALL_EMPS = "select * from employee,position where employee.position_id=position.id";
	private static final String SELECT_ALL_POS = "select * from position";
	private static final String INSERT_EMPS_SQL = "INSERT employee (emp_code,name,birthday,address,gender,position_id) values\n"
												+ "('?','?','?','?','?','?'),";
	private static final String DELETE_EMP_SQL = "delete from employee where id = ?;";
	private static final String SELECT_EMP_BY_ID = "select * from employee where id =?";
	
	
	public EmployeeModel() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public List<Employee> getListEmployee() {

		List<Employee> emps = new ArrayList<>();
		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPS);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String code = rs.getString("emp_code");
				String name = rs.getString("employee.name");
				Date birthday = rs.getDate("birthday");
				String address = rs.getString("address");
				String gender = rs.getString("gender");
				String position = rs.getString("position.name");
				
				String status = rs.getString("status");
				emps.add(new Employee(id, code, name, birthday, address, gender, position, status));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return emps;
	}
	
	public List<Position> getListPosition() {

		List<Position> pos = new ArrayList<>();
		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_POS);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String status = rs.getString("status");
				pos.add(new Position(id, name, status));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return pos;
	}
	
	public void addEmployee(Employee emp) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPS_SQL)) {
			preparedStatement.setString(1, emp.getCode());
			preparedStatement.setString(2, emp.getName());
			preparedStatement.setDate(3, (Date) emp.getBirthday());
			preparedStatement.setString(4, emp.getAddress());
			preparedStatement.setString(5, emp.getGender());
			preparedStatement.setString(6, emp.getPosition());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public boolean removeEmployee(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_EMP_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public Employee selectEmployee(int id) {
		Employee emp = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMP_BY_ID);) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String code = rs.getString("code");
				String name = rs.getString("name");
				Date birthday = rs.getDate("birthday");
				String address = rs.getString("address");
				String gender = rs.getString("gender");
				String position = rs.getString("position");
				String status = rs.getString("status");
				emp = new Employee(id, code, name, birthday, address,gender,position,status);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return emp;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
