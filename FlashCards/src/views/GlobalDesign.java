package views;
import java.awt.Color;
import java.awt.Font;



public interface GlobalDesign {
	ScreenDimensions dimensions = new ScreenDimensions();
	public int charLimit = 40;

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
	public String defaultGroupName = "Default";
	public String defaultGroupColor = "0xFFFFFF";
	
	//colors representing groups in group of cards display
	public Color[] groupColorOptions = {
			new Color(0x2662E8),  //blue 1
			new Color(0x5148FF), //blue 2
			new Color(0x9391f6), //pastel purple
			new Color(0xD068FA), //purple
			new Color(0xFF5899), //dark pink
			new Color(0xFF5454), //coral
			new Color(0xFF0303), //red
			new Color(0xFF5400), //orange
			new Color(0xFFA030), //pastel orange
			new Color(0xEFE078), //pastel yellow		
			new Color(0xF8EE02), //yellow
			new Color(0x8CB459), //olive
			new Color(0x00bf63), //green
			new Color(0x67E1A7), //turquoise
			new Color(0x71D3DD), //aqua
			new Color(0xFFFFFF), //white
			};

	
	public Color[] subGroupColors = {new Color(0x8CB459), 
			new Color(0x9391f6),
			new Color(0xFF5454),
			new Color(0xEFE078),
			new Color(0xEFE078),
			new Color(0x8CB459), 
			new Color(0x9391f6),
			new Color(0xFF5454),
			new Color(0xEFE078)
	};
	
	public String[] subGroupNames = {"", "Subgroup1", "SubGroup2", "SubGroup3", "Subgroup4",
			"Subgroup1", "SubGroup2", "SubGroup3", "Subgroup4"
	};     

	public String[] cardQuestion = {"", "Tko ide u kino neka ide slobodno, tko ne ide ne može ići.", 
			"Tko ide u kino neka ide slobodno, tko ne ide ne može ićiiiiiii.", "SubGroup3", "Subgroup4", 
			"Tko ide u kino neka ide slobodno, tko ne ide ne može ićiiiiiii.", 
			"Tko ide u kino neka ide slobodno, tko ne ide ne može ićiiiiiii."
	};   

	public Color[] cardQuestionColors = {new Color(0x8CB459), 
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
			new Color(0x71D3DD), 
			new Color(0xD068FA),
			new Color(0x5148FF),
			new Color(0x67E1A7),
			new Color(0x9391f6),
			new Color(0xD068FA),
			new Color(0x71D3DD),
			};
		
}

