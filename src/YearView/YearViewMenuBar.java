package YearView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import CalendarApp.CalendarApp;
import Records.CachedCalendar;


public class YearViewMenuBar extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3252815883180101512L;

	public YearViewMenuBar(){
		/*
		 * Configure panel
		 */
		setMinimumSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT/2));
		setMaximumSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT/2));
		setPreferredSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT/2));
		setLayout(new GridLayout(1,7));
		setBackground(Color.white);
		
	
                     
                        int year = CachedCalendar.getInstance().Year;
                        String year1 = "";
                        if (year == 2016)
                        {
                            year1 = "2016";
                        }
                        if (year == 2017)
                        {
                            year1 = "2017";
                        }
			JLabel name = new JLabel(year1);
			JPanel j = new JPanel();
			j.setMinimumSize(new Dimension(CalendarApp.DAY_OF_MONTH_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT/2));
			j.setMaximumSize(new Dimension(CalendarApp.DAY_OF_MONTH_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT/2));
			j.setPreferredSize(new Dimension(CalendarApp.DAY_OF_MONTH_WIDTH,CalendarApp.DAY_OF_MONTH_HEIGHT/2));
			j.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
			name.setFont(new Font("Helvetica", Font.BOLD, 44));
			j.add(name);
			add(j);
	}

}
