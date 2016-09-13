package WeekView;

import javax.swing.JPanel;

import Views.Views;

public class WeekView extends JPanel implements Views{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6951212514653400318L;

	private static WeekView sInstance = null;
	
	private WeekView(){
		
	}
	
	@Override
	public void update() {
		
		invalidate();
		validate();
		
	}

	public static WeekView getInstance(){
		if(sInstance == null){
			sInstance = new WeekView();
		}
		return sInstance;
	}
}
