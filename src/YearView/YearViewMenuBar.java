package MonthView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.DaysOfWeek;
import Temp.CalendarApp;


public class MonthViewMenuBar extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3252815883180101512L;

	public MonthViewMenuBar(){
		/*
		 * Configure panel
		 */
		setMinimumSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT/2));
		setMaximumSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT/2));
		setPreferredSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT/2));
		setLayout(new GridLayout(1,7));
		setBackground(Color.ORANGE);
		
		/*
		 * Add day of week Tiles
		 */
		for(int i = 0; i<DaysOfWeek.values().length; i++){
			JLabel name = new JLabel(DaysOfWeek.values()[i].toString());
			JPanel j = new JPanel();
			j.setMinimumSize(new Dimension(CalendarApp.DAY_OF_MONTH_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT/2));
			j.setMaximumSize(new Dimension(CalendarApp.DAY_OF_MONTH_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT/2));
			j.setPreferredSize(new Dimension(CalendarApp.DAY_OF_MONTH_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT/2));
			j.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			j.add(name);
			add(j);
		}
	}

}
