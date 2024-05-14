package databaseInfo;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;

import views.GlobalDesign;

public class UserInfo implements GlobalDesign{
	public static String userID;
	public static String groupID;
	
	public static ArrayList<Color> groupColors;
	public static ArrayList<String> groupNames;
	public static ArrayList<String> groupIDs;
	public static ArrayList<Color> subGroupColors;
	public static ArrayList<String> subGroupNames;
	public static Crude crude;
	
	public UserInfo(String username, boolean newUser) {
		 crude = new Crude();
		 groupColors = new ArrayList<>();
		 groupNames = new ArrayList<>();
		 groupIDs = new ArrayList<>();
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
		groupIDs.clear();
		PullFrom groupInfo = new PullFrom("grupa","iduser", userID);
		try {
			 while(groupInfo.rs.next()) {
				 groupNames.add(groupInfo.rs.getString("name"));
				 groupColors.add(Color.decode(groupInfo.rs.getString("boja")));
				 groupIDs.add(groupInfo.rs.getString("idgroup"));
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void getSubgroups() {
		subGroupNames.clear();
		subGroupColors.clear();
		PullFrom subgroupInfo = new PullFrom("subgroup","idgroup", groupID);
		try {
			 while(subgroupInfo.rs.next()) {
				 subGroupNames.add(subgroupInfo.rs.getString("name"));
				 subGroupColors.add(Color.decode(subgroupInfo.rs.getString("boja")));
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static void addGroup(String name, String chosenColor) {
		try {
			crude.create("grupa", name, chosenColor, userID);
			getGroups();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addsubGroup(String name, String chosenColor, String group) {
		try {
			crude.create("subgroup", name, chosenColor, groupID);
			getSubgroups();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getGroupID(String name) {
		//get group
		 PullFrom group = new PullFrom("grupa","name", name);
		
		 //get group ID
		 try {
			 while (group.rs.next()) {
				 if(group.rs.getString("iduser").equals(userID)) {
					 groupID = group.rs.getString("idgroup"); 
				 }
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static void changeGroupColor(String grID, Color newColor) {
		int index = groupIDs.indexOf(grID);
		if(index > -1) {
			groupColors.set(index, newColor);	
		}
	}


	public static void changeGroupName(String grID, String newName) {
		int index = groupIDs.indexOf(grID);
		if(index > -1) {
			groupNames.set(index, newName);	
		}
	}
	
	public static void saveEditAllGroups() {
		for(int i = 0; i < groupIDs.size(); i++) {
			int red = groupColors.get(i).getRed();
	        int green = groupColors.get(i).getGreen();
	        int blue = groupColors.get(i).getBlue();
	        // Convert the RGB values to hexadecimal format
	        String newColor = String.format("0x%02X%02X%02X", red, green, blue);
	        
			crude.update("grupa", "boja", "idgroup", groupIDs.get(i), newColor);
			crude.update("grupa", "name", "idgroup", groupIDs.get(i), groupNames.get(i));
		}
	}
	
	public static void saveEditGroup(String grID) {
		int index = groupIDs.indexOf(grID);
		if(index > -1) {
			int red = groupColors.get(index).getRed();
	        int green = groupColors.get(index).getGreen();
	        int blue = groupColors.get(index).getBlue();
	        // Convert the RGB values to hexadecimal format
	        String newColor = String.format("0x%02X%02X%02X", red, green, blue);
	        
			crude.update("grupa", "boja", "idgroup", groupIDs.get(index), newColor);
			crude.update("grupa", "name", "idgroup", groupIDs.get(index), groupNames.get(index));	
		}
	}
	
	public static void deleteGroup(String grID) {
		crude.delete("grupa", "idgroup", grID);
		getGroups();
	}


}
