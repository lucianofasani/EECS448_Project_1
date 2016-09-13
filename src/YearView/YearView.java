package YearView;

import javax.swing.JPanel;

import Views.Views;

public class YearView extends JPanel implements Views{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5529096929347599975L;

	private static YearView sInstance 			= null;
	
	private YearView(){
		
	}
	
	@Override
	public void update() {
		invalidate();
		validate();
		
	}
	public static YearView getInstance(){
		if(sInstance == null){
			sInstance = new YearView();
		}
		return sInstance;
	}

}
