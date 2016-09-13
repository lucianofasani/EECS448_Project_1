package FileOperations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileIO {

	
	public static synchronized void writeToFile(String filepath, Object data){
		FileWriter fw;
		BufferedWriter bw;
		try {
			fw = new FileWriter(filepath);
			bw = new BufferedWriter(fw);
			String toWrite;
			
			if(data.getClass() == JSONArray.class){

				toWrite = ((JSONArray)data).toString();
			}else if(data.getClass() == JSONObject.class){

				toWrite = ((JSONObject)data).toString();
			}else{
				toWrite = data.toString();
			}
			
			bw.write(toWrite);
			
			
			bw.close();
		} catch (IOException e) {
			// Unlucky
		}
		
		
	}
	public static synchronized JSONArray readToArray(String filepath) throws FileNotFoundException{
		File file = new File(filepath);
		if( !file.exists() || file.isDirectory() ){
			throw new FileNotFoundException();
		}

		JSONArray jsonArray;
		try {
			JSONParser parser = new JSONParser();
			jsonArray = (JSONArray) parser.parse( new FileReader(filepath));
		} catch (ParseException | IOException e) {
			jsonArray = new JSONArray();
		}
		
		return jsonArray;
	}
	public static synchronized String readDate(String filepath) throws FileNotFoundException{
		File file = new File(filepath);
		if( !file.exists() || file.isDirectory() ){
			throw new FileNotFoundException();
		}
		String str ="";
		try {
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			
			str = br.readLine();
			br.close();
		} catch ( IOException e) {
			e.printStackTrace();
		}
		
		return str;
	}

}
