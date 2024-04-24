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
			//PW ENCRYPTION !!
			
			this.query = "INSERT INTO user (username, email, password, profilePic) " +
	                "VALUES ('" + d1 + "', '" + d2 + "', md5('" + d3 + "'), '" + d4 + "');";
			
		}
		if(tableName.equals("card")) {
			
			this.query = "INSERT INTO card (title, paragraph, boja, idsubgroup) " +
	                "VALUES ('" + d1 + "', '" + d2 + "', '" + d3 + "', '" + d4 + "');";
		}
		
		try {
			 st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			this.query = "INSERT INTO grupa (name, boja, iduser) " +
	                "VALUES ('" + d1 + "', '" + d2 + "', '" + d3 + "');";
		
		}
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void delete(String tableName, String columnName, String d1) { 
		 this.query = "DELETE FROM " + tableName + " WHERE " + columnName + "= '" + d1 +"';";
	    try {
	        st.executeUpdate(query);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public void update(String tableName, String columnName, String before, String after) { 
		if(tableName.equals("user") && columnName.equals("password")) {
			before = Help.encrypt(before);
			after = Help.encrypt(after);
		}
	    this.query = "UPDATE " + tableName + " SET " + columnName + " = '" + after + "' WHERE " + columnName + " = '" + before + "';";
			try {
		        st.executeUpdate(query);
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
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
