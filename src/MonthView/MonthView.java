package MonthView;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Views.Views;

public class MonthView extends JPanel implements Views {
	
	/**
	 * 
	 */
	private static final long serialVersionUID 	= 7422166790444563256L;
	
	private static MonthView sInstance 			= null;
	private MonthViewGrid current  				= null;

	private MonthView(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS) );

		current = new MonthViewGrid();
		
		add( new MonthViewMenuBar() );
		add( current );
		
	}

	/**
	 * Singleton
	 */
	public static MonthView getInstance(){
		if(sInstance == null){
			sInstance = new MonthView();
		}
		return sInstance;
	}
	

	@Override
	public void update() {
		remove(current);
		current = new MonthViewGrid();
		add(current);
		invalidate();
		validate();
	}
	

}