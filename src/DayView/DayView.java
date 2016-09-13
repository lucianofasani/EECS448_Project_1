package DayView;

import javax.swing.JPanel;

import Views.Views;

public class DayView extends JPanel implements Views{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5529096929347599975L;

	private static DayView sInstance 			= null;
	
	private DayView(){
		
	}
	
	@Override
	public void update() {
		invalidate();
		validate();
		
	}
	public static DayView getInstance(){
		if(sInstance == null){
			sInstance = new DayView();
		}
		return sInstance;
	}

}
