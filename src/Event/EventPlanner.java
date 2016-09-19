package Event;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import CalendarApp.CalendarApp;
import Exceptions.InstanceOverflowException;
import Exceptions.IrregularFormatException;
import Listeners.Lock;
import Styles.FontManager;

public class EventPlanner extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5294561201437336422L;
	

	
	private static final String[] time_strings = new String[2*12*4];
	
	/**
	 *  Initialize the time String array in chronological order
	 */
	static{
		int count = 0;
		for(int t = 0; t<2; t++)
		{
			for(int i = 0; i< 60; i+=15,count++){
				time_strings[count]="12:"+i;
				if(i == 0)
					time_strings[count]+=0;
				if(t==0)
					time_strings[count]+="am";
				else
					time_strings[count]+="pm";
			}
			for(int i = 1; i<12;i++)
			{
				for(int j = 0; j< 60; j+=15,count++){
					time_strings[count]=i+":"+j;
					if(j == 0)
						time_strings[count]+=0;
					if(t==0)
						time_strings[count]+="am";
					else
						time_strings[count]+="pm";
				}
			}
		}

	}

	private JComboBox<String> startTime;
	private JComboBox<String> endTime;
	private JLabel startTimeLabel;
	private JLabel endTimeLabel;
	private JTextField nameText;
	private JRadioButton timeRange;
	private JRadioButton allDay;
	private JTextArea descriptionText;

	
	private static final int TEXT_BOX_LENGTH = 300;

	private static final int DESCRIPTION_Y = 250;
	private static final int DESCRIPTION_HEIGHT = 200;

	private static final int NAME_Y = 45;
	private static final int NAME_HEIGHT = 30;
	
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 400;
	
	private innerSaveClass saveClass;

	private String mDate;

	private static  EventPlanner mInstance;
	
	public static EventPlanner create(Date dateOn) throws InstanceOverflowException{
		if(!Lock.getLock().tryAcquire())
		{
			throw new InstanceOverflowException();
		}
		
		if(mInstance == null ){
			return mInstance = new EventPlanner(dateOn);
		}
		else{
			mInstance.toFront();
			throw new InstanceOverflowException();
		}

	}
	
	private EventPlanner(Date dateOn){

		saveClass = new innerSaveClass();
		mDate = DateFormatter.format(FormatTypes.Date,dateOn);
		
		setTitle("New Event for "+mDate);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);
		setLocationRelativeTo(CalendarApp.app);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				mInstance = null;
				Lock.getLock().release();
			}
		});


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
			nameText.setBorder(BorderFactory.createLoweredBevelBorder());
			nameText.setBounds(50, NAME_Y, TEXT_BOX_LENGTH, NAME_HEIGHT);
			add(nameText);	
		}

		/*
		 * Radio buttons for time
		 */
		{
			ButtonGroup startGroup = new ButtonGroup();

			allDay = new JRadioButton("All Day.");
			allDay.setBounds(50,90,150,30);
			allDay.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					toggleEnabled(false);
				}
			});

			timeRange = new JRadioButton("Time Range:");
			timeRange.setBounds(50,120,150,30);
			timeRange.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					toggleEnabled(true);
				}
			});

			startGroup.add(allDay);
			startGroup.add(timeRange);
		

			add(allDay);
			add(timeRange);

			startTimeLabel = new JLabel("Start Time:");
			startTimeLabel.setBounds(50,145,150,30);
			add(startTimeLabel);

			startTime = new JComboBox<String>(time_strings);
			startTime.setBounds(50,175,110,30);
			add(startTime);

			endTimeLabel = new JLabel("End Time:");
			endTimeLabel.setBounds(250,145,150,30);
			add(endTimeLabel);

			endTime = new JComboBox<String>(time_strings);
			endTime.setBounds(250,175,110,30);
			add(endTime);
			
			allDay.setSelected(true);
			toggleEnabled(false);
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
			descriptionText.setWrapStyleWord(true);
			descriptionText.setFont(FontManager.getStandardFont());
			descriptionText.setBorder(BorderFactory.createLoweredBevelBorder());
			descriptionText.setBounds(50, DESCRIPTION_Y, TEXT_BOX_LENGTH, DESCRIPTION_HEIGHT);
		
			descriptionText.addKeyListener(new KeyListener(){

				@Override
				public void keyPressed(KeyEvent arg0) {
					if( arg0.getKeyCode() == KeyEvent.VK_ENTER){
						saveClass.save();
					}
				
				}

				@Override
				public void keyReleased(KeyEvent arg0) {
					//Intentionally left blank
				}

				@Override
				public void keyTyped(KeyEvent arg0) {
					//Intentionally left blank
				}
			
			});
			add(descriptionText);
		}
		
		/*
		 * Submit Button
		 */
		{
			JButton submit = new JButton("Save");
			submit.setBounds(100, 500, 200, 30);
			submit.addActionListener(saveClass);
			add(submit);
		}

		setVisible(true);
	}
	
	/**
	 * Save the event to JSON and exit this frame
	 * @throws IrregularFormatException
	 */
	@SuppressWarnings("unchecked")
	private void saveAndExit() throws IrregularFormatException{
		JSONObject event;
		try{
			event = new JSONObject();
			event.put(Event.NAME_STRING, (String)nameText.getText());
			event.put(Event.DATE_STRING, mDate);
			if(allDay.isSelected())
			{
				event.put(Event.START_STRING, "-1");
				event.put(Event.STOP_STRING, "-1");
			}else{
				event.put(Event.START_STRING, startTime.getSelectedItem().toString());
				event.put(Event.STOP_STRING, endTime.getSelectedItem().toString());
			}
			event.put(Event.DESC_STRING, (String)descriptionText.getText());
			
			Random r = new Random(System.currentTimeMillis());
			long id = r.nextLong();
			event.put(Event.ID_STRING, id );
		}catch(Exception e){
			throw new IrregularFormatException();
		}

		EventCache.getInstance().addEvent(event);
		JOptionPane.showMessageDialog(this, "Event Saved!","Event Planner",JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
		Lock.getLock().release();
		CalendarApp.app.updateCurrentView();
	}
	
	private void toggleEnabled( boolean on ){
		startTime.setEnabled(on);
		endTime.setEnabled(on);
		endTimeLabel.setEnabled(on);
		startTimeLabel.setEnabled(on);
	}
	private class innerSaveClass implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			save();	
		}
		
		private void save(){
		
			if(nameText.getText().equals("")){
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Please name your event.","Error",JOptionPane.WARNING_MESSAGE);
			}else if(startTime.getSelectedIndex()>endTime.getSelectedIndex()){
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Your event has negative time :( ","Time Machine?",JOptionPane.WARNING_MESSAGE);
			}
			else{
				try {
					saveAndExit();
				} catch (IrregularFormatException d) {
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Please give valid input.","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			mInstance = null;
		}
		
	}


}
