package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;

import model.Route;
import model.Stage;
import model.Trip;
import model.TripCollection;
import util.Converter;

/**
 * Application that finds most popular routes in trip collection.
 *
 * @author Mix
 */
public class App {

	/**
	 * Application start point (main program). This method reads data from input file,
	 * finds most popular routes according to input parameters, and writes result into output file.
	 * @param args Main program arguments. It is array of 2 to 4 strings. 
	 * First string represents input file location and second is output file location.
	 * Third and fourth strings are optional. Third is minimum route length (minimum number of stages in route)
	 * and fourth is required destination in found routes.
	 */
	public static void main(String[] args) {
		
		// If there is less then two arguments, the program exits with code 1
		if(args.length < 2) {
			System.out.println("Specify input and output file!");
			System.exit(1);
		}
		
		String inputFilePath = args[0];
		String outputFilePath = args[1];
	
		File inputFile = new File(inputFilePath); 
		File outputFile = new File(outputFilePath); 
		
		TripCollection tripCollection = new TripCollection();
		
		JsonArray tripsJsonArray = null;
		
		// InputStream and JsonReader is being created in try-with-resources
		try (InputStream is = new FileInputStream(inputFile); 
				JsonReader jsonReader = Json.createReader(is)) {
			
			// Receiving trips from input file
			JsonObject obj = jsonReader.readObject();
			tripsJsonArray = obj.getJsonArray("trips");
		}
		catch (FileNotFoundException e) {
			System.out.println("Input file " + inputFilePath + " not found!");
			System.exit(2);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Converter conv = new Converter();
		
		// adding trips into tripCollection
		for (JsonObject tripJsonObject : tripsJsonArray.getValuesAs(JsonObject.class)) {
			Trip trip = conv.jsonObjectToTrip(tripJsonObject);
			tripCollection.addTrip(trip);
		}
		
		// finding most popular routes according to number of input parameters
		switch(args.length) {
		case 2: 
			tripCollection.findAllRoutes();
			break;
		case 3: 
			tripCollection.findAllRoutes(Integer.parseInt(args[2]));
			break;
		case 4: 
			tripCollection.findAllRoutes(Integer.parseInt(args[2]), args[3]);
			break;
		}
		
		// finding best routes
		List<Route> bestRoutes = tripCollection.findBestRoute();

		// config Map is created for pretty printing.
		Map<String, Boolean> config = new HashMap<>();
		// Pretty printing feature is added.
		config.put(JsonGenerator.PRETTY_PRINTING, true);
		// PrintWriter and JsonWriter is being created in try-with-resources
		try (PrintWriter pw = new PrintWriter(outputFile);
				JsonWriter jsonWriter = Json.createWriterFactory(config).createWriter(pw)) {
			// creating output JSON object
			JsonObject jsonObject = Json.createObjectBuilder()
					.add("most_popular_routes", conv.routesToJsonArray(bestRoutes, tripCollection.getMaxRoutePopularity()))
					.build();
			// writing result into output file
			jsonWriter.writeObject(jsonObject);

		}
		catch (FileNotFoundException e) {
			System.out.println("Output file " + outputFilePath + " not found!");
		}
	}

}
