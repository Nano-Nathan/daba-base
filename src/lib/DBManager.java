package lib;

import java.sql.*;
public class DBManager {
	private final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private final String user = "postgres";
	private final String pass = "cs250900";
	public DBManager () {
		try{
			Class.forName("org.postgresql.Driver");     
		} catch(ClassNotFoundException e) {
			System.out.println(e);
		}
	}
	private Connection getConnection () {
		Connection c = null;
		try {
			//Get connection
			c = DriverManager.getConnection(URL, user, pass);
		} catch (Exception e) {
			System.out.println(e);
		}
		return c;
	}
	public void test (){
		Connection c = getConnection();
		try {
			c.close();			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
