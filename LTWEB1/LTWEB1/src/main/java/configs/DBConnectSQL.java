package configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnectSQL {
	private final String serverName = "DESKTOP-RN4NHQ2\\SQLEXPRESS";
	private final String dbName = "Tuan1";
	private final String portNumber = "1433";
	private final String instance = "";
	private final String userID = "sa";
	private final String password = "0907935456";

	public Connection getConnection() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber + ";databaseName=" + dbName;
		if (instance == null || instance.trim().isEmpty()) {
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
		}
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);
	}

	public static void main(String[] args) {
		DBConnectSQL DB = new DBConnectSQL();
		try {
			Connection conn = DB.getConnection();
			Statement stmt = conn.createStatement();
			// insert ‘GiaoVien'
			// get data from table ‘GiaoVien'
			ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
			// show data
			while (rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
						+ rs.getString(4) + " " + rs.getBoolean(5) + " " + rs.getDate(6));
			}
			conn.close(); // close connection
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}