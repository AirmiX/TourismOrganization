package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Represents collection of all trips and their routes.
 * This class also includes business logic for finding routes in collection of trips.
 * 
 * @author Milan Sovic
 */

public class TripCollection {

	/** Collection of all trips */
	private List<Trip> trips;
	/** All found routes and their popularity */
	private Map<Route, Integer> routes;
	/** Maximum route popularity */
	int maxRoutePopularity;
	
	public TripCollection() {
		trips = new ArrayList<>();
		routes = new HashMap<>();
	}

	
	/**
	 * Finds all routes with two stages and their popularity, in collection of trips. 
	 * The result is placed in <code>routes</code> attribute. <br>
	 * We iterate trough collection of trips and do following in each iteration:
	 * <ul>
	 * 	<li>Find all routes for current trip</li>
	 * 	<li>Iterate trough found routes and check if the current route is contained in a map of routes.
	 * 		If it is contained, we increase the route popularity. On the contrary, we put it in the map 
	 * 		and set its popularity to 1.
	 * 	</li>
	 * </ul>
	 */
	public void findAllRoutes() {
		for(Trip trip : trips) {
			List<Route> tripRoutes = trip.findRoutes();
			for(Route route : tripRoutes) {
				if(!routes.containsKey(route)) {
					routes.put(route, 1);
				}
				else {
					routes.put(route, routes.get(route) + 1);
				}
			}
		}
	}
	
	/**
	 * Finds all routes with minimum <code>minLength</code> stages and their popularity, in collection of trips.
	 * The result is placed in <code>routes</code> attribute. <br>
	 * We iterate trough collection of trips and do following in each iteration:
	 * <ul>
	 * 	<li>Find all routes for current trip</li>
	 * 	<li>Iterate trough found routes and check if the current route is contained in a map of routes.
	 * 		If it is contained, we increase the route popularity. On the contrary, we put it in the map 
	 * 		and set its popularity to 1.
	 * 	</li>
	 * </ul>
	 * @param minLength Minimum number of stages in route
	 */
	public void findAllRoutes(int minLength) {
		for(Trip trip : trips) {
			List<Route> tripRoutes = trip.findRoutes(minLength);
			for(Route route : tripRoutes) {
				if(!routes.containsKey(route)) {
					routes.put(route, 1);
				}
				else {
					routes.put(route, routes.get(route) + 1);
				}
			}
		}
	}
	
	/**
	 * Finds all routes with minimum <code>minLength</code> stages, that includes 
	 * <code>destinationName</code> destination, and their popularity, in collection of trips.
	 * The result is placed in <code>routes</code> attribute. <br>
	 * We iterate trough collection of trips and do following in each iteration:
	 * <ul>
	 * 	<li>Find all routes for current trip</li>
	 * 	<li>Iterate trough found routes and check if the current route is contained in a map of routes.
	 * 		If it is contained, we increase the route popularity. On the contrary, we put it in the map 
	 * 		and set its popularity to 1.
	 * 	</li>
	 * </ul>
	 * @param minLength Minimum number of stages in route
	 * @param destinationName Name of required destination
	 */
	public void findAllRoutes(int minLength, String destinationName) {
		for(Trip trip : trips) {
			List<Route> tripRoutes = trip.findRoutes(minLength, destinationName);
			for(Route route : tripRoutes) {
				if(!routes.containsKey(route)) {
					routes.put(route, 1);
				}
				else {
					routes.put(route, routes.get(route) + 1);
				}
			}
		}
	}
	
	/**
	 * Finds best route in a map of routes and their popularity. <br>
	 * Firstly, we check for the maximum route popularity of all routes,
	 * then we find maximum number of stages in most popular routes,
	 * and finally filter only routes with maximum popularity and maximum number of stages.
	 * @return List of most popular routes with maximum number of stages.
	 */
	public List<Route> findBestRoute() {
		List<Route> bestRoutes = new ArrayList<Route>();
		int maxRoutePopularity = 0;
		for (Integer popularity : routes.values()) {
		    if(popularity > maxRoutePopularity) {
		    	maxRoutePopularity = popularity;
		    }
		}
		this.maxRoutePopularity = maxRoutePopularity;
		int maxRouteLength = 0;
		for (Map.Entry<Route, Integer> entry : routes.entrySet()) {
		    Integer popularity = entry.getValue();
		    Route route = entry.getKey();
	    	int routeLength = route.getStages().size();
		    if(popularity == maxRoutePopularity) {
		    	if(routeLength > maxRouteLength) {
		    		maxRouteLength = routeLength; 
		    	}
		    }
		}
		for (Map.Entry<Route, Integer> entry : routes.entrySet()) {
		    Integer popularity = entry.getValue();
		    Route route = entry.getKey();
	    	int routeLength = route.getStages().size();
		    if(popularity == maxRoutePopularity && routeLength == maxRouteLength) {
		    	bestRoutes.add(route);
		    }
		}
		return bestRoutes;
	}
	
	public List<Trip> getTrips() {
		return trips;
	}
	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}	
	public Map<Route, Integer> getRoutes() {
		return routes;
	}
	public void setRoutes(Map<Route, Integer> routes) {
		this.routes = routes;
	}
	public int getMaxRoutePopularity() {
		return maxRoutePopularity;
	}
	public void setMaxRoutePopularity(int maxRoutePopularity) {
		this.maxRoutePopularity = maxRoutePopularity;
	}

	public void addTrip(Trip trip) {
		trips.add(trip);
	}
	
}
