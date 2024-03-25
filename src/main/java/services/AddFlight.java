package services;

import io.restassured.*;
import io.restassured.response.Response;
import utilities.Constants;
import utilities.Reuseable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.json.simple.parser.ParseException;

public class AddFlight extends Reuseable {

	public static void flightAddition(Properties p) throws IOException, ParseException {	

		// Updating addFlightRequest json
	/*	byte[] b = Files.readAllBytes(Paths.get(Constants.requestBody_AddFlight));
		String addFlightRequest = new String(b);

		byte[] b1 = Files.readAllBytes(Paths.get(Constants.responseBody_SelectedFlight));
		selectedFlightResponse = new String(b1);

		selectedFlightResponseUpdated = prepareAPIRequestDynamically(addFlightRequest, serachRq,
				selectedFlightResponse); 
		
		//updateJson_addFlightRequest(); */
		
		System.out.println("Security Token wtout: " + SecurityToken);
		System.out.println("Security Token: " + "\""+SecurityToken +"\"");
		
		File requestBodyFile = new File(Constants.requestBody_AddFlight);

		// POST request
		Response response = RestAssured.given().baseUri("https://qa1-flights2.np.flydubai.com")
				.contentType("application/json")
				.header("SecurityToken", SecurityToken)
				.body(requestBodyFile) //.body(selectedFlightResponseUpdated)				
				.post("/api/itinerary/AddFlight");		

		// Retrieve and print response status code
		int statusCode = response.getStatusCode();
		System.out.println("Response Status Code: " + statusCode);
		
		// Retrieve and print response body
				String responseBody = response.getBody().asString();
				System.out.println("Add Flight Response Body:");
				System.out.println(responseBody);

		// writing Response into a json file
		FileWriter file = new FileWriter(Constants.responseBody_AddFlight);
		file.write(response.getBody().prettyPrint());
		file.flush();
		file.close();

	}
}
