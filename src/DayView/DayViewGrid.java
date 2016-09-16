package DayView;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Event.Event;
import Listeners.EventPicker;

public class DayViewGrid extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6262892174085562405L;

	public DayViewGrid(JScrollPane pane, Date d, LinkedList<Event> todaysEvents, EventPicker eventPicker){
		int sizeOfEachGrid = 100;
		int sizeOfList = todaysEvents.size();
		setLayout(new GridLayout(sizeOfList>6?sizeOfList:6,1));
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
