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

import CalendarApp.CalendarApp;
import Listeners.DatePicker;
import Records.CachedCalendar;

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
		WeekViewDay temp = null;
		
	
		int plottedCount = 0;
		while(start<=0){
			temp = new WeekViewDay(start, CachedCalendar.getInstance().Month-1,null,new Dimension(50,100));
			temp.setBackground(Color.GRAY.brighter());
			temp.addMouseListener(mouseListener);
			add(temp);
			start++;
			plottedCount++;
		}
		
		for(; plottedCount<7 && start <= mycal.getActualMaximum(Calendar.DAY_OF_MONTH); start++,plottedCount++){
			temp = new WeekViewDay(start,null,null,new Dimension(50,100));
			temp.setBackground(Color.GRAY.brighter());
			temp.addMouseListener(mouseListener);
			add(temp);
		}
		for(int i = 1;plottedCount<7;plottedCount++,i++){
			temp = new WeekViewDay(i,CachedCalendar.getInstance().Month+1,null,new Dimension(50,100));
			temp.setBackground(Color.GRAY.brighter());
			temp.addMouseListener(mouseListener);
			add(temp);
		}
			
	}
}
