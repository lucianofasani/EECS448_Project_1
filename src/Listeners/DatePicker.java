package Listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import Days.IDay;
import Event.EventPlanner;
import Exceptions.InstanceOverflowException;

/**
 * MouseListener for clicking a date to add an event
 */
public class DatePicker implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getClickCount() == 2) {
			
			IDay label = (IDay)arg0.getSource();
			int dayOfMonth = label.getDay();
			int month = label.getMonth();
			int year = label.getYear();
			 
			Calendar cal = Calendar.getInstance();
			cal.set(year, month, dayOfMonth); //Year, month and day of month
			final Date date = cal.getTime();
			
			try {
				EventPlanner.create(date);
			} catch (InstanceOverflowException e) {
				JOptionPane.showMessageDialog(null, "Please finish what you are doing before creating another.");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//Intentionally blank
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//Intentionally blank
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		//Intentionally blank
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//Intentionally blank
	}

}
