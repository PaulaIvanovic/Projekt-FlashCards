package services;

import java.sql.SQLException;

import databaseInfo.Crude;
import databaseInfo.PullFrom;

public class Register {
	String username;
	String pw;
	String email;
	String profilePic = "default.jpg"; //treba rjesit pristup basic slici
	
	public Register(String username, String pw, String email){
		this.username = username;
		this.pw = pw;
		this.email = email;
	}
	
	Crude crude = new Crude();
	PullFrom p = new PullFrom("user", "username", username);
	PullFrom e = new PullFrom("user", "email", email);
	
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
	public void AddUser() {
		if(checkDuplicate()) {
			System.out.println("This usrename already exists");
		}else {
			//adding
			crude.create("user", username, email, pw, profilePic);
		}
	}
}
