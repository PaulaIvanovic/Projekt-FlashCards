package databaseInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class PullFrom {
	
	public static void main(String []args) {
		 String url = "jdbc:mysql://localhost:3306/flashcards";
		 Connection connection = null;
		 Statement st = null; //korististi Prepared statement za izbjegavanje sql injectiona (login,reg,edit etc.)
		 String query = "SELECT * from user";
		 ResultSet rs = null;
		 ResultSetMetaData rsmd = null;
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				 connection = DriverManager.getConnection(url);
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
			 st = connection.createStatement(); //izvrsavanje upita
			 rs =  st.executeQuery(query);
			 rsmd = rs.getMetaData();
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
		 
		 
		 
		 try {
			 rs.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
