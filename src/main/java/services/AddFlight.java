package services;

import io.restassured.*;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import utilities.Constants;
import utilities.Reuseable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class AddFlight extends Reuseable {

	public static void flightAddition(Properties p) throws IOException {	

		 // Enable SSL certificate verification
	     //  RestAssured.useRelaxedHTTPSValidation();	
		
		System.out.println("Security Token wtout: " + SecurityToken);
		System.out.println("Security Token: " + "\""+SecurityToken +"\"");
		
		File requestBodyFile = new File(Constants.requestBody_AddFlight);

		// POST request
		Response response = RestAssured.given()
				.relaxedHTTPSValidation()
				.baseUri("https://qa1-flights2.np.flydubai.com")
				.contentType("application/json")
				.header("SecurityToken", SecurityToken)
				.body(requestBodyFile) 				
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
