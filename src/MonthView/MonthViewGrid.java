package MonthView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.JPanel;

import CalendarApp.CalendarApp;
import Listeners.DatePicker;
import Records.CachedCalendar;



public class MonthViewGrid extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6229769874630466647L;

	private final DatePicker mouseListener;
	
	public MonthViewGrid(){
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
		int day = mycal.get(Calendar.DAY_OF_WEEK);
		int maxDaysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		MonthViewDay temp = null;
		int plottedDays = 0;
		for(int i =1 ;i<day; i++, plottedDays++){
			temp = new MonthViewDay(null,new Dimension(CalendarApp.DAY_OF_MONTH_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT));
			temp.setBackground(Color.black);
			add(temp);
		}
		for(int i = 1; i<=maxDaysInMonth; i++, plottedDays++){
			temp = new MonthViewDay((i),new Dimension(CalendarApp.DAY_OF_MONTH_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT));
			temp.setBackground(Color.white.darker());
			temp.addMouseListener(mouseListener);
			add(temp);
		}
		for( ;plottedDays<7*6; plottedDays++){
			temp = new MonthViewDay(null,new Dimension(CalendarApp.DAY_OF_MONTH_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT));
			temp.setBackground(Color.black);
			add(temp);
		}	
	}
}
