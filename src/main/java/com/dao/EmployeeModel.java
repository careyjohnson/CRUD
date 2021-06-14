package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Employee;
import com.bean.Position;
import com.helper.ConnectionUtils;


public class EmployeeModel {

	private static final String SELECT_ALL_EMPS = "select employee.id,emp_code,employee.name,birthday,address,gender,position.name,employee.status "
												+ "from employee\n"
												+ "inner join position on employee.id=position.id;";
	private static final String INSERT_EMPS_SQL = "INSERT INTO employee (emp_code,name,birthday,address,gender,position_id) values\n"
												+ "('?','?','?','?','?','?'),";
	private static final String DELETE_EMP_SQL = "delete from employee where id = ?;";
	private static final String SELECT_EMP_BY_ID = "select employee.id,emp_code,employee.name,birthday,address,gender,position.name,employee.status \n"
												+ "from employee\n"
												+ "inner join position on employee.id=position.id\n"
												+ "where employee.id=?;";
	private static final String UPDATE_USERS_SQL = "update employee set emp_code=?,name=?,birthday=?,address=?,gender=?,position_id=? "
												+ "where id=?;";
	
	public EmployeeModel() {
	}


	public int addEmployee(Employee emp) throws SQLException {
		try (Connection connection = ConnectionUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPS_SQL)) {
			preparedStatement.setString(1, emp.getCode());
			preparedStatement.setString(2, emp.getName());
			preparedStatement.setDate(3, (Date) emp.getBirthday());
			preparedStatement.setString(4, emp.getAddress());
			preparedStatement.setString(5, emp.getGender());
			preparedStatement.setInt(6, emp.getPositionId().getId());
			
			if(preparedStatement.executeUpdate()>0) {
				return 1;
			}
			//preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return -1;
	}
	
	public List<Employee> getListEmployee() {

		List<Employee> emps = new ArrayList<>();
		try (Connection connection = ConnectionUtils.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPS);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt(1));
				emp.setCode(rs.getString(2));
				emp.setName(rs.getString(3));
				emp.setBirthday(rs.getDate(4));
				emp.setAddress(rs.getString(5));
				emp.setGender(rs.getString(6));
				emp.setPositionId(new Position(0,rs.getString(7), null));
				emp.setStatus(rs.getString(8));

				emps.add(emp);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return emps;
	}
	
	
	public boolean removeEmployee(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = ConnectionUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_EMP_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public Employee getEmployeeById(int id) {
		Employee emp = new Employee();
		try (Connection connection = ConnectionUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMP_BY_ID);) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				emp.setId(rs.getInt(1));
				emp.setCode(rs.getString(2));
				emp.setName(rs.getString(3));
				emp.setBirthday(rs.getDate(4));
				emp.setAddress(rs.getString(5));
				emp.setGender(rs.getString(6));
				emp.setPositionId(new Position(0,rs.getString(7), null));
				emp.setStatus(rs.getString(8));

				return emp;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return null;
	}
	
	public boolean editEmployee(Employee emp) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = ConnectionUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, emp.getCode());
			statement.setString(2, emp.getName());
			statement.setDate(3, (Date) emp.getBirthday());
			statement.setString(4, emp.getAddress());
			statement.setString(5, emp.getGender());
			statement.setInt(6, emp.getPositionId().getId());
			statement.setInt(7, emp.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
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
