package views;

import java.awt.Color;
import java.awt.Font;

public interface GlobalDesign {

	public Color backgroundColor = new Color(68,62,130);
	public Color toolbarColor = new Color(31,0,57);
	public Color textEnter = new Color(166,166,166);
	public Color textRed = new Color(255,124,124);
	public Font mainFont = new Font("Canva Sans", Font.PLAIN , 24);
	public Font secFont = new Font("Canva Sans", Font.PLAIN , 18);
	
	//colors representing groups in group of cards display
	public Color[] groupColors = {new Color(0x8CB459), 
			new Color(0x9391f6),
			new Color(0xFF5454),
			new Color(0xEFE078),
			new Color(0x71D3DD), 
			new Color(0xD068FA),
			new Color(0xFFA030),
			new Color(0xFF5899),
			new Color(0x5148FF),
			new Color(0x67E1A7)
			};
	
	//test names of groups to be used when clicked on certain group:
	public String[] groupNames = {"Maths", "Physics", "Biology", "Geography", "Arts", "History",
			"Chemistry", "English", "Latin", "Music"};
	        
		
}
