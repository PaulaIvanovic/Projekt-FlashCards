package services;

import java.sql.SQLException;

import databaseInfo.PasswordEncryption;
import databaseInfo.Crude;
import databaseInfo.PullFrom;
import databaseInfo.UserInfo;

public class Register {
	String username;
	String pw;
	String email;
	String profilePic = "default.jpg"; //need to access the basic picture
	public String message = "";
	Crude crude;
	PullFrom p;
	PullFrom e;
	String EncryptedPw = "";	
	
	public Register(String username, String pw, String email){
		this.username = username;
		this.pw = pw;
		this.email = email;
		crude = new Crude();
		p = new PullFrom("user", "username", username);
		//p.userCheck(username);
		e = new PullFrom("user", "email", email);
	}
	
	
	//checking for duplicate username
	public boolean checkDuplicate() {
		try {
			if(p.rs.next() || e.rs.next()) {
				return true;
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//adding users to the base
	public String AddUser() {
		//checking if fields are empty
		if(username.equals("Enter your username") || pw.equals("Enter your password") || email.equals("Enter your email")) {
			message = "One or more fields are empty.";
		}else {
			//checking for duplicate username
			if(checkDuplicate()) {
				//System.out.println("This username already exists");
				message = "Account with this username/email already exists.";
			}else if(username.contains(" ") || username.length() > 20){ //checking username conditions
				message = "Invalid username.";
			}else{
				//System.out.println(checkDuplicate());
				//checking password conditions
				if(pw.equals("Enter your password")){
					message = "Password is required.";
				}else if(!pw.matches(".*[A-Z].*") || !pw.matches(".*[a-z].*") || !pw.matches(".*\\d.*") || pw.length() < 8){
					message = "<html>Password must be at least 8 characters long and include:<br> an uppercase letter, a lowercase letter and a numeral</html>";
				}else if(!email.contains("@")) { //checking email conditions
					message = "Invalid e-mail.";
				}else{
					//username, password and email are valid, adding to the base
					EncryptedPw = PasswordEncryption.encrypt(pw);
					crude.create("user", username, email, pw, profilePic);
					//System.out.println(EncryptedPw);
					message = "Registration completed.";
					UserInfo collect = new UserInfo(username, true);
				}
			}
		}
		return message;
	}
}
