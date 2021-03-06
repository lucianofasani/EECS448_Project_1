package Event;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import FileOperations.FileIO;



/**
 * Handling class for scheduled events
 */
public class EventCache {

	private static final String EVENT_FILE = "Events.txt";

	private JSONArray events;
	private static EventCache sInstance = null;
	
	private EventCache(){
		try{
			events = FileIO.readToArray(EVENT_FILE);
		}catch(FileNotFoundException e){
			events = new JSONArray();
		}
	}
	/**
	 * Singleton Instance
	 */
	public static EventCache getInstance(){
		if(sInstance == null){
			sInstance = new EventCache();
		}
		return sInstance;
	}
	
	/**
	 * 
	 * @param event to add to the handler
	 */
	@SuppressWarnings("unchecked")
	public void addEvent(JSONObject event){
		events.add(event);
		FileIO.writeToFile(EVENT_FILE, events);
	}
	/**
	 * 
	 * @param dateString to get dates for
	 * @return List of events on that date
	 */
	public LinkedList<Event> getEventsForDate(String dateString){
		LinkedList<Event> dayEvents = new LinkedList<Event>();
		for(int idx = 0; idx<events.size(); idx++){
			JSONObject theEvent = (JSONObject)events.get(idx);
			boolean match = dateString.equals(theEvent.get(Event.DATE_STRING));
			
			if(match){
				Event e = new Event();
				e.Date = (String)theEvent.get(Event.DATE_STRING);
				e.Name = (String)theEvent.get(Event.NAME_STRING);
				e.StartTime = (String)theEvent.get(Event.START_STRING);
				e.StopTime = (String)theEvent.get(Event.STOP_STRING);
				e.Description = (String)theEvent.get(Event.DESC_STRING);
				//e.Overlap = (String)theEvent.get(Event.OVERLAP_STRING);
				e.unique_id = (long)theEvent.get(Event.ID_STRING);
				dayEvents.add(e);
			}
			
		}
		return dayEvents;
	}
	/**
	 * @param Event to Remove
	 * @return true if the event was removed
	 */
	@SuppressWarnings("unchecked")
	public boolean removeEvent(Event e){
		
		JSONArray newArray = new JSONArray();
		boolean retn = false;
		
		for(int idx = 0; idx<events.size(); idx++){
			JSONObject jo = (JSONObject) events.get(idx);
			long id = (long)jo.get(Event.ID_STRING);
			
			if(id != e.unique_id){
				newArray.add(jo);
	
			}else{
				retn = true;
			}
		}
		events = newArray;
		FileIO.writeToFile(EVENT_FILE, events);
		return retn;
	}

}
