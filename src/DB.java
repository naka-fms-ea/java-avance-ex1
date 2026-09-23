package fr.lndr.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	
	private static Connection conn = null;
		
		public static Connection getConnection() {
			
			try {
				Class.forName("org.mariadb.jdbc.Driver");	
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			if (conn == null) {
				try {
					String url = "jdbc:mariadb://localhost:3306/shop";
					String login = "root";
					String password = "";
					conn = DriverManager.getConnection(url, login, password);
				}
				catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
			}
			return conn;
		}
		
		public static void closeConnection() {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
			}
		}
		
		public static void closeStatement(Statement st) {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
			}
		}
		
		public static void closeResultSet(ResultSet rs) {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
			}
		}
}
