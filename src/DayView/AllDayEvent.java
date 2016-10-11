package DayView;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Event.Event;
import Styles.FontManager;

public class AllDayEvent extends JPanel implements IDayEvent{
	/**
	 * @commentauthor: Brock Sauvage
	 * Still no idea what in the world this variable does. It appears to be some sort of user id that 
	 * was going to be used in an implementation of some sort of user system.
	 */
	private static final long serialVersionUID = -8486049515518099751L;
	
	/**
	 * @author brocksauvage
	 * This is a private Event object, created from the Event class.
	 */
	private Event theEvent;
	public AllDayEvent(Event e){
		setLayout(null);
		setBorder(BorderFactory.createRaisedBevelBorder());
		setForeground(Color.WHITE);
		theEvent = e;
		JLabel name = new JLabel(e.Name);
		name.setFont(FontManager.getStandardFont());
		name.setBounds(15,5,200,30);
		add(name);
	}

	@Override
	public Event getEvent() {
		// TODO Auto-generated method stub
		return theEvent;
	}

}
