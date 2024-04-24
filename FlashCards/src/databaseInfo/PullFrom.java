package databaseInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSetMetaData;


public class PullFrom {
	 Connection connection = null;
	 Statement st = null; //use Prepared statement for SQLInjection defence
	 String query = "SELECT * from ";
	 ResultSet rs = null;
	 
	//constructor to get the table from SQL base
	public  PullFrom(String table) {
		connect();
		query += table;
		fetchData();
	}
	
	//constructor to get the row of the table in SQL
	public  PullFrom(String table, String element, String elementValue) {
		connect();
		query += table + " WHERE " + element + " = " + elementValue;
		fetchData();	
	}
	
	public void connect() {
		//connecting to the base
		String url = "jdbc:mysql://localhost:3306/flashcards";
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				 connection = DriverManager.getConnection(url, "root", "root");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//fetching data from SQL base
	public void fetchData() {
		 try { 
			 st = connection.createStatement();
			 rs =  st.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	}
	
	public ResultSet returnRs() {
		return rs;
	}
	
	//this need to be called every time we use PullFrom
	//closing the rs connection
	public void close() {
		try {
			rs.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	/*public static void main(String args[]) {
		PullFrom p = new PullFrom("grupa","iduser", "2");
		p.returnRs();
		p.close();
	 }
	*/
}