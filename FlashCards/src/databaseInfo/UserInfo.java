package databaseInfo;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;

import views.GlobalDesign;

public class UserInfo implements GlobalDesign{
	public static String userID;
	public static String groupID;
	public static String subGroupID;
	
	public static ArrayList<Color> groupColors;
	public static ArrayList<String> groupNames;
	public static ArrayList<String> groupIDs;
	
	public static ArrayList<Color> subGroupColors;
	public static ArrayList<String> subGroupNames;
	public static ArrayList<String> subGroupIDs;
	
	public static ArrayList<Color> cardColors;
	public static ArrayList<String> cardQuestion;
	public static ArrayList<String> cardAnswer;
	public static ArrayList<String> cardCorrect;
	public static ArrayList<String> cardID;
	public static Crude crude;
	
	public UserInfo(String username, boolean newUser) {
		 crude = new Crude();
		 
		 groupColors = new ArrayList<>();
		 groupNames = new ArrayList<>();
		 groupIDs = new ArrayList<>();
		 
		 subGroupColors = new ArrayList<>();
		 subGroupNames = new ArrayList<>();
		 subGroupIDs = new ArrayList<>();
		 
		 cardColors = new ArrayList<>();
		 cardQuestion = new ArrayList<>();
		 cardAnswer = new ArrayList<>();
		 cardCorrect = new ArrayList<>();
		 cardID = new ArrayList<>();
		 
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
		subGroupIDs.clear();
		PullFrom subgroupInfo = new PullFrom("subgroup","idgroup", groupID);
		try {
			 while(subgroupInfo.rs.next()) {
				 subGroupNames.add(subgroupInfo.rs.getString("name"));
				 subGroupColors.add(Color.decode(subgroupInfo.rs.getString("boja")));
				 subGroupIDs.add(subgroupInfo.rs.getString("idsubgroup"));
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void getCards() {
		cardColors.clear();
		cardQuestion.clear();
		cardAnswer.clear();
		cardCorrect.clear();
		cardID.clear();
		PullFrom cardInfo = new PullFrom("card","idsubgroup", subGroupID);
		try {
			 while(cardInfo.rs.next()) {
				 cardQuestion.add(cardInfo.rs.getString("title"));
				 cardAnswer.add(cardInfo.rs.getString("paragraph"));
				 cardColors.add(Color.decode(cardInfo.rs.getString("boja")));
				 cardCorrect.add(cardInfo.rs.getString("T/F"));
				 cardID.add(cardInfo.rs.getString("idcard"));
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
	
	public static void addsubGroup(String name, String chosenColor) {
		try {
			crude.create("subgroup", name, chosenColor, groupID);
			getSubgroups();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addCard(String question, String answer, String chosenColor) {
		try {
			crude.create("card", question, answer, chosenColor, subGroupID);
			getCards();
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
	
	public static void getsubGroupID(String name) {
		//get subgroup
		 PullFrom subgroup = new PullFrom("subgroup","name", name);
		
		 //get subgroup ID
		 try {
			 while (subgroup.rs.next()) {
				 if(subgroup.rs.getString("idgroup").equals(groupID)) {
					 subGroupID = subgroup.rs.getString("idgroup"); 
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
	
	public static void changesubGroupColor(String sgrID, Color newColor) {
		int index = subGroupIDs.indexOf(sgrID);
		if(index > -1) {
			subGroupColors.set(index, newColor);	
		}
	}


	public static void changesubGroupName(String sgrID, String newName) {
		int index = subGroupIDs.indexOf(sgrID);
		if(index > -1) {
			subGroupNames.set(index, newName);	
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
	

	public static void saveEditAllSubgroups() {
		for(int i = 0; i < subGroupIDs.size(); i++) {
			int red = subGroupColors.get(i).getRed();
	        int green = subGroupColors.get(i).getGreen();
	        int blue = subGroupColors.get(i).getBlue();
	        // Convert the RGB values to hexadecimal format
	        String newColor = String.format("0x%02X%02X%02X", red, green, blue);
	        
			crude.update("subgroup", "boja", "idsubgroup", subGroupIDs.get(i), newColor);
			crude.update("subgroup", "name", "idsubgroup", subGroupIDs.get(i), subGroupNames.get(i));
		}
	}
	
	public static void saveEditAllCards() {
		for(int i = 0; i < cardID.size(); i++) {
			int red = cardColors.get(i).getRed();
	        int green = cardColors.get(i).getGreen();
	        int blue = cardColors.get(i).getBlue();
	        // Convert the RGB values to hexadecimal format
	        String newColor = String.format("0x%02X%02X%02X", red, green, blue);
	        
			crude.update("card", "boja", "idcard", cardID.get(i), newColor);
			crude.update("card", "title", "idcard", cardID.get(i), cardQuestion.get(i));
			crude.update("card", "paragraph", "idcard", cardID.get(i), cardAnswer.get(i));
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
	
	public static void saveEditsubGroup(String grID) {
		int index = groupIDs.indexOf(grID);
		if(index > -1) {
			int red = subGroupColors.get(index).getRed();
	        int green = subGroupColors.get(index).getGreen();
	        int blue = subGroupColors.get(index).getBlue();
	        // Convert the RGB values to hexadecimal format
	        String newColor = String.format("0x%02X%02X%02X", red, green, blue);
	        
			crude.update("subgroup", "boja", "idsubgroup", subGroupIDs.get(index), newColor);
			crude.update("subgroup", "name", "idsubgroup", subGroupIDs.get(index), subGroupNames.get(index));
		}
	}
	
	public static void deleteGroup(String grID) {
		crude.delete("grupa", "idgroup", grID);
		getGroups();
	}


	public static void deleteSubgroup(String sgrID) {
		crude.delete("subgroup", "idsubgroup", sgrID);
		getSubgroups();
	}
	
	
	public static String getname(boolean choice) {
		if(choice) {
			//get group name
			PullFrom groupInfo = new PullFrom("grupa","idgroup", groupID);
				 try {
					while(groupInfo.rs.next()) {
						  return (groupInfo.rs.getString("name"));
					 }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else {
			//get subgroup name
			PullFrom subgroupInfo = new PullFrom("subgroup","idsubgroup", subGroupID);
			 try {
				while(subgroupInfo.rs.next()) {
					  return (subgroupInfo.rs.getString("name"));
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "";
	}


}
