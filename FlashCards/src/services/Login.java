package services;

import java.sql.SQLException;

import databaseInfo.PullFrom;

public class Login  {
	
	
 public static Boolean loginValidation(String userName, String pw) {
	 Boolean flag = true;
	
	 PullFrom p = new PullFrom("user","password", pw);
	 PullFrom u = new PullFrom("user","username", userName);
	 
	 try {
		if(!p.rs.next())
			flag = false; //pw je zauzet
		if(!u.rs.next())
			flag = false; //u je zauzet
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return flag;
 }
	
}
