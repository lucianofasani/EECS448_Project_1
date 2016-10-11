package Event;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author previous team
 * Comment by Brock Sauvage
 * Brief: This class is essentially responsible for formatting the date and time of a given date.
 *
 */
public class DateFormatter {
	
	//These are the objects from java.text.SimpleDateFormat that are responsible for
	//formating the date correctly.
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("M-d-yyyy");;
	private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("M-d-yyyy hh:mm:ss");
	
	/**
	 * Comment by Brock Sauvage
	 * @Brief: This is simply a getter function in order to get the dateFormatter object.
	 * @return
	 */
	public static SimpleDateFormat getFormat(){
		return dateFormatter;
	}
	
	/**
	 * Comment by Brock Sauvage
	 * @param type
	 * @param date
	 * @return
	 * @Brief: This function takes in a type of format and a date object in order to determine what the format
	 * 		   the date should be returned as, with the type being a string.
	 */
	public static String format(FormatTypes type, Date date){
		switch(type)
		{
		case Date:
			return dateFormatter.format(date);
		case DateTime:
			return dateTimeFormatter.format(date);
		default:
			return dateFormatter.format(date);
		
		}
		
	}
}
