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
import javax.swing.JCheckBox;
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
	
	private static final Integer[] dayToSpan_integers = new Integer[31];
	
	private static final String[] repeat_strings = {"Weekly", "Biweekly", "Monthly"};
		
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
	
	/**
	 * added by Denae
	 *  Initialize the days to span string array
	 */
	static{
		for(int i = 0; i < 31; i++){
			dayToSpan_integers[i] = (i+1);
		}
	}

	private JComboBox<String> startTime;
	private JComboBox<String> endTime;
	private JComboBox<Integer> daysToSpan; //JCombo to pick number of days to span
	private JComboBox<String> repeatType; //i.e. weekly, biweekly, monthly
	private JCheckBox Sunday;
	private JLabel startTimeLabel;
	private JLabel endTimeLabel;
	private JLabel daysToSpanLabel;
	private JTextField nameText;
	private JRadioButton timeRange;
	private JRadioButton allDay;
	private JRadioButton multiDay;
	private JRadioButton repeat;
	private JRadioButton day; //Sun, Mon,...,Sat
	private JRadioButton date; //1,2,...,30 || 31 || 28
	private JTextArea descriptionText;

	
	private static final int TEXT_BOX_LENGTH = 300;

	private static final int DESCRIPTION_Y = 375;
	private static final int DESCRIPTION_HEIGHT = 125;

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
			ButtonGroup repeatGroup = new ButtonGroup();

			allDay = new JRadioButton("All Day.");
			allDay.setBounds(50,90,150,30);
			allDay.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					toggleEnabled(false);
					toggleMultiDay(false);
				}
			});

			timeRange = new JRadioButton("Time Range:");
			timeRange.setBounds(200,90,150,30);
			timeRange.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					toggleEnabled(true);
					toggleMultiDay(false);
				}
			});
			
			//code added by Denae to make radio button for multiDay
			multiDay = new JRadioButton("Mutli-Day.");
			multiDay.setBounds(50,120,90,30);
			multiDay.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {//toggle functions make it to where other options are grayed out when this radio button is selected
					toggleEnabled(false);//gray out time range feature
					toggleMultiDay(true);//ungray the dayspan comboBox and label
				}
			});
			
			/*
			 * Radio buttons for repeating
			 */
			
			repeat = new JRadioButton("Repeat");
			repeat.setBounds(50,160,70,30);
			
			day = new JRadioButton("Day");
			day.setBounds(50,190,46,30);
			
			date = new JRadioButton("Date");
			date.setBounds(300,160,150,30);

			startGroup.add(allDay);
			startGroup.add(timeRange);
			startGroup.add(multiDay);
			
			repeatGroup.add(day);
			repeatGroup.add(date);
		

			add(allDay);
			add(timeRange);
			add(multiDay);
			add(repeat);
			add(day);
			add(date);

			startTimeLabel = new JLabel("Start Time:");
			startTimeLabel.setBounds(50,270,150,30);
			add(startTimeLabel);

			startTime = new JComboBox<String>(time_strings);
			startTime.setBounds(50,300,110,30);
			add(startTime);

			endTimeLabel = new JLabel("End Time:");
			endTimeLabel.setBounds(250,270,150,30);
			add(endTimeLabel);

			endTime = new JComboBox<String>(time_strings);
			endTime.setBounds(250,300,110,30);
			add(endTime);
			
			//added by Denae to make daySpan label
			daysToSpanLabel = new JLabel("Days To Span: ");
			daysToSpanLabel.setBounds(200,120,150,30);
			add(daysToSpanLabel);
			
			//code added by Denae to make daySpan comboBox
			daysToSpan = new JComboBox<Integer>(dayToSpan_integers);
			daysToSpan.setBounds(290,126,40,20);
			add(daysToSpan);
			
			repeatType = new JComboBox<String>(repeat_strings);
			repeatType.setBounds(120,166,80,20);
			add(repeatType);
			
			allDay.setSelected(true);
			toggleEnabled(false);
			toggleMultiDay(false);
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
			submit.setBounds(100, 520, 200, 30);
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
		//multiDay stuff added by Denae
		if(multiDay.isSelected()){
			String[] str_array = mDate.split("-"); //split date string "1-13-2016" on "-" and put into array
			int month = Integer.parseInt(str_array[0]); //change month value in array to int
			int day = Integer.parseInt(str_array[1]); //change day value in array to int
			int year = Integer.parseInt(str_array[2]); //change year value in array to int
			int j = 0;//counter to increment the day
			for(int i = 0; i < daysToSpan.getSelectedIndex()+1; i++){ //add event to days determined by the number of days the user chooses to span
				JSONObject newEvent;
				try{
					newEvent = new JSONObject();
					newEvent.put(Event.NAME_STRING, (String)nameText.getText());
					if(month == 12 && day+j > 31){//this covers the case that the user wants to span across years 2016-2017
						month = 1; //set month to january
						day = 1; //set day to the fist of the month
						j = 0; //restart the counter for day increment
						year = 2017; //set year to next year
					}
					if(month == 2 && day+j > 29){//this case cover February
						month++;
						day = 1;
						j = 0;
					}
					if(month == 4 || month == 6 || month == 8 || month == 10){//this case covers months with 31 days
						if(day+j > 31){
							month++;
							day = 1;
							j = 0;
						}
					}
					if(month == 1 || month == 3 || month == 5 || month == 7 || month == 9 || month == 11){//this case covers months with 30 days
						if(day+j > 30){
							month++;
							day = 1;
							j = 0;
						}
					}
					newEvent.put(Event.DATE_STRING, month +"-"+ (day+j) + "-" + year);
					newEvent.put(Event.START_STRING, "-1");
					newEvent.put(Event.STOP_STRING, "-1");
					newEvent.put(Event.DESC_STRING, (String)descriptionText.getText());
					j++;
					Random r = new Random(System.currentTimeMillis());
					long id = r.nextLong();
					newEvent.put(Event.ID_STRING, id );
				}catch(Exception e){
					throw new IrregularFormatException();
				}
				EventCache.getInstance().addEvent(newEvent);
			}
			JOptionPane.showMessageDialog(this, "Event Saved!","Event Planner",JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			Lock.getLock().release();
			CalendarApp.app.updateCurrentView();
		}//ends multiDay stuff
		
		else{//old team's original code for event creation
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
		}//end old team's code
	}
	
	private void toggleEnabled( boolean on ){
		startTime.setEnabled(on);
		endTime.setEnabled(on);
		endTimeLabel.setEnabled(on);
		startTimeLabel.setEnabled(on);
	}
	
	private void toggleMultiDay( boolean on ){
		daysToSpanLabel.setEnabled(on); //when multiday is selected these will be true
		daysToSpan.setEnabled(on);
		repeat.setEnabled(!on); //when multiday is selected change all these to be false
		date.setEnabled(!on);
		date.setSelected(!on);
		day.setEnabled(!on);
		day.setSelected(!on);
		repeatType.setEnabled(!on);
	}
	
	private void toggleRepeat( boolean on ){
		day.setEnabled(on);
		date.setEnabled(on);
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
