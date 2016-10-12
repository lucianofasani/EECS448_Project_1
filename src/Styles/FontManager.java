package Styles;

import java.awt.Font;

/**
 * 
 * Comment by Brock Sauvage
 * Written by old team
 * This class is responsible for setting all aspects of the typeface used, including
 * font and size.
 *
 */
public class FontManager {
	private static final Font mStandardFont = new Font("Helvetica",Font.PLAIN,18);
	private static final Font mSmallFont = new Font("Helvetica",Font.PLAIN,12);
	private static final Font mBoldFont = new Font("Helvetica",Font.BOLD,18);
	
	
	public static Font getStandardFont(){
		return mStandardFont;
	}
	public static Font getSmallFont(){
		return mSmallFont;
	}
	public static Font getBoldFont(){
		return mBoldFont;
	}

}
