package lib;

import java.sql.*;
public class DBManager {
	private final PasswordManager p = new PasswordManager();
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
			c = DriverManager.getConnection(p.URL, p.user, p.pass);
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
