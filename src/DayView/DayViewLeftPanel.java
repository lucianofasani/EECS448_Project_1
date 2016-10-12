package DayView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import CalendarApp.CalendarApp;
import Event.Event;
import Event.EventPlanner;
import Exceptions.InstanceOverflowException;
import Listeners.EventPicker;
import Styles.FontManager;

public class DayViewLeftPanel extends JPanel {

	/**
	 * Written by previous team, commented by Brock Sauvage.
	 * This class essentially handles all of the information that is displayed on the 
	 * left-hand panel you see in dayview, including all events, the date, etc.
	 */
	private static final long serialVersionUID = 8850861518801177742L;

	public DayViewLeftPanel(
							Date date,
							String dateString, 
							LinkedList<Event> allDayEventsList, 
							EventPicker eventPicker 
							)
	{
		
		setLayout(null);
		setBackground(Color.lightGray);
		setPreferredSize(new Dimension((int)((1.0/3.0)*CalendarApp.FRAME_WIDTH),CalendarApp.FRAME_HEIGHT));
		setMinimumSize(new Dimension((int)((1.0/3.0)*CalendarApp.FRAME_WIDTH),CalendarApp.FRAME_HEIGHT));
		setMaximumSize(new Dimension((int)((1.0/3.0)*CalendarApp.FRAME_WIDTH),CalendarApp.FRAME_HEIGHT));
		
		JLabel allDayEvents = new JLabel("All-Day Events for:");
		allDayEvents.setFont(FontManager.getBoldFont());
		allDayEvents.setBounds(25,5,200,30);
		
		JLabel dateLabel = new JLabel(dateString);
		dateLabel.setFont(FontManager.getBoldFont());
		dateLabel.setBounds(65,35,200,30);
		

		

		add(allDayEvents);
		add(dateLabel);
		for(int i = 0; i< allDayEventsList.size(); i++){
			AllDayEvent e = new AllDayEvent(allDayEventsList.get(i));
			e.setFont(FontManager.getStandardFont());
			e.setBounds(0,100+100*i,(int)getPreferredSize().getWidth(),100);
			e.addMouseListener(eventPicker);
			add(e);

			
		}
		JButton b = new JButton("New Event");
		b.setBounds(0,610,(int)getPreferredSize().getWidth(),40);
		b.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					EventPlanner.create(date);
				} catch (InstanceOverflowException ex) {
					JOptionPane.showMessageDialog(null, "Please finish what you are doing before creating another.");
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		add(b);	
	}
}
