package DayView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import CalendarApp.CalendarApp;
import Event.DateFormatter;
import Event.Event;
import Event.EventCache;
import Event.FormatTypes;
import Listeners.EventPicker;
import Records.CachedCalendar;

public class DayViewCollection extends JPanel{
	/**
	 * Comment by Brock Sauvage
	 * This section is where member variable declaration happens.
	 */
	private static final long serialVersionUID = -3078193866264175025L;
	
	private JPanel leftBar;
	private String dateString;
	private Date date;
	private JScrollPane rightScrollPane;
	private LinkedList<Event> todaysEvents;
	private LinkedList<Event> allDayEvents;
	private EventPicker eventPicker;
	
	/**
	 * Comment by Brock Sauvage
	 * This method is responsible for creating a linked list of a particular
	 * days events, and creating a window for them to be displayed
	 */
	public DayViewCollection(){
		todaysEvents = new LinkedList<Event>();
		eventPicker = new EventPicker();
		setDate();
		
		dateString = DateFormatter.format(FormatTypes.Date, date);
		todaysEvents = EventCache.getInstance().getEventsForDate(dateString);
		
		allDayEvents = removeAllDayEvents(todaysEvents);
		sort(todaysEvents);
		
		initThisPanel();
		
		Component spacer = Box.createRigidArea(new Dimension(CalendarApp.FRAME_WIDTH,50));
		add(spacer, BorderLayout.PAGE_START);
		
		addLeftBar();
		
		addRightScrollBar();
		
		spacer = Box.createRigidArea(new Dimension(CalendarApp.FRAME_WIDTH,50));
		add(spacer, BorderLayout.PAGE_END);

		
	}
	
	/**
	 * Comment by Brock Sauvage
	 * This method is responsible for setting the date of an event, using the CachedCalendar class
	 */
	private void setDate(){
		Calendar t = Calendar.getInstance();
		t.set(Calendar.MONTH, CachedCalendar.getInstance().Month);
		t.set(Calendar.DAY_OF_MONTH,CachedCalendar.getInstance().DayOfMonth);
		t.set(Calendar.YEAR, CachedCalendar.getInstance().Year);
		date = t.getTime();
		
	}
	
	/**
	 * Comment by Brock Sauvage
	 * @Brief: This method is responsible for initializing the panel that
	 * 		   the events will be displayed in.
	 */
	private void initThisPanel(){
		setLayout( new BorderLayout() );
		setPreferredSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.FRAME_HEIGHT+50));
		setMinimumSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.FRAME_HEIGHT+50));
		setMaximumSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.FRAME_HEIGHT+50));
		setBackground(Color.GRAY);
	}
	
	/**
	 * Comment by Brock Sauvage
	 * @Brief: This adds the "left bar" section of the day view, where events are
	 * 		   displayed.
	 */
	private void addLeftBar(){
		leftBar = new DayViewLeftPanel(date,dateString,allDayEvents,eventPicker);
		add(leftBar, BorderLayout.LINE_START);
	}
	
	/**
	 * Comment by Brock Sauvage
	 * @Brief: This is responsible for creating a scroll bar in the event viewer in the
	 *         case that there are too many events to display all at once.
	 */
	private void addRightScrollBar(){
		rightScrollPane = new JScrollPane();
		rightScrollPane.setPreferredSize(new Dimension((int)((2.0/3.0)*CalendarApp.FRAME_WIDTH),(int)(CalendarApp.FRAME_HEIGHT)));
		rightScrollPane.setMinimumSize(new Dimension((int)((2.0/3.0)*CalendarApp.FRAME_WIDTH),(int)(CalendarApp.FRAME_HEIGHT)));
		rightScrollPane.setMaximumSize(new Dimension((int)((2.0/3.0)*CalendarApp.FRAME_WIDTH),(int)(CalendarApp.FRAME_HEIGHT)));
		
		JScrollBar vertical = rightScrollPane.getVerticalScrollBar();
		vertical.setUnitIncrement(20);
		InputMap im = vertical.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		im.put(KeyStroke.getKeyStroke("DOWN"), "positiveUnitIncrement");
		im.put(KeyStroke.getKeyStroke("UP"), "negativeUnitIncrement");
		

		rightScrollPane.setViewportView(new DayViewGrid(rightScrollPane,date,todaysEvents,eventPicker));
		
		add(rightScrollPane, BorderLayout.LINE_END);
		
	}
	/**
	 * remove all day events before this point
	 * @param linkedList
	 */
	public void sort(LinkedList<Event> lle){
		Collections.sort(lle, new Comparator<Event>() {
	         @Override
	         public int compare(Event o1, Event o2) {
	        	
	        	 /**
	        	  * This block of code runs comparisons of two events to see if they overlap each other while it is sorting
	        	  * them into chronological order
	        	  */	        	 
	        	 if((getTimeStringAsMinutes(o1.StartTime) <= getTimeStringAsMinutes(o2.StartTime) ) && (getTimeStringAsMinutes(o1.StopTime) >= getTimeStringAsMinutes(o2.StartTime))){
	        		 JOptionPane.showMessageDialog(null, "WARNING: There are overlapping events present in this day.");
	        	 } else if((getTimeStringAsMinutes(o1.StartTime) >= getTimeStringAsMinutes(o2.StartTime) ) && (getTimeStringAsMinutes(o1.StopTime) <= getTimeStringAsMinutes(o2.StartTime))){
	        		 JOptionPane.showMessageDialog(null, "WARNING: There are overlapping events present in this day.");
	        	 } else if((getTimeStringAsMinutes(o1.StopTime) <= getTimeStringAsMinutes(o2.StopTime) ) && (getTimeStringAsMinutes(o1.StartTime) >= getTimeStringAsMinutes(o2.StopTime))){
	        		 JOptionPane.showMessageDialog(null, "WARNING: There are overlapping events present in this day.");
	        	 } else if((getTimeStringAsMinutes(o1.StopTime) >= getTimeStringAsMinutes(o2.StopTime) ) && (getTimeStringAsMinutes(o1.StartTime) <= getTimeStringAsMinutes(o2.StopTime))){
	        		 JOptionPane.showMessageDialog(null, "WARNING: There are overlapping events present in this day.");
	        	 }
	        	 
	        	 
	             return getTimeStringAsMinutes(o1.StartTime)-getTimeStringAsMinutes(o2.StartTime);
	         }
	     });
	}
	
	/**
	 * Comment by Brock Sauvage
	 * @Pre: Takes in a string, timeString
	 * @Post: Takes timeString, and converts it into minutes
	 * @Return: Returns the given time string in minutes, as an int.
	 * @param timeString
	 * @return
	 */
	public int getTimeStringAsMinutes(String timeString){
		int timeInMinutes=0;
		
		String[] arr = timeString.split(":");
		int hourValue = Integer.parseInt(arr[0]);
		char[] minutes = arr[1].toCharArray();
		int minutesValue = Integer.parseInt(minutes[0]+"")* 10 +Integer.parseInt(minutes[1]+"");
		boolean isAM = minutes[2] =='a';
		
		if(hourValue == 12 && isAM){
			timeInMinutes = minutesValue;
		}else if(isAM){
			timeInMinutes = hourValue*60+minutesValue;
		}else if(hourValue == 12 && !isAM){
			timeInMinutes = hourValue*60+minutesValue;
		}else if(!isAM){
			timeInMinutes = 12*60+hourValue*60+minutesValue;
		}
		return timeInMinutes;
		
	}
	
	/**
	 * @Pre: Takes in a link list of event objects
	 * @Post: Removes all of the events from the linked list of a particular day
	 * @Return: Returns a linked list of event objects
	 * @param lle
	 * @return
	 */
	public LinkedList<Event> removeAllDayEvents(LinkedList<Event> lle){
		
		Iterator<Event> it  = lle.iterator();
		LinkedList<Event> allDayEvents = new LinkedList<Event>();
		
		while(it.hasNext()){
			Event e = it.next();
			if(e.StartTime.equals("-1") || e.StopTime.equals("-1")){
				it.remove();
				allDayEvents.add(e);
			}
			
		}
		return allDayEvents;
	}
}
