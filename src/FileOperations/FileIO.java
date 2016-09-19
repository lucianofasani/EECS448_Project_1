package FileOperations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileIO {

	/**
	 * Write object to file
	 * @param filepath
	 * @param data to write
	 */
	public static synchronized void writeToFile(String filepath, Object data){

		BufferedWriter bw;
		try {

			bw = new BufferedWriter(new FileWriter(filepath));
			
			String toWrite = data.toString();
			
			bw.write(toWrite);
			
			bw.close();
		} catch (IOException e) {
			// Unlucky
		}
	}
	/**
	 * 
	 * @param filepath
	 * @return JSONArray filled with events from file
	 * @throws FileNotFoundException
	 */
	public static synchronized JSONArray readToArray(String filepath) throws FileNotFoundException{
		File file = new File(filepath);
		if( !file.exists() || file.isDirectory() ){
			throw new FileNotFoundException();
		}

		JSONArray jsonArray;
		try {
			JSONParser parser = new JSONParser();
			jsonArray = (JSONArray) parser.parse( new FileReader(filepath) );
		} catch (ParseException | IOException e) {
			jsonArray = new JSONArray();
		}
		
		return jsonArray;
	}
	/**
	 * 
	 * @param filepath
	 * @return the cached date from file
	 * @throws FileNotFoundException
	 */
	public static synchronized String readDate(String filepath) throws FileNotFoundException{
		File file = new File(filepath);
		if( !file.exists() || file.isDirectory() ){
			throw new FileNotFoundException();
		}
		
		String str ="";
		try {

			BufferedReader br = new BufferedReader( new FileReader(filepath) );
			
			str = br.readLine();
			br.close();
		} catch ( IOException e) {
			e.printStackTrace();
		}
		
		return str;
	}

}
