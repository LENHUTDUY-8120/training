package deviceManager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

	private static final String url = "jdbc:postgresql://localhost/device-manager";
	private static final String user = "postgres";
	private static final String password = "8120";
	private static Connection conn;

	public static Connection getConnection() {

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
