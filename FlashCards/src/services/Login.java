package services;


import java.sql.SQLException;
import databaseInfo.PullFrom;
import databaseInfo.UserInfo;

public class Login  {
	
	
 public static Boolean loginValidation(String userName, String pw) {
	 Boolean flag = false;
	 String pass = null;
	
	 //PullFrom p = new PullFrom("user","password", pw);
	 PullFrom u = new PullFrom("user","username", userName);
	 // id pass u.getString("password")
	 try {
	        if (u.rs.next()) {
	            pass = u.rs.getString("password");
	            if (pw.equals(pass)) {
	                flag = true;
	                UserInfo collect = new UserInfo(userName, false);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return flag;
  }
}
