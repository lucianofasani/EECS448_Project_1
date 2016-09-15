package YearView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.JPanel;

import Listeners.DatePicker;
import Records.CachedCalendar;
import Temp.CalendarApp;
import javax.swing.BorderFactory;
import javax.swing.JLabel;



public class YearViewGrid extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6229769874630466647L;

	private final DatePicker mouseListener;
	
	public YearViewGrid(){
		/*
		 * Configure Panel
		 */
		setMinimumSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.FRAME_HEIGHT));
		setMaximumSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.FRAME_HEIGHT));
		setPreferredSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.FRAME_HEIGHT));
		setLayout(new GridLayout(6,7));
		
		/*
		 * MouseListener for dates
		 */
		mouseListener = new DatePicker();
	
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
		//int day = mycal.get(Calendar.DAY_OF_WEEK);
		int maxDaysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		YearViewDay temp = null;
		int plottedDays = 0;               
		for(int i =1 ;i<13; i++, plottedDays++){
			temp = new YearViewDay((i),new Dimension(CalendarApp.DAY_OF_MONTH_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT));
			temp.setBackground(Color.white);
                        //temp.addMouseListener(mouseListener);
			add(temp);    
                        
                        
		}
//		for(int i = 1; i<=4; i++, plottedDays++){
//			temp = new YearViewDay((i),new Dimension(CalendarApp.DAY_OF_MONTH_WIDTH/2,CalendarApp.DAY_OF_MONTH_HEIGHT/6));
//			temp.setBackground(Color.GRAY);
//			temp.addMouseListener(mouseListener);
//			add(temp);
//		}
//		for( ;plottedDays<7*6; plottedDays++){
//			temp = new YearViewDay(null,new Dimension(CalendarApp.DAY_OF_MONTH_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT));
//			temp.setBackground(Color.blue);
//			add(temp);
//		}
                    
	}
}
