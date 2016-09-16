package DayView;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Event.Event;
import Styles.FontManager;

public class AllDayEvent extends JPanel implements IDayEvent{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8486049515518099751L;
	
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
