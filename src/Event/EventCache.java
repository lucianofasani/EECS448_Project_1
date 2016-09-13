package Event;

import org.json.simple.JSONObject;




public class EventCache {


	private static EventCache sInstance = null;
	private EventCache(){
		

		
	}
	public static EventCache getInstance(){
		if(sInstance == null){
			sInstance = new EventCache();
		}
		return sInstance;
	}
	

	public void addEvent(JSONObject event){

	}

}
