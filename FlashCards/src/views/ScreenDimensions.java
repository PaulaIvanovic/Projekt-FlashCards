package views;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ScreenDimensions {
	public int screenWidth;
	public int screenHeight;
	int minimumWindowWidth;
	int minimumWindowHeight;
	
    public ScreenDimensions() {
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenHeight = (int)screenSize.getHeight();
    	screenWidth = (int)screenSize.getWidth();
    	minimumWindowHeight = (int)(screenHeight*0.75);
    	minimumWindowWidth = (int)(screenWidth*0.55);
	}

}
