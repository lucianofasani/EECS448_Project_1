package DayView;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Event.Event;
import Exceptions.InstanceOverflowException;
import Styles.FontManager;
import Temp.CalendarApp;

public class DayViewEventViewer extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 763604350492381552L;
	
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 400;
	private static  DayViewEventViewer mInstance;
	public static DayViewEventViewer create(DayViewEvent e) throws InstanceOverflowException{

		if(mInstance == null){
			return mInstance = new DayViewEventViewer(e);
		}
		else{
			mInstance.toFront();
			throw new InstanceOverflowException();
		}

	}
	private JTextField startTime;
	private JTextField endTime;
	private JLabel allDay;
	private JLabel startTimeLabel;
	private JLabel endTimeLabel;
	private JTextField nameText;


	private JTextArea descriptionText;
	private final Event eventToShow;
	
	private static final int TEXT_BOX_LENGTH = 300;

	private static final int DESCRIPTION_Y = 250;
	private static final int DESCRIPTION_HEIGHT = 200;

	private static final int NAME_Y = 45;
	private static final int NAME_HEIGHT = 30;
	private DayViewEventViewer(DayViewEvent e)
	{
		eventToShow = e.getEvent();
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				mInstance = null;
			}
		});
		
		setTitle("View Event");
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);
		setLocationRelativeTo(CalendarApp.app);
		/*
		 * Event name field
		 */
		{
			JLabel nameLabel = new JLabel("Event Name:");
			nameLabel.setBounds(50,NAME_Y-40,250,50);
			nameLabel.setFont(FontManager.getStandardFont());
			add(nameLabel);

			nameText = new JTextField();
			nameText.setFont(FontManager.getStandardFont());
			nameText.setText(eventToShow.Name);
			nameText.setBounds(50, NAME_Y, TEXT_BOX_LENGTH, NAME_HEIGHT);
			nameText.setEditable(false);
			add(nameText);	
		}

		/*
		 * Radio buttons for time
		 */
		{
		

			if(!eventToShow.StartTime.equals("-1"))
			{
				startTimeLabel = new JLabel("Start Time:");
				startTimeLabel.setBounds(50,145,150,30);
				add(startTimeLabel);
	
				startTime = new JTextField();
				startTime.setText(eventToShow.StartTime);
				startTime.setEditable(false);
				startTime.setBounds(50,175,110,30);
				add(startTime);
	
				endTimeLabel = new JLabel("End Time:");
				endTimeLabel.setBounds(250,145,150,30);
				add(endTimeLabel);
	
				endTime = new JTextField();
				endTime.setText(eventToShow.StopTime);
				endTime.setEditable(false);;
				endTime.setBounds(250,175,110,30);
				add(endTime);
			}else{
				allDay = new JLabel("All day Event");
				allDay.setBounds(50,145,150,30);
				add(allDay);
				
			}
			
		}

		/*
		 * Event Description Field
		 */
		{
			JLabel descriptionLabel = new JLabel("Brief Event Description:");
			descriptionLabel.setBounds(50,DESCRIPTION_Y-40,250,50);
			descriptionLabel.setFont(FontManager.getStandardFont());
			add(descriptionLabel);

			descriptionText = new JTextArea();
			descriptionText.setLineWrap(true);
			descriptionText.setText(eventToShow.Description);
			descriptionText.setEditable(false);
			descriptionText.setWrapStyleWord(true);
			descriptionText.setFont(FontManager.getStandardFont());
			descriptionText.setBorder(BorderFactory.createRaisedBevelBorder());
			descriptionText.setBounds(50, DESCRIPTION_Y, TEXT_BOX_LENGTH, DESCRIPTION_HEIGHT);
			add(descriptionText);
		}
		
		/*
		 * Submit Button
		 */
		{
			JButton submit = new JButton("Close");
			submit.setBounds(50, 500, 100, 30);
			submit.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					mInstance.dispose();
					mInstance = null;
					
				}
			
			});
			add(submit);
			
			JButton delete = new JButton("Remove");
			delete.setBounds(250, 500, 100, 30);
			delete.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					mInstance.dispose();
					mInstance = null;
					
				}
			
			});
			
			add(delete);
		}
		setVisible(true);
	}



}
