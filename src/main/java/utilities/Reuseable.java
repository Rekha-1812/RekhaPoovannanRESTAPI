package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@SuppressWarnings("deprecation")
public class Reuseable {

	public static String SecurityToken;
	public static String serachRq;
	public static String selectedFlightResponse;
	public static String selectedFlightResponseUpdated;

	public static void deletePreviousJSONResponses() {

		File folder = new File("C:\\workspace\\RestApiAutomation\\src\\main\\java\\responseJsonFiles");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].getName().contains(".json") && ! listOfFiles[i].getName().contains("selectedFlightResponse") || ! listOfFiles[i].getName().contains("addFlightResponse")) {
				listOfFiles[i].delete();
			}
		}
	}

	public static String prepareAPIRequestDynamically(String request, String... args) {

		for (int i = 0; i < args.length; i++) {
			request.replace("#" + i + "#", args[i]);
		}

		return request;
	}

	@SuppressWarnings({ "unchecked" })
	public static void updateJson_searchFlightRequest() throws FileNotFoundException, IOException, ParseException {

		ObjectMapper mapper = new ObjectMapper();
		String jsonPath = Constants.requestBody_SearchFlight;
		/*JSONParser jsonParser = new JSONParser();
		JSONObject obj = (JSONObject) jsonParser.parse(new FileReader(jsonPath));
		obj.put("date", "05/29/2024");
		obj.put("dest", "PRG");
		obj.put("origin", "DXB");
		ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
		writer.writeValue(Paths.get(jsonPath).toFile(), obj);*/
		
		JsonNode root = mapper.readTree(new File(jsonPath));
	
        ObjectNode sc = (ObjectNode) root.path("searchCriteria");
        sc.put("date", "05/29/2024");
        String resultUpdate = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
        
        FileWriter file = new FileWriter(Constants.responseBody_SearchFlight);
	    file.write(resultUpdate);
	    file.flush();
	    file.close();
        
		
		System.out.println("searchFlightRequest json updated .........");

	}
	

}
