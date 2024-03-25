package testsuite;

import java.awt.AWTException;
import java.io.*;
import java.text.ParseException;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import services.AddFlight;
import services.SearchFlight;
import utilities.Constants;
import utilities.Reuseable;


public class PNRTest extends Reuseable {
	
	@BeforeTest
	public void Setup()
	{
		deletePreviousJSONResponses();
	}

	
	@Test
	public void PNR_Creation() throws IOException, InterruptedException, ParserConfigurationException, SAXException, AWTException, ParseException, org.json.simple.parser.ParseException {

		BufferedReader reader = new BufferedReader(new FileReader(Constants.settingsFileLoc));
		Properties properties = new Properties();
        properties.load(reader);
        
		SearchFlight.flightSearch(properties);
		AddFlight.flightAddition(properties);
		
		reader.close();			

	}
	
}
