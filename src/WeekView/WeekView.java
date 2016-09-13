package WeekView;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Views.Views;

public class WeekView extends JPanel implements Views {
	
	/**
	 * 
	 */
	private static final long serialVersionUID 	= 7422166790444563256L;
	
	private static WeekView sInstance 			= null;
	private WeekViewGrid current  				= null;

	private WeekView(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS) );

		current = new WeekViewGrid();
		
		add( new WeekViewMenuBar() );
		add( current );
		
	}

	/**
	 * Singleton
	 */
	public static WeekView getInstance(){
		if(sInstance == null){
			sInstance = new WeekView();
		}
		return sInstance;
	}
	

	@Override
	public void update() {
		remove(current);
		current = new WeekViewGrid();
		add(current);
		invalidate();
		validate();
	}
	

}
