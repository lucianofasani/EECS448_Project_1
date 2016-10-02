package Event;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("M-d-yyyy");;
	private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("M-d-yyyy hh:mm:ss");
	public static SimpleDateFormat getFormat(){
		return dateFormatter;
	}
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
