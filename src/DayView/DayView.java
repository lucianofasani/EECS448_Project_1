package DayView;

import javax.swing.JPanel;

import Views.Views;


public class DayView extends JPanel implements Views {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3308272936623729765L;
	
	private static DayView sInstance = null;

	private DayViewCollection current;
	
	private DayView(){
	
		current = new DayViewCollection();
		add(current);
	}
	
	@Override
	public void update() {
		
		invalidate();
		
		remove(current);
		current = new DayViewCollection();
		add(current);
		
		validate();
		repaint();
		
	}
	public static DayView getInstance(){
		if(sInstance == null)
		{
			sInstance = new DayView();
		}
		return sInstance;
	}

}
