package Event;

import java.awt.Dimension;
import java.util.Date;

import javax.swing.JFrame;

import Temp.Entry;

public class EventPlanner extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5294561201437336422L;

	public EventPlanner(Date dateOn){
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(200,200));
		setResizable(false);
		setLocationRelativeTo(Entry.app);
		setVisible(true);
		
	}

}
