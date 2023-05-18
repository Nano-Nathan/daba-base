package lib;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
	private Connection c;
	private PreparedStatement stmt;

	public DBManager () {
		try{
			Class.forName("org.postgresql.Driver");     
		} catch(ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	//Logic with database
	private void connectAndPrepare(String query){
		try {
			//Connect to database
			c = DriverManager.getConnection(PasswordManagerLocal.URL, PasswordManagerLocal.user, PasswordManagerLocal.pass);
			//Create statement
			stmt = c.prepareStatement(query);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	private Object execute (String type, String query, Object[] params) {
		Object result = null;
		connectAndPrepare(query);
		_insertParams(params);
		switch (type) {
			case "SELECT":
				result = resultSELECT();
				break;
			case "UPDATE":
				result = resultUPDATE();
				break;
			case "INSERT":
				result = resultINSERT();
				break;
			default:
				break;
		}
		closeConnections();
		return result;
	}
	private void _insertParams (Object[] params) {
		try {
			for (int i = 0; i < params.length; i++) {
				stmt.setObject(i + 1, params[i]);
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	private void closeConnections() {
		try {
			c.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	

	//Internal logic query
	private ArrayList<Object[]> resultSELECT(){
		//ArrayList of results
		ArrayList<Object[]> results = new ArrayList<>();
		try {
			//Execute query
			ResultSet rs = stmt.executeQuery();
	
			//Get table metadata
			ResultSetMetaData metadata = rs.getMetaData();
			int numColumns = metadata.getColumnCount();
	
			//Add header
			String[] columnNames = new String[numColumns];
			for (int i = 1; i <= numColumns; i++) {
				columnNames[i - 1] = metadata.getColumnName(i);
			}
			results.add(columnNames);
	
			//Parse data
			while (rs.next()) {
				Object[] row = new Object[numColumns];
				for (int i = 1; i <= numColumns; i++) {
					row[i - 1] = rs.getObject(i);
				}
				results.add(row);
			}
			
			//Close result
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		return results;
	}
	private int resultUPDATE () {
		try {
			return stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		return 0;
	}
	private int resultINSERT () {
		try {
			return stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	//Excecute SELECT
	public ArrayList<Object[]> executeSELECT (String query) {
		return (ArrayList<Object[]>) execute("SELECT", query, new Object[] {});
	}
	//Excecute UPDATE
	public boolean executeUPDATE (String query, Object... params) {
		return (boolean) execute("UPDATE", query, params);
	}
	//Excecute INSERT
	public int executeINSERT (String query, Object... params) {
		return (int) execute("INSERT", query, params);
	}
}
