package databaseInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

/*
 * u buildpath mora bit mysql connector.jar skinut s neta i stavljen u tomcat - lib folder
 * https://dev.mysql.com/downloads/connector/j/
 * */
//

public class PullFrom {
	
	public static void main(String []args) {
		 String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11700330";
		 Connection connection = null;
		 Statement st = null; //use Prepared statement for SQLInjection defense
		 String query = "SELECT * from user";
		 ResultSet rs = null;
		 ResultSetMetaData rsmd = null;
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				 connection = DriverManager.getConnection(url, "sql11700330", "ugqkTnHrnj");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Integer empId = -1;
		 String firstName = null;
		 try {
			 st = connection.createStatement();
			 rs =  st.executeQuery(query);
			 rsmd = rs.getMetaData(); //extra help with manipulating data 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //manipuliraj podacima
		try {
			while(rs.next()) {
			System.out.println("id: " + rs.getInt("iduser") +"\n"+
			"ime: "+ rs.getString("username")+"\n"+"email: "+ rs.getString("email")+'\n');
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		/* 
		public ResultSet users() {
			
		}
		
		public ResultSet cards() {
			
		}
		public ResultSet groups() {
			
		}
		public ResultSet subgroups() {
			
		}*/
		
		
		
		 
		 try {
			 rs.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}