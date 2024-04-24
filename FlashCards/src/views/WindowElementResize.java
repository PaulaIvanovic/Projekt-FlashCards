package views;

import java.awt.Font;

public class WindowElementResize {
	public static Font mainFont;
	public static Font mainTitle;
	public static Font mediumFont;
	public static Font secFont;
	public static Font smallFont;
	public static Font inputText;
	public static Font buttonText;
	public static Font tinyFont;
	
	public static void getFontForWindowSize(int windowHeight) {
		int mainTitleSize = (int) (windowHeight * 0.06);
	    int mainFontSize = (int) (windowHeight * 0.04);
	    int mediumFontSize = (int) (windowHeight * 0.03);
	    int secFontSize = (int) (windowHeight * 0.025);		
	    int smallFontSize = (int) (windowHeight * 0.02);	
	    int inputTextSize = (int) (windowHeight * 0.015);
	    int tinyFontSize = (int) (windowHeight * 0.0135);
	    
	    mainFont = new Font("Tahoma", Font.BOLD , mainFontSize);
	    mainTitle = new Font("Tahoma", Font.BOLD , mainTitleSize);
	    mediumFont = new Font("Tahoma", Font.PLAIN , mediumFontSize);
		secFont = new Font("Tahoma", Font.PLAIN , secFontSize);
		smallFont = new Font("Tahoma", Font.PLAIN , smallFontSize);
		inputText = new Font("Tahoma", Font.PLAIN , inputTextSize);
		buttonText = new Font("Tahoma", Font.BOLD , inputTextSize);
		tinyFont = new Font("Tahoma", Font.PLAIN , tinyFontSize);
	}
}
