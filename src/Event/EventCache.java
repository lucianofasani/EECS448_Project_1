package Event;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;




public class EventCache {


	JSONArray events;
	private static EventCache sInstance = null;
	private EventCache(){
		events = new JSONArray();

		
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
	}

}
