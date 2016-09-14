package DayView;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Event.Event;
import Styles.FontManager;
import Temp.CalendarApp;

public class DayViewLeftPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8850861518801177742L;

	public DayViewLeftPanel(Date date, String dateString, LinkedList<Event> allDayEventsList){
		
		setLayout(null);
		setBackground(Color.lightGray);
		setPreferredSize(new Dimension((int)((1.0/3.0)*CalendarApp.FRAME_WIDTH),CalendarApp.FRAME_HEIGHT));
		setMinimumSize(new Dimension((int)((1.0/3.0)*CalendarApp.FRAME_WIDTH),CalendarApp.FRAME_HEIGHT));
		setMaximumSize(new Dimension((int)((1.0/3.0)*CalendarApp.FRAME_WIDTH),CalendarApp.FRAME_HEIGHT));
		
		JLabel allDayEvents = new JLabel("All-Day Events for:");
		allDayEvents.setFont(FontManager.getStandardFont());
		allDayEvents.setBounds(25,5,200,30);
		
		JLabel dateLabel = new JLabel(dateString);
		dateLabel.setFont(FontManager.getStandardFont());
		dateLabel.setBounds(25,35,200,30);

		add(allDayEvents);
		add(dateLabel);
		
		for(int i = 0; i< allDayEventsList.size(); i++){
			JLabel label = new JLabel(allDayEventsList.get(i).Name);
			label.setFont(FontManager.getStandardFont());
			label.setBounds(25,60+40*i,200,30);
			add(label);

			
		}
	}
}
