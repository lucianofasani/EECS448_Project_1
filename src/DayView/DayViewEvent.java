package DayView;

import java.awt.Color;
import java.text.ParseException;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Event.DateFormatter;
import Event.Event;
import Styles.FontManager;

public class DayViewEvent extends JPanel implements IDayEvent{

	/**
	 * Comment by Brock Sauvage
	 * This is the section where variable declaration happens.
	 */
	private static final long serialVersionUID = 2318788304336791454L;
	private int day;
	private int month;
	private int year;
	private final Event thisEvent;
	
	/**
	 * Comment by Brock Sauvage
	 * @Brief: Constructor for the class, is able to take in an event that can be viewed.
	 * @param e
	 */
	public DayViewEvent(Event e){
		this.setLayout(null);
		thisEvent = e;
		//this.setBackground(Color.BLACK);
		Border doubleBorder = BorderFactory.createCompoundBorder(
				BorderFactory.createRaisedBevelBorder(), 
				BorderFactory.createLoweredBevelBorder()
				);
	
		setBorder(BorderFactory.createCompoundBorder(
													doubleBorder, 
													BorderFactory.createLineBorder(Color.BLACK)
													));
	
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(DateFormatter.getFormat().parse(e.Date));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
		}
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
		
		JLabel name = new JLabel(e.Name);
		name.setForeground(Color.BLACK);
		name.setBounds(10, 5, 400, 30);
		name.setFont(FontManager.getBoldFont());
		add(name);
		
		JLabel times = new JLabel(e.StartTime+" -> "+e.StopTime);
		times.setForeground(Color.BLACK);
		times.setBounds(30, 45, 400, 30);
		times.setFont(FontManager.getBoldFont());
		add(times);
		

	}

	/**
	 * @Pre: None
	 * @Post: Returns the member variable 'day'
	 * @Return: an int, day.
	 * @return
	 */
	public int getDay() {
		// TODO Auto-generated method stub
		return day;
	}

	/**
	 * @Pre: None
	 * @Post: Returns the member variable 'month'
	 * @Return: Returns an int, month.
	 * @return
	 */
	public int getMonth() {
		// TODO Auto-generated method stub
		return month;
	}

	/**
	 * @Pre: None
	 * @Post: Returns the member variable, 'year'
	 * @Return: Returns an int, year.
	 * @return
	 */
	public int getYear() {
		// TODO Auto-generated method stub
		return year;
	}
	
	/**
	 * @Pre: None
	 * @Post: Returns the current event that the DayViewEvent object is holding.
	 * @Return: Returns an event object, thisEvent.
	 */
	@Override
	public Event getEvent(){
		return thisEvent;
	}

}
