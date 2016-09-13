package DayView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import Temp.CalendarApp;
import Views.Views;


public class DayView extends JPanel implements Views {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3308272936623729765L;

	private static DayView sInstance = null;
	
	private DayView(){
		setLayout( new BorderLayout() );
		setPreferredSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.FRAME_HEIGHT+50));
		setMinimumSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.FRAME_HEIGHT+50));
		setMaximumSize(new Dimension(CalendarApp.FRAME_WIDTH,CalendarApp.FRAME_HEIGHT+50));
		this.setBackground(Color.GRAY);
		
		Component spacer = Box.createRigidArea(new Dimension(CalendarApp.FRAME_WIDTH,50));
		add(spacer, BorderLayout.PAGE_START);
		
		
		JPanel leftBar = new JPanel();
		leftBar.setBackground(Color.RED);
		leftBar.setPreferredSize(new Dimension((int)((1.0/3.0)*CalendarApp.FRAME_WIDTH),CalendarApp.FRAME_HEIGHT));
		leftBar.setMinimumSize(new Dimension((int)((1.0/3.0)*CalendarApp.FRAME_WIDTH),CalendarApp.FRAME_HEIGHT));
		leftBar.setMaximumSize(new Dimension((int)((1.0/3.0)*CalendarApp.FRAME_WIDTH),CalendarApp.FRAME_HEIGHT));
		add(leftBar, BorderLayout.LINE_START);
		
		
		
		
		JScrollPane pane = new JScrollPane();
		pane.setPreferredSize(new Dimension((int)((2.0/3.0)*CalendarApp.FRAME_WIDTH),(int)(CalendarApp.FRAME_HEIGHT)));
		pane.setMinimumSize(new Dimension((int)((2.0/3.0)*CalendarApp.FRAME_WIDTH),(int)(CalendarApp.FRAME_HEIGHT)));
		pane.setMaximumSize(new Dimension((int)((2.0/3.0)*CalendarApp.FRAME_WIDTH),(int)(CalendarApp.FRAME_HEIGHT)));
		
		JScrollBar vertical = pane.getVerticalScrollBar();
		vertical.setUnitIncrement(20);
		InputMap im = vertical.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		im.put(KeyStroke.getKeyStroke("DOWN"), "positiveUnitIncrement");
		im.put(KeyStroke.getKeyStroke("UP"), "negativeUnitIncrement");
		
		JPanel calendarContentPanel = new JPanel();
		calendarContentPanel.setLayout(new GridLayout(24,2));
		calendarContentPanel.setMinimumSize(new Dimension(pane.getWidth(),2*CalendarApp.FRAME_HEIGHT));
		calendarContentPanel.setMaximumSize(new Dimension(pane.getWidth(),2*CalendarApp.FRAME_HEIGHT));
		calendarContentPanel.setPreferredSize(new Dimension(pane.getWidth(),2*CalendarApp.FRAME_HEIGHT));
		calendarContentPanel.setBackground(Color.black);
		JLabel t = new JLabel("test");
		t.setForeground(Color.WHITE);
		calendarContentPanel.add(t);
		t = new JLabel("Test2");
		t.setForeground(Color.WHITE);
		calendarContentPanel.add(t);
		
		pane.setViewportView(calendarContentPanel);
		
		add(pane, BorderLayout.LINE_END);
		
		spacer = Box.createRigidArea(new Dimension(CalendarApp.FRAME_WIDTH,50));
		add(spacer, BorderLayout.PAGE_END);
		
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