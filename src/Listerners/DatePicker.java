package Listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;

import Event.EventPlanner;
import MonthView.MonthViewDay;
import Records.CachedCalendar;

public class DatePicker implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		MonthViewDay label = (MonthViewDay)arg0.getSource();
		int dayOfMonth = label.getDay();
		Calendar cal = Calendar.getInstance();
		cal.set(CachedCalendar.getInstance().Year, CachedCalendar.getInstance().Month, dayOfMonth); //Year, month and day of month
		Date date = cal.getTime();
		System.out.println(date.toString());
		
		new EventPlanner(date);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
