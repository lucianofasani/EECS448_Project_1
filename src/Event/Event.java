package Event;
/**
 * 
 * Event container for handling
 *
 */
public class Event {
	public static final String DATE_STRING = "date";
	public static final String START_STRING = "start";
	public static final String STOP_STRING = "end";
	public static final String NAME_STRING = "name";
	public static final String DESC_STRING = "description";
	public static final String ID_STRING = "id";
	public static final String OVERLAP_STRING = "overlap";//added by Denae but not yet used
	public static final String SPAN_STRING = "span";//added by Denae
	
	public String Date,StartTime,StopTime,Name,Description,Overlap;
	public Integer Span;//added by Denae
	public long unique_id;

}
