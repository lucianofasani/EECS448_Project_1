package DayView;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Event.Event;

public class DayViewEvent extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2318788304336791454L;
	
	public DayViewEvent(Event e){
		//this.setBackground(Color.BLACK);
		Border doubleBorder = BorderFactory.createCompoundBorder(
				BorderFactory.createRaisedBevelBorder(), 
				BorderFactory.createLoweredBevelBorder()
				);
	
		setBorder(BorderFactory.createCompoundBorder(
													doubleBorder, 
													BorderFactory.createLineBorder(Color.BLACK)
													));
		
		JLabel name = new JLabel(e.Name);
		name.setForeground(Color.BLACK);
		add(name);
		
		this.setToolTipText(e.Description);

	}

}
