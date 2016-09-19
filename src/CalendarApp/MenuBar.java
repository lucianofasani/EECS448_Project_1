package CalendarApp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import Records.CachedCalendar;
import constants.MonthsOfYear;
import constants.ViewTypes;
/**
 * 
 * Top menubar to be show in the frame, for all viewtypes
 *
 */
public class MenuBar extends JPanel{

	private static final long serialVersionUID = 341341509482431909L;
	private static final int BUTTON_HEIGHT 	= 25;
	private static final int BUTTON_WIDTH 	= 80;
	private static final int YEAR_OFFSET = 2016;
	
	/*
	 * JComponents that we need increased scope on.
	 */
	private JComboBox<String> monthList 		= null;
	private JComboBox<String> dayList			= null;
	private JComboBox<String> yearList			= null;
	private JButton applyDate 					= null;
	
	/**
	 * Container this is set in.
	 */
	private CalendarApp calendarApp				= null;
	
	/*
	 * Values being hovered in comboboxes 
	 */
	private int Year 	= 0;
	private int Month 	= 0;
	private int Day 	= 0;
	
	
	
	public MenuBar(CalendarApp calendarApp){
		this.calendarApp = calendarApp;
		
		setMinimumSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT/2));
		setMaximumSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT/2));
		setPreferredSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT/2));
		setBackground(Color.ORANGE);
		
		initFormatButtons();
		initMonthBox();
		initDayBox();
		initYearBox();
		initUpdate();
	
	}
	
	/**
	 * Initialize the update button
	 */
	private void initUpdate(){
		applyDate = new JButton("Apply");
		
		applyDate.addActionListener(new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	SaveNonVol();
		    	calendarApp.updateCurrentView();
		    	applyDate.setEnabled(false);
		    	
		    }
		});
		setupButton(applyDate);
		applyDate.setEnabled(false);
		add(applyDate);
	}
	/**
	 * Initialize the view buttons
	 */
	private void initFormatButtons(){
		
		JButton year = new JButton("Year");
		year.addActionListener(new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	calendarApp.setActiveView(ViewTypes.YEAR);
		    }
		});
		setupButton(year);
		
		JButton month = new JButton("Month");
		month.addActionListener(new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	calendarApp.setActiveView(ViewTypes.MONTH);
		    }
		});
		setupButton(month);
		
		JButton week = new JButton("Week");
		week.addActionListener(new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	calendarApp.setActiveView(ViewTypes.WEEK);
		    }
		});
		setupButton(week);
		
		JButton day = new JButton("Day");
		day.addActionListener(new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	calendarApp.setActiveView(ViewTypes.DAY);
		    }
		});
		setupButton(day);

		add(year);
		add(month);
		add(week);
		add(day);
		
		Component spacer = Box.createRigidArea(new Dimension(BUTTON_WIDTH/2,0));
		add(spacer);
	}
	/**
	 * Initialize button sizes
	 */
	private void setupButton(JButton b){
		b.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		b.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		b.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
	}
	
	/**
	 * Initialize month JComboBox
	 */
    private void initMonthBox(){
		
		MonthsOfYear[] months = MonthsOfYear.values();
		String[] names = new String[months.length];
		for(int i = 0; i<months.length; i++){
			names[i] = months[i].toString();
		}
		
		monthList = new JComboBox<String>(names);
		monthList.setSelectedIndex(CachedCalendar.getInstance().Month);
		this.Month = CachedCalendar.getInstance().Month;
		monthList.addActionListener(new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	storeMonth();
		        resetDayBox();
		    }
		});
		    
		add(monthList);
	}
	/**
	 * Initialize day JComboBox
	 */
	private void initDayBox(){
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, CachedCalendar.getInstance().Month);
		int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		String[] names = new String[count];
		for(int i = 1; i<=count; i++){
			names[i-1] = i+"";
		}
		
		dayList = new JComboBox<String>(names);
		dayList.setSelectedIndex(CachedCalendar.getInstance().DayOfMonth-1);
		this.Day = CachedCalendar.getInstance().DayOfMonth-1;
		dayList.addActionListener(new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	storeDay();
		    	applyDate.setEnabled(true);
		    }
		});
		
		add(dayList);
	}
	/**
	 * Initialize year JComboBox
	 */
	private void initYearBox(){
		String[] years = {"2016","2017"};
		
		yearList = new JComboBox<String>(years);
		yearList.setSelectedIndex(CachedCalendar.getInstance().Year-YEAR_OFFSET);
		this.Year = CachedCalendar.getInstance().Year;
	
		yearList.addActionListener(new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	storeYear();
		    	
		    	//Stupid February and its day count
		    	if( Month == 1 ){
		    		resetDayBox();
		    	}
		    	applyDate.setEnabled(true);
		    	
		    	
		    }
		});
		add(yearList);
	}
	/**
	 * Store month from ComboBox to member variable
	 */
	private void storeMonth(){
		this.Month = monthList.getSelectedIndex();
		System.out.println("Month "+this.Month);
	}
	/**
	 * Store day from ComboBox to member variable
	 */
	private void storeDay(){
		this.Day = dayList.getSelectedIndex()+1;
		System.out.println("Day "+this.Day);
	}
	/**
	 * Store year from ComboBox to member variable
	 */
	private void storeYear(){
		this.Year = yearList.getSelectedIndex()+YEAR_OFFSET;
		System.out.println("Year "+this.Year);
		storeDay();
	}
	
	/**
	 * Save to our calendar instance and write the date to file
	 */
	private void SaveNonVol(){
		CachedCalendar.getInstance().Year = this.Year;
		CachedCalendar.getInstance().DayOfMonth = this.Day;
		CachedCalendar.getInstance().Month = this.Month;
		CachedCalendar.getInstance().saveToFile();
	}
	
	/**
	 * Reinitialize day box with proper number of days.
	 */
	private void resetDayBox(){
		int temp = dayList.getSelectedIndex()+1;
		
		dayList.removeAllItems();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, this.Month);
		cal.set(Calendar.YEAR, this.Year);
		
		int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		temp = (temp<=count)?temp:count;
		temp--;
		
		for(int i = 1; i<=count; i++){

			dayList.addItem(i+"");
		}
		dayList.setSelectedIndex(temp);
		
	}
	

}
