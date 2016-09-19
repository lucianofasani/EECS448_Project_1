package YearView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.JPanel;

import CalendarApp.CalendarApp;
import Records.CachedCalendar;



public class YearViewGrid extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6229769874630466647L;
	
	public YearViewGrid(){
		/*
		 * Configure Panel
		 */
		setMinimumSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.FRAME_HEIGHT));
		setMaximumSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.FRAME_HEIGHT));
		setPreferredSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.FRAME_HEIGHT));
		setLayout(new GridLayout(6,7));
		
	
		/*
		 * Configure it to draw the current month 
		 */
		Calendar mycal = Calendar.getInstance();
		mycal.set(Calendar.YEAR, CachedCalendar.getInstance().Year);
		mycal.set(Calendar.DAY_OF_MONTH, 1);
		mycal.set(Calendar.MONTH, CachedCalendar.getInstance().Month);

		/*
		 * Draw the days
		 */

		YearViewDay temp = null;
             
		for(int i =1 ;i<13; i++){
			temp = new YearViewDay((i),new Dimension(CalendarApp.DAY_OF_MONTH_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT));
			temp.setBackground(Color.white);
			add(temp);    
                        
                        
		}

                    
	}
}
