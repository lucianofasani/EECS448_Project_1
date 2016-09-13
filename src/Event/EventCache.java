package Event;

import java.io.FileNotFoundException;

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

}
