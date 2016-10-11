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
import java.util.Calendar;

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
	 * comment added by brocksauvage
	 * No clue as to what this variable does. Appears to be a user ID thing for features that
	 * didn't make it in.
	 */
	private static final long serialVersionUID = 5294561201437336422L;
	

	/**
	 * comment added by brocksauvage
	 * These string arrays act as labels in the UI whenever an event is being created. Used
	 * specifically in the event creation window.
	 */
	private static final String[] time_strings = new String[2*12*4];
	private static final Integer[] dayToSpan_integers = new Integer[31];
	private static final String[] repeat_strings = {"Monthly", "Weekly", "Biweekly"};
	private static final String[] daysOfWeek_strings = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
	private int[] daysSelected = new int[7];

			
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
	 *  Initialize the days to span string array and the dateToRepeat
	 */
	static{
		for(int i = 0; i < 31; i++){
			dayToSpan_integers[i] = (i+1);
		}
	}

	/**
	 * Comment added by Brock Sauvage
	 * This section contains the declaration of the UI components of the event creation window.
	 * Elements included are the time boxes, labels, selection buttons, the text area to show
	 * any events that exist on a given day.
	 */
	private JComboBox<String> startTime;
	private JComboBox<String> endTime;
	private JComboBox<Integer> daysToSpan; //JCombo to pick number of days to span
	private JComboBox<String> repeatType; //i.e. weekly, biweekly, monthly
	private JCheckBox[] days = new JCheckBox[daysOfWeek_strings.length];
	private JLabel startTimeLabel;
	private JLabel endTimeLabel;
	private JLabel daysToSpanLabel;
	private JTextField nameText;
	private JRadioButton timeRange;
	private JRadioButton allDay;
	private JRadioButton multiDay;
	private JRadioButton repeat;
	private JTextArea descriptionText;

	/*
	 * Comment added by Brock Sauvage
	 * These components are responsible for the dimensions of the description box, as well as the 
	 * label for it.
	 */
	
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
	
	/**
	 * Comment By Brock Sauvage
	 * Method written by previous group
	 * @param dateOn
	 * @return
	 * @throws InstanceOverflowException
	 * @Brief: It appears as though this method is responsible for creating an EventPlanner object if
	 * 		   one does not currently exist.
	 */
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
					repeat.setSelected(true);
				}
			});

			timeRange = new JRadioButton("Time Range:");
			timeRange.setBounds(200,90,150,30);
			timeRange.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					toggleEnabled(true);
					toggleMultiDay(false);
					repeat.setSelected(true);
				}
			});
			
			//code added by Denae to make radio button for multiDay
			multiDay = new JRadioButton("Mutli-Day.");
			multiDay.setBounds(50,120,90,30);
			multiDay.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {//toggle functions make it to where other options are grayed out when this radio button is selected
					toggleEnabled(false);//gray out time range feature
					toggleMultiDay(true);//ungray the dayspan comboBox and label
					repeat.setSelected(false);
				}
			});
			
			/*
			 * Radio buttons for repeating
			 */
			
			repeat = new JRadioButton("Repeat");
			repeat.setBounds(50,160,70,30);
			repeat.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(repeat.isSelected() == true){
						toggleRepeat(false);
					}
					else{
						toggleRepeat(true);
					}
				}
			});
			
			
			//checkboxes for day of week initilizations
			for(int i = 0; i < days.length; i++){
				days[i] = new JCheckBox(daysOfWeek_strings[i]);
			}
			days[0].setBounds(70,200,50,20); //Sunday
			days[1].setBounds(120,200,50,20);
			days[2].setBounds(172,200,50,20);
			days[3].setBounds(220,200,55,20); //Wednesday
			days[4].setBounds(275,200,50,20);
			days[5].setBounds(157,240,47,20);
			days[6].setBounds(200,240,50,20);//Saturday

			//checkbox list initializaiton done
			
			
			startGroup.add(allDay);
			startGroup.add(timeRange);
			startGroup.add(multiDay);

			add(allDay);
			add(timeRange);
			add(multiDay);
			add(repeat);
			add(days[0]);
			add(days[1]);
			add(days[2]);
			add(days[3]);
			add(days[4]);
			add(days[5]);
			add(days[6]);

			/**
			 * Comment added by Brock Sauvage
			 * These labels and boxes will appear on the UI in the event adding window.
			 * Each has a set of boundaries that users are required to interact within.
			 * Users may change the values in the boxes, but they cannot go outside of the boundaries.
			 */
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
			
			//code added by Denae to make repeat option combobox
			repeatType = new JComboBox<String>(repeat_strings);
			repeatType.setBounds(120,166,80,20);
			repeatType.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(repeatType.getSelectedIndex() == 0){
						toggleDays(false);
					}
					else{
						toggleDays(true);
					}
				}
			});
			add(repeatType);
						
			allDay.setSelected(true);
			repeat.setSelected(true);
			repeatType.setSelectedIndex(-1);
			toggleEnabled(false);
			toggleMultiDay(false);
		}

		/**
		 * Comment Modified by Brock Sauvage
		 * Event Description Field
		 * This section creates the field responsible for showing all of the events that occur on
		 * any given day, from the event planner window. The descriptionText variable has values
		 * that can be modified to change font, font size, and the boundaries of the text.
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
		Calendar cal = Calendar.getInstance();
		String[] str_array = mDate.split("-"); //split date string "1-13-2016" on "-" and put into array
		int month = Integer.parseInt(str_array[0]); //change month value in array to int
		int day = Integer.parseInt(str_array[1]); //change day value in array to int
		int year = Integer.parseInt(str_array[2]); //change year value in array to int
		//multiDay stuff added by Denae...can't repeat on multiday events
		if(multiDay.isSelected()){
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
					if(month == 4 || month == 6 || month == 7 || month == 8 || month == 10){//this case covers months with 31 days
						if(day+j > 31){
							month++;
							day = 1;
							j = 0;
						}
					}
					if(month == 1 || month == 3 || month == 5 || month == 9 || month == 11){//this case covers months with 30 days
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
		}//ends multiDay code
		
		//begin repeat monthly code
		else if(repeat.isSelected() && repeatType.getSelectedIndex() == 0){
			int j = 0; //month increment
			for(int i = month; i <= 12; i++){ //will go for the duration of the year
				JSONObject newEvent;
				try{
					newEvent = new JSONObject();
					newEvent.put(Event.NAME_STRING, (String)nameText.getText());
					if(day == 31){//if they add something on the 31, lets take the liberty of actually adding it to every month
						if(month+j == 4 || month+j == 6 || month+j == 9 || month+j == 11){//this case covers months with 30 days
							newEvent.put(Event.DATE_STRING, (month+j) +"-"+ (30) + "-" + year);
						}
						else if(month+j == 2 && year == 2016){//this case covers february leap year
							newEvent.put(Event.DATE_STRING, (month+j) +"-"+ (29) + "-" + year);
						}
						else if(month+j == 2 && year == 2017){//this case covers february non leap year
							newEvent.put(Event.DATE_STRING, (month+j) +"-"+ (28) + "-" + year);
						}
						else{
							newEvent.put(Event.DATE_STRING, (month+j) +"-"+ (day) + "-" + year);
						}
					}
					else{//this covers the case for all other day options
						newEvent.put(Event.DATE_STRING, (month+j) +"-"+ (day) + "-" + year);
					}
					if(allDay.isSelected()){
						newEvent.put(Event.START_STRING, "-1");
						newEvent.put(Event.STOP_STRING, "-1");
					}
					else{
						newEvent.put(Event.START_STRING, startTime.getSelectedItem().toString());
						newEvent.put(Event.STOP_STRING, endTime.getSelectedItem().toString());
					}
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
			JOptionPane.showMessageDialog(this, "Event Saved For the duartion of this year!","Event Planner",JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			Lock.getLock().release();
			CalendarApp.app.updateCurrentView();
		}//ends repeat monthly code
		
		else if(repeat.isSelected() && repeatType.getSelectedIndex() == 1){//if repeat weekly
			getDaysSelected();
			cal.set(year, month-1, day); //this line returns the week number of the day you clicked on
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); //this line stores that week in an int
			int j = dayOfWeek-1; //increments the daysSelected array
			while(month <= 12){
				if(daysSelected[j] != -1){//if day is one of the days the user selected to repeat on, add event
					JSONObject event;
					try{
						event = new JSONObject();
						event.put(Event.NAME_STRING, (String)nameText.getText());
						event.put(Event.DATE_STRING, month +"-"+ (day) + "-" + year);
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
					day++;
					cal.set(year, month-1,day-1);
					int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);//max number of days in current month
					//this first set of ifs checks to see if we're at the end of the month
					if (day > maxDay && month != 12) {
						month++;
						day = 1;
					}
					else if(month == 12 && day > 31){
						break;
					}
					if(j == 6){//if we're at the end of the week, start back over on Sunday
						j = 0;	
					}
					else{//go to the next day of the week
						j++;
					}
				}
				else{//if day is not one of the days the user selected, go to the next day
					day++;
					cal.set(year, month-1,day-1);
					int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);//max num of days in current month
					//check if we're at end of the month again
					if(day > maxDay && month != 12){
						month++;			
						day = 1;
					}
					else if(month == 12 && day > 31){
						break;
					}
					if(j == 6){//check if we're at end of the week
						j = 0;	
					}
					else{//otherwise go to the next day of week
						j++;
					}
				}
			}
			JOptionPane.showMessageDialog(this, "Event Saved for the duration of this year!","Event Planner",JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			Lock.getLock().release();
			CalendarApp.app.updateCurrentView();
		}//end repeat weekly code
		
		
		else if(repeat.isSelected() && repeatType.getSelectedIndex() == 2){//repeat biweekly code
			getDaysSelected();
			cal.set(year, month-1, day); //this line returns the week number of the day you clicked on
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); //this line stores that day of week in an int
			int j = dayOfWeek-1; //begins on the day of the week they clicked on to add the event, increments the daysSelected array
			while(month <= 12){
				if(daysSelected[j] != -1){//if day is one of the days the user selected to repeat on, add event
					JSONObject event;
					try{
						event = new JSONObject();
						event.put(Event.NAME_STRING, (String)nameText.getText());
						event.put(Event.DATE_STRING, month +"-"+ (day) + "-" + year);
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
					day++;
					cal.set(year,month-1,day-1);
					int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);//max number of days in current month
					if(day > maxDay && month != 12){
						if(j != 6){//if we're adding events and we're not at the end of the week, keep adding events in the same week of the next month
							day = 1;
							month++;
						}
						else{
							day = 1 + 7;//if we are at the end of a week and done adding events, skip the first week of the next month
							j = 0;
							month++;
						}
					}
					else if(day > maxDay && month == 12){//if we increment day and we're at the end of the year, get out of the loop
						break;
					}
					if(j == 6){//if at the end of a week, add 7 days to skip a week
						day += 7;
						j = 0;
						if(day > maxDay){//if we add 7 days and day becomes greater than the number of days in the month, go to the first Sunday of the next month
							cal.set(year,month,1);
							int firstSunday = cal.get(Calendar.DAY_OF_WEEK);
							day = 1 + (7-firstSunday) + 1;
							j = 0;
							month++;
						}
					}
					else{//if we're not at the end of week, go to the next day in the week
						j++;
					}
				}
				else{//if day isn't one of the days they selected to repeat on, perform the same instructions as above but don't add an event
					day++;
					cal.set(year,month-1,day-1);
					int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);//max num of days in current month
					if(day > maxDay && month != 12){
						if(j != 6){//if we're adding events and we're not at the end of the week, keep adding events in the same week of the next month
							day = 1;
							month++;
						}
						else{
							day = 1 + 7;//if we are at the end of a week and done adding events, skip the first week of the next month
							j = 0;
							month++;
						}
					}
					else if(day > maxDay && month == 12){//if we increment day and we're at the end of the year, get out of the loop
						break;
					}
					if(j == 6){//if at the end of a week, add 7 days to skip a week
						day += 7;
						j = 0;
						if(day > maxDay){//if we add 7 days and day becomes greater than the number of days in the month, go to the first Sunday of the next month
							cal.set(year,month,1);
							int firstSunday = cal.get(Calendar.DAY_OF_WEEK);
							day = 1 + (7-firstSunday) + 1;
							j = 0;
							month++;
						}
					}
					else{//if we're not at the end of week, go to the next day in the week
						j++;
					}
				}
			}
			JOptionPane.showMessageDialog(this, "Event Saved for the duration of this year!","Event Planner",JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			Lock.getLock().release();
			CalendarApp.app.updateCurrentView();
		}//end repeat biweekly code
		
		
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
	
	/**
	 * Comment by Brock Sauvage
	 * @param on, a boolean used to determine whether the toggle will be active
	 * Essentially, this function will be used in order to allow the start and end times in the event
	 * creation window to be modified by the user. 
	 */
	private void toggleEnabled( boolean on ){
		startTime.setEnabled(on);
		endTime.setEnabled(on);
		endTimeLabel.setEnabled(on);
		startTimeLabel.setEnabled(on);
	}
	
	/**
	 * Comment by Brock Sauvage
	 * @param on, a boolean used to determine whether the multiday event toggle is active
	 * Function determines when an event is being set over multiple days, enables the program to allow
	 * a time span for the event, and disables the repeat option, hiding the option to select repeat days
	 * in the user interface.
	 */
	private void toggleMultiDay( boolean on ){
		daysToSpanLabel.setEnabled(on); //when multiday is selected these will be true
		daysToSpan.setEnabled(on);
		repeat.setEnabled(!on); //when multiday is selected don't allow a repeat option
		repeatType.setEnabled(!on);
		days[0].setEnabled(!on);
		days[1].setEnabled(!on);
		days[2].setEnabled(!on);
		days[3].setEnabled(!on);
		days[4].setEnabled(!on);
		days[5].setEnabled(!on);
		days[6].setEnabled(!on);

	}
	
	/**
	 * Comment added by Brock Sauvage
	 * @param on, determines whether the toggling for the repeat days will be active
	 * Brief: This section determines whether or not the days for the "repeat day" option will be displayed
	 * to the user and active for selection. 
	 */
	private void toggleRepeat( boolean on ){ //when repeat is enabled, make days available
		repeatType.setEnabled(!on);
		days[0].setEnabled(!on);
		days[1].setEnabled(!on);
		days[2].setEnabled(!on);
		days[3].setEnabled(!on);
		days[4].setEnabled(!on);
		days[5].setEnabled(!on);
		days[6].setEnabled(!on);
	}
	
	/**
	 * Comment added by Brock Sauvage
	 * @param on, determines whether the option to select certain days in weekly and biweekly mode will
	 * be enabled.
	 */
	private void toggleDays( boolean on ){//only available in weekly and biweekly mode
		days[0].setEnabled(on);
		days[1].setEnabled(on);
		days[2].setEnabled(on);
		days[3].setEnabled(on);
		days[4].setEnabled(on);
		days[5].setEnabled(on);
		days[6].setEnabled(on);
	}
	
	/**
	 * this function gets the days the user selected in repeat mode
	 * if a day is selected it puts the day of the week that day falls on (i.e. 0 for Sunday, 1 for Monday
	 * otherwise it puts a -1 in the array
	 */
	private void getDaysSelected(){
		for(int i = 0; i < days.length; i++){
			if(days[i].isSelected()){
				daysSelected[i] = i;
			}
			else{
				daysSelected[i] = -1;
			}
		}
	}
	
	/**
	 * 
	 * Comment added by Brock Sauvage
	 * Subclass written by previous team.
	 * Brief: A subclass of EventPlanner, it appears that this is responsible for handling errors that
	 * can occur when a user is interacting with the program, such as entering in negative number values.
	 *
	 */
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
