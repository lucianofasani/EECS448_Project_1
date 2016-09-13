/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WeekView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.JPanel;

import Listeners.DatePicker;
import MonthView.MonthViewDay;
import Records.CachedCalendar;
import Temp.CalendarApp;

/**
 *
 * @author Sam
 */
public class WeekViewGrid extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6229769874630466647L;

	private final DatePicker mouseListener;
	
	public WeekViewGrid(){
		/*
		 * Configure Panel
		 */
		setMinimumSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.FRAME_HEIGHT));
		setMaximumSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.FRAME_HEIGHT));
		setPreferredSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.FRAME_HEIGHT));
		setLayout(new GridLayout(1,7));
		
		/*
		 * MouseListener for dates
		 */
		mouseListener = new DatePicker();
	
		/*
		 * Configure it to draw the current month 
		 */
		Calendar mycal = Calendar.getInstance();
		mycal.set(Calendar.YEAR, CachedCalendar.getInstance().Year);
		mycal.set(Calendar.DAY_OF_MONTH, CachedCalendar.getInstance().DayOfMonth);
		mycal.set(Calendar.MONTH, CachedCalendar.getInstance().Month);
                
		/*
		 * Draw the days
		 */
		int dayName = mycal.get(Calendar.DAY_OF_WEEK);
                int day = mycal.get(Calendar.DAY_OF_MONTH);
                int start = (day - dayName)+1;
		MonthViewDay temp = null;
		int plottedDays = 0;
		for(int i = start; i<start+7; i++, plottedDays++){
			temp = new MonthViewDay((i),new Dimension(50,100));
			temp.setBackground(Color.GRAY);
			temp.addMouseListener(mouseListener);
			add(temp);
		}
			
	}
}
