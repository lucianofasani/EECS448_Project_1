package DayView;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Event.Event;
import Listeners.EventPicker;

public class DayViewGrid extends JPanel{
	/**
	 * Written by previous team, commented by Brock Sauvage
	 * This class essentially creates a grid for all of the day view elements
	 * to sit in.
	 */
	private static final long serialVersionUID = -6262892174085562405L;

	public DayViewGrid(JScrollPane pane, Date d, LinkedList<Event> todaysEvents, EventPicker eventPicker){
		int sizeOfEachGrid = 100;
		int sizeOfList = todaysEvents.size();
		GridLayout listEvents = new GridLayout(sizeOfList>6?sizeOfList:6,1);
		setLayout(listEvents);
		setMinimumSize(new Dimension(pane.getWidth(),pane.getHeight()));
		setMaximumSize(new Dimension(pane.getWidth(),sizeOfList*sizeOfEachGrid));
		setPreferredSize(new Dimension(pane.getWidth(),sizeOfList*sizeOfEachGrid));
		//setBackground(Color.black);

	
		
		Iterator<Event> it = todaysEvents.iterator();
	
		while(it.hasNext()){
			
			DayViewEvent e = new DayViewEvent(it.next());
			e.addMouseListener(eventPicker);
			add(e);
			
		}

		
	}



}
