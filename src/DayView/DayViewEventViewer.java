package DayView;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import CalendarApp.CalendarApp;
import Event.Event;
import Event.EventCache;
import Exceptions.InstanceOverflowException;
import Listeners.Lock;
import Styles.FontManager;

public class DayViewEventViewer extends JFrame{

	/**
	 * Comment by Brock Sauvage
	 * Written by old team
	 * This section is where member variables declaration occurs.
	 */
	private static final long serialVersionUID = 763604350492381552L;
	
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 400;
	private static  DayViewEventViewer mInstance;
	public static DayViewEventViewer create(IDayEvent e) throws InstanceOverflowException{

		if(!Lock.getLock().tryAcquire())
		{
			throw new InstanceOverflowException();
		}
		if(mInstance == null){
			return mInstance = new DayViewEventViewer(e);
		}
		else{
			mInstance.toFront();
			throw new InstanceOverflowException();
		}

	}
	
	//More member variables, primarily the label elements of the event window
	private JTextField startTime;
	private JTextField endTime;
	private JLabel allDay;
	private JLabel startTimeLabel;
	private JLabel endTimeLabel;
	private JTextField nameText;
	private JTextArea descriptionText;
	private final Event eventToShow;
	
	//Member variables that set the dimensions for the event window
	private static final int TEXT_BOX_LENGTH = 300;
	private static final int DESCRIPTION_Y = 250;
	private static final int DESCRIPTION_HEIGHT = 200;
	private static final int NAME_Y = 45;
	private static final int NAME_HEIGHT = 30;
	
	/**
	 * @Pre: Takes in an IDayEvent object
	 * @Post: Takes the event object and displays the information in the UI
	 * @param e
	 */
	private DayViewEventViewer(IDayEvent e)
	{
		eventToShow = e.getEvent();
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				mInstance = null;
				Lock.getLock().release();
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
				allDay.setFont(FontManager.getBoldFont());
				allDay.setBounds(140,110,150,30);
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
					Lock.getLock().release();
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
					Lock.getLock().release();
					EventCache.getInstance().removeEvent(eventToShow);
					CalendarApp.app.updateCurrentView();
					
				}
			
			});
			
			add(delete);
		}
		setVisible(true);
	}



}
