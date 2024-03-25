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

public class SearchFlight extends Reuseable {

	public static void flightSearch(Properties p) throws IOException, ParseException {
	    
	    File requestBodyFile = new File(Constants.requestBody_SearchFlight);

	    //POST request
	     Response response = RestAssured.given()
	    .baseUri(p.getProperty("baseUrl"))
	    .header(p.getProperty("headerName"), p.getProperty("headerValue"))
	    .body(requestBodyFile)
	    .post(p.getProperty("endpoint_SearchFlight"));

	    // Retrieve and print response body
	    String responseBody = response.getBody().asString();
	    System.out.println("Response Body:");
	    System.out.println(responseBody);

	    // Retrieve and print response status code
	    int statusCode = response.getStatusCode();
	    System.out.println("Response Status Code: " + statusCode);
	    
	    //read data from SearchFlight Request JSON file then store in byte array
	     byte[] b = Files.readAllBytes(Paths.get(Constants.requestBody_SearchFlight));	      
	     serachRq = new String(b);	 
	     
	    //retrieving security token from  SearchFlight Response header
	    SecurityToken = response.getHeader("SecurityToken").toString();	  
	    
	    //writing  SearchFlight Response into json file
	    FileWriter file = new FileWriter(Constants.responseBody_SearchFlight);
	    file.write(response.getBody().prettyPrint());
	    file.flush();
	    file.close();
		
	}
}
