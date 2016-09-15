package Listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import DayView.DayViewEvent;
import DayView.DayViewEventViewer;
import Exceptions.InstanceOverflowException;

public class EventPicker implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getClickCount() == 2){
			DayViewEvent e = (DayViewEvent)arg0.getSource();

			try {
				DayViewEventViewer.create(e);
			} catch (InstanceOverflowException e1) {
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
