package MonthView;

import javax.swing.JPanel;

import Views.Views;

public class MonthView extends JPanel implements Views{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5529096929347599975L;

	private static MonthView sInstance 			= null;
	
	private MonthView(){
		
	}
	
	@Override
	public void update() {
		invalidate();
		validate();
		
	}
	public static MonthView getInstance(){
		if(sInstance == null){
			sInstance = new MonthView();
		}
		return sInstance;
	}

}
