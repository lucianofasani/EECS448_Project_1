package YearView;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Views.Views;

public class YearView extends JPanel implements Views {
	
	/**
	 * 
	 */
	private static final long serialVersionUID 	= 7422166790444563256L;
	
	private static YearView sInstance 			= null;
	private YearViewGrid current  				= null;
	private YearViewMenuBar currentBar 			= null;

	private YearView(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS) );

		current = new YearViewGrid();
		
		currentBar = new YearViewMenuBar();
		add( currentBar );
		add( current );
		
	}

	/**
	 * Singleton
	 */
	public static YearView getInstance(){
		if(sInstance == null){
			sInstance = new YearView();
		}
		return sInstance;
	}
	

	@Override
	public void update() {
		remove(current);
		remove(currentBar);
		currentBar = new YearViewMenuBar();
		current = new YearViewGrid();
		add(currentBar);
		add(current);
		invalidate();
		validate();
	}
	

}
