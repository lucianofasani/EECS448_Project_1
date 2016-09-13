package Styles;

import java.awt.Font;

public class FontManager {
	private static final Font mStandardFont = new Font("Arial",Font.PLAIN,18);
	private static final Font mSmallFont = new Font("Arial",Font.PLAIN,12);
	private static final Font mBoldFont = new Font("Arial",Font.BOLD,18);
	
	public static Font getStandardFont(){
		return mStandardFont;
	}
	public static Font getSmallFont(){
		return mSmallFont;
	}
	public static Font geBoldFont(){
		return mBoldFont;
	}

}
