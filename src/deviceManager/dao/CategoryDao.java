package deviceManager.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import deviceManager.model.Category;
import deviceManager.util.JDBCConnection;

public class CategoryDao {

	public List<Category> getAll() {

		Connection connection = JDBCConnection.getConnection();
		List<Category> categories = new ArrayList<Category>();
		String sql = "select * from category";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Category category = new Category(rs.getInt(1), rs.getString(2));
				categories.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return categories;
	}

}
