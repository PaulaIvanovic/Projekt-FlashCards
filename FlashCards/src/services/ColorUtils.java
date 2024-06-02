package services;
import java.awt.Color;

public class ColorUtils {
    
    public static Color getContrastingTextColor(Color backgroundColor) {
        // Calculate the luminance of the background color
        double luminance = (0.299 * backgroundColor.getRed() +
                            0.587 * backgroundColor.getGreen() +
                            0.114 * backgroundColor.getBlue()) / 255;

        // Return black or white based on luminance
        return luminance > 0.5 ? Color.BLACK : Color.WHITE;
    }
}