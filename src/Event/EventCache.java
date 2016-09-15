package Event;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import FileOperations.FileIO;




public class EventCache {

	private static final String EVENT_FILE = "Events.txt";

	JSONArray events;
	private static EventCache sInstance = null;
	private EventCache(){
		try{
			events = FileIO.readToArray(EVENT_FILE);
		}catch(FileNotFoundException e){
			events = new JSONArray();
		}
		

		
	}
	public static EventCache getInstance(){
		if(sInstance == null){
			sInstance = new EventCache();
		}
		return sInstance;
	}
	
	@SuppressWarnings("unchecked")
	public void addEvent(JSONObject event){
		events.add(event);
		FileIO.writeToFile(EVENT_FILE, events);
	}
	
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
				
				e.unique_id = (long)theEvent.get(Event.ID_STRING);
				dayEvents.add(e);
			}
			
		}
		return dayEvents;
	}
	
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
