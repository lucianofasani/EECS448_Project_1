package Frame;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class CalendarApp extends JFrame {
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 142874753849595415L;
	
    public static final int FRAME_WIDTH 		= 700;
    public static final int FRAME_HEIGHT 		= 700;
	
    public static CalendarApp app;
	
    private CalendarApp(){
		initFrame();
		display();
	}
	
    private void initFrame(){
		setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS) );
	}
	
    private void display(){
		pack();
		invalidate();
		validate();
		setVisible(true);
	}
	
	
    public static void main(String[] args){
		app = new CalendarApp();
	}
}
