package Records;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import Event.DateFormatter;
import FileOperations.FileIO;
/**
 * 
 * Container for the date we are actually looking at, not the actual date
 *
 */
public class CachedCalendar {
	/**
	 * Singleton Instance
	 */
	private static CachedCalendar sInstance= null;
	/**
	 * values 0-11
	 */
	public int Month;
	/**
	 * values 1-31
	 */
	public int DayOfMonth;
	/**
	 * 2015,2016
	 */
	public int Year;
	
	
	private static final String DATE_FILE = "Date.txt";
	private CachedCalendar(){
		try {
			Date date = DateFormatter.getFormat().parse(FileIO.readDate(DATE_FILE));
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			Month 		= cal.get(Calendar.MONTH)+1;
			DayOfMonth 	= cal.get(Calendar.DAY_OF_MONTH);
			Year 		= cal.get(Calendar.YEAR);
			
		} catch ( ParseException  | FileNotFoundException e ) {
			Calendar cal = Calendar.getInstance();
			Month 		= cal.get(Calendar.MONTH);
			DayOfMonth 	= cal.get(Calendar.DAY_OF_MONTH);
			Year 		= cal.get(Calendar.YEAR);
		}
	}
	/*
	 * Singleton
	 */
	public static CachedCalendar getInstance(){
		if(sInstance == null){
			sInstance = new CachedCalendar();
		}
		return sInstance;
		
	}
	public void saveToFile()
	{
		FileIO.writeToFile(DATE_FILE, Month+"-"+DayOfMonth+"-"+Year+"\n");
	}

}
