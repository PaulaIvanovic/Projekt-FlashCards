package databaseInfo;

public class PullFrom {
	package flashCards;
	import java.security.NoSuchAlgorithmException;  
	import java.security.MessageDigest;  
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.sql.ResultSetMetaData;


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
				 rsmd = rs.getMetaData(); //pomoc kod dohvata retka, stupca...
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
			 
			 
		    /* Plain-text password initialization. */  
	        String password = "myPassword";  
	        String encryptedpassword = null;  
	        try   
	        {  
	            /* MessageDigest instance for MD5. */  
	            MessageDigest m = MessageDigest.getInstance("MD5");  
	              
	            /* Add plain-text password bytes to digest using MD5 update() method. */  
	            m.update(password.getBytes());  
	              
	            /* Convert the hash value into bytes */   
	            byte[] bytes = m.digest();  
	              
	            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
	            StringBuilder s = new StringBuilder();  
	            for(int i=0; i< bytes.length ;i++)  
	            {  
	                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
	            }  
	              
	            /* Complete hashed password in hexadecimal format */  
	            encryptedpassword = s.toString();  
	        }   
	        catch (NoSuchAlgorithmException e)   
	        {  
	            e.printStackTrace();  
	        }  
	          
	        /* Display the unencrypted and encrypted passwords. */  
	        System.out.println("Plain-text password: " + password);  
	        System.out.println("Encrypted password using MD5: " + encryptedpassword);  
			
			 
			 try {
				 rs.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}

}
