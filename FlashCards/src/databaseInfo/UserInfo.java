package databaseInfo;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;

import views.GlobalDesign;

public class UserInfo implements GlobalDesign{
	public static String userID;
	
	public static ArrayList<Color> groupColors;
	public static ArrayList<String> groupNames;
	public static ArrayList<Color> subGroupColors;
	public static ArrayList<String> subGroupNames;
	public static Crude crude;
	
	public UserInfo(String username, boolean newUser) {
		 crude = new Crude();
		 groupColors = new ArrayList<>();
		 groupNames = new ArrayList<>();
		 subGroupColors = new ArrayList<>();
		 subGroupNames = new ArrayList<>();
		 
		 //get user info
		 PullFrom u = new PullFrom("user","username", username);
		 
		 //get user ID
		 try {
			 if (u.rs.next()) {
				 userID = u.rs.getString("iduser");
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 if(newUser) {
			 defaultGroup();
		 }
		 getGroups();
	}
	
	
	public void defaultGroup() {
		//create default group once user is registered first time
		crude.create("grupa", defaultGroupName, defaultGroupColor, userID);
	}
	
	public static void getGroups() {
		groupNames.clear();
		groupColors.clear();
		PullFrom groupInfo = new PullFrom("grupa","iduser", userID);
		try {
			 while(groupInfo.rs.next()) {
				 groupNames.add(groupInfo.rs.getString("name"));
				 groupColors.add(Color.decode(groupInfo.rs.getString("boja")));
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getSubgroups() {
		
	}


	public static void addGroup(String name, String chosenColor) {
		System.out.println(name + " " + chosenColor);
		try {
			crude.create("grupa", name, chosenColor, userID);
			getGroups();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
