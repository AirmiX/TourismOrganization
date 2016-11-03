package util;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.Destination;
import model.Route;
import model.Stage;
import model.Tourist;
import model.Trip;

/**
 * Helper class for converting from JSON to model objects and vice versa.
 * 
 * @author Milan Sovic
 */

public class Converter {

	/** Converts JSON object to trip object from a model.
	 * @param jsonObject JSON object for conversion
	 * @return Converted trip object
	 */
	public Trip jsonObjectToTrip(JsonObject jsonObject) {
		
		Trip trip = new Trip();
		
		Tourist tourist = jsonObjectToTourist(jsonObject.getJsonObject("tourist"));
		trip.setTourist(tourist);
		
		JsonArray destinationsJsonArray = jsonObject.getJsonArray("destinations");
		for(JsonObject destinationJsonObject : destinationsJsonArray.getValuesAs(JsonObject.class)) {
			Destination destination = jsonObjectToDestination(destinationJsonObject);
			trip.addDestination(destination);
		}

		return trip;
	}
	
	/** Converts JSON object to tourist object from a model.
	 * @param jsonObject JSON object for conversion
	 * @return Converted tourist object
	 */
	public Tourist jsonObjectToTourist(JsonObject jsonObject) {
		
		Tourist tourist = new Tourist();
		tourist.setName(jsonObject.getString("name"));
		tourist.setCountryOfOrigin(jsonObject.getString("country_of_origin"));
		return tourist;
	}
	
	/** Converts JSON object to destination object from a model.
	 * @param jsonObject JSON object for conversion
	 * @return Converted destination object
	 */
	public Destination jsonObjectToDestination(JsonObject jsonObject) {
		
		Destination destination = new Destination();
		destination.setName(jsonObject.getString("name"));
		destination.setLatitude(jsonObject.getJsonNumber("latitude").doubleValue());
		destination.setLongitude(jsonObject.getJsonNumber("longitude").doubleValue());
		return destination;
	}
	
	/** Converts trip object from a model to JSON object.
	 * @param trip JSON object for conversion
	 * @return JSON representation of a trip object
	 */
	public JsonObject tripToJsonObject(Trip trip) {
		
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder()
				.add("tourist", touristToJsonObject(trip.getTourist()));
			
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		
		for(Destination destination : trip.getDestinations()) {
			jsonArrayBuilder.add(destinationToJsonObject(destination));
		}
		
		jsonObjectBuilder.add("destinations", jsonArrayBuilder.build());
		
		return jsonObjectBuilder.build();
	}

	/** Converts tourist object from a model to JSON object.
	 * @param tourist JSON object for conversion
	 * @return JSON representation of a tourist object
	 */
	public JsonObject touristToJsonObject(Tourist tourist) {
		
		JsonObject jsonObject = Json.createObjectBuilder()
				.add("name", tourist.getName())
				.add("country_of_origin", tourist.getCountryOfOrigin())
				.build();
		return jsonObject;
	}
	
	/** Converts destination object from a model to JSON object.
	 * @param destination JSON object for conversion
	 * @return	JSON representation of a destination object
	 */
	public JsonObject destinationToJsonObject(Destination destination) {
		
		JsonObject jsonObject = Json.createObjectBuilder()
				.add("name", destination.getName())
				.add("latitude", destination.getLatitude())
				.add("longitude", destination.getLongitude())
				.build();
		return jsonObject;
	}
	
	/** Converts list of route objects from a model to JSON array.
	 * @param routes List of route objects for conversion
	 * @param popularity Routes popularity
	 * @return JSON representation of a route list
	 */
	public JsonArray routesToJsonArray(List<Route> routes, int popularity) {
		
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

		for(Route route : routes) {
			jsonArrayBuilder.add(routeToJsonArray(route, popularity));	
		}
		
		return jsonArrayBuilder.build();
	}
	
	/** Converts route object from a model to JSON array.
	 * @param route Route objects for conversion
	 * @param popularity Route popularity
	 * @return JSON representation of a route object
	 */
	public JsonObject routeToJsonArray(Route route, int popularity) {
		
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder()
				.add("popularity", popularity);
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		
		if(route.getStages() != null && route.getStages().size() > 0 && route.getStages().getFirst() != null) {
			jsonArrayBuilder.add(destinationToJsonObject(route.getStages().getFirst().getStartDestination()));
		}
		
		for(Stage stage : route.getStages()) {
			jsonArrayBuilder.add(destinationToJsonObject(stage.getEndDestination()));
		}
		
		jsonObjectBuilder.add("destinations", jsonArrayBuilder.build());
		
		return jsonObjectBuilder.build();
	}
}
