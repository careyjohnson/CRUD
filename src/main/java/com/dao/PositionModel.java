package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Position;
import com.helper.ConnectionUtils;

public class PositionModel {
	private static final String SELECT_ALL_POS = "select * from position";
	public List<Position> getListPosition() {

		List<Position> pos = new ArrayList<>();
		try (Connection connection = ConnectionUtils.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_POS);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String status = rs.getString("status");
				pos.add(new Position(id, name, status));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pos;
	}
}
