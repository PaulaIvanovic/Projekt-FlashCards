package views;

import java.awt.Color;
import java.awt.Font;


public interface GlobalDesign {
	ScreenDimensions dimensions = new ScreenDimensions();

	public Color backgroundColor = new Color(68,62,130);
	public Color toolbarColor = new Color(31,0,57);
	public Color textEnter = new Color(166,166,166);
	public Color textRed = new Color(255,124,124);
	
	public Color white = new Color(0xFFFFFF);
	public Color groupDefaultColor = new Color(0x8CB459);
	
	String groupDefaultName = "Default";
	
	int mainTitleSize = (int) (dimensions.screenHeight * 0.06);
    int mainFontSize = (int) (dimensions.screenHeight * 0.04);
    int mediumFontSize = (int) (dimensions.screenHeight * 0.03);
    int secFontSize = (int) (dimensions.screenHeight * 0.025);		
    int smallFontSize = (int) (dimensions.screenHeight * 0.02);	
    int inputTextSize = (int) (dimensions.screenHeight * 0.015);
    int tinyFontSize = (int) (dimensions.screenHeight * 0.0135);
    
    public Font mainFont = new Font("Tahoma", Font.BOLD , mainFontSize);
    public Font mainTitle = new Font("Tahoma", Font.BOLD , mainTitleSize);
    public Font mediumFont = new Font("Tahoma", Font.PLAIN , mediumFontSize);
    public Font secFont = new Font("Tahoma", Font.PLAIN , secFontSize);
    public Font smallFont = new Font("Tahoma", Font.PLAIN , smallFontSize);
    public Font inputText = new Font("Tahoma", Font.PLAIN , inputTextSize);
    public Font buttonText = new Font("Tahoma", Font.BOLD , inputTextSize);
    public Font tinyFont = new Font("Tahoma", Font.PLAIN , tinyFontSize);
	
    String[] profilePictures = {
            "1.jpg",
            "2.jpg",
            "3.jpeg",
            "4.jpg",
            "5.jpg",
            "6.jpg"
        };
	
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
			new Color(0x67E1A7),
			new Color(0x9391f6),
			new Color(0xFF5454),
			new Color(0xEFE078),
			new Color(0x71D3DD), 
			new Color(0xD068FA),
			new Color(0xD068FA),
			new Color(0xFFA030),
			new Color(0xFF5899),
			new Color(0x5148FF),
			new Color(0x67E1A7),
			new Color(0x9391f6),
			new Color(0xD068FA),
			new Color(0xD068FA),
			new Color(0xFFA030),
			new Color(0xEFE078),
			new Color(0x71D3DD),
			new Color(0xEFE078),
			new Color(0x71D3DD), 
			
			};
	
	//test names of groups to be used when clicked on certain group:
	public String[] groupNames = {"", "Maths", "Physics", "Biology", "Geography", "Arts", "History",
			"Chemistry", "English", "Latin", "Music", "Croatian", "AI", "Programming", "Maths", 
			"English", "Latin", "Music", "Croatian", "AI", "Programming", "Geography", "Arts", "History",
			"Latin", "Music", "Croatian", "AI"};
	
	public Color[] subGroupColors = {new Color(0x8CB459), 
			new Color(0x9391f6),
			new Color(0xFF5454),
			new Color(0xEFE078)
	};
	
	public String[] subGroupNames = {"Subgroup1", "SubGroup2", "SubGroup3", "Subgroup4"};      
		
}
