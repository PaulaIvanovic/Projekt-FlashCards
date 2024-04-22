package databaseInfo;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;



public class Crude {
	
	 String url = "jdbc:mysql://localhost:3306/fc";
	 Connection connection = null;
	 Statement st = null; //use Prepared statement for SQLInjection defense
	 String query = null;
	 ResultSet rs = null;
	 ResultSetMetaData rsmd = null;
	 
	 
	 public Crude() {
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 connection = DriverManager.getConnection(url);
			 st = connection.createStatement();
		 } catch (ClassNotFoundException | SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
	 }

	 //create user normalno, sto ako je boja null? 
	public void create(String tableName, String d1, String d2, String d3, String d4) {  //
		 
		if(tableName.equals("user")) {
			//d1 je username, d2 je mail, d3 pw
			this.query = "INSERT INTO user (username, email, password, profilePic) " +
	                "VALUES ('" + d1 + "', '" + d2 + "', '" + d3 + "', '" + d4 + "');";
			try {
				 st.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			
		}
		if(tableName.equals("card")) {
			
			this.query = "INSERT INTO user (title, paragraph, idsubgroup, boja) " +
	                "VALUES ('" + d1 + "', '" + d2 + "', '" + d3 + "', '" + d4 + "');";
			 
			
		}
	
	}
	
	public void create(String tableName, String d1, String d2, String d3) { 
	//subgroup name boja idgruop
		
		
		if(tableName.equals("subgroup")) {
			//d1 je username, d2 je mail, d3 pw
			this.query = "INSERT INTO subgroup (name, boja, idgroup) " +
	                "VALUES ('" + d1 + "', '" + d2 + "', '" + d3 + "');"; //kartica mora pripadati nekoj grupi
		}
		
	//group name iduser boja
		if(tableName.equals("grupa")) {
			//d1 je username, d2 je mail, d3 pw
			if(d2 == null) {this.query = "INSERT INTO grupa (name,boja) " + //ako kartica ne pripada nijednom uzeru
	                "VALUES ('" + d1 + "', '" + d2 + "', '" + d3 + "');";}
			this.query = "INSERT INTO grupa (name, iduser, boja) " +
	                "VALUES ('" + d1 + "', '" + d2 + "', '" + d3 + "');";
		}
	}
	
	
	public void delete(String tableName, String d1) { 
		
	}
	
	
	public void update(String tableName, String before, String after) { 
		
	}
	
	public void closeConnection() {
		try {
			this.rs.close();
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
