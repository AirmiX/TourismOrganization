package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Models a trip.
 * This class also includes business logic for finding routes.
 * 
 * @author Milan Sovic
 */

public class Trip {
	
	/** Tourist who takes a trip */
	private Tourist tourist;
	/** Trip destinations */
	private LinkedList<Destination> destinations;
	

	public Trip() {
		destinations = new LinkedList<>();
	}
	
	/**
	 * Finds all stages. 
	 * @return List of found stages.
	 */
	public LinkedList<Stage> findStages() {
		LinkedList<Stage> stages = new LinkedList<>();
		ListIterator<Destination> listIterator = destinations.listIterator();
		Destination lastDestination = null;
		while (listIterator.hasNext()) {
			Destination destination = listIterator.next();
			if(lastDestination != null) {
				Stage stage = new Stage(lastDestination, destination);
				stages.addLast(stage);
			}
			lastDestination = destination;
		}
		return stages;
	}
	
	/**
	 * Finds all routes with two stages. 
	 * @return List of found routes.
	 */
	public List<Route> findRoutes() {	
		List<Route> routes = new ArrayList<>();
		LinkedList<Stage> stages = findStages();
		ListIterator<Stage> listIterator = stages.listIterator();
		Stage lastStage = null;
		while (listIterator.hasNext()) {
			Stage stage = listIterator.next();
			if(lastStage != null) {
				Route route = new Route();
				route.addStage(lastStage);
				route.addStage(stage);
				routes.add(route);
			}
			lastStage = stage;
		}
		return routes;
		
	}
	
	/**
	 * Finds all routes with minimum <code>minLength</code> stages.
	 * @param minLength Minimum number of stages in route
	 * @return List of found routes.
	 */
	public List<Route> findRoutes(int minLength) {
		List<Route> routes = new ArrayList<>();
		LinkedList<Stage> stages = findStages();
		for(int length = minLength; length <= stages.size(); length++) {
			ListIterator<Stage> listIterator = stages.listIterator();
			LinkedList<Stage> routeStages = new LinkedList<>();
			while (listIterator.hasNext()) {
				Stage stage = listIterator.next();
				routeStages.addLast(stage);
				if(routeStages.size() == length) {
					Route route = new Route();
					route.addStages(routeStages);
					routes.add(route);
					routeStages.removeFirst();
				}
			}
		}
		return routes;
	}
	
	/**
	 * Finds all routes with minimum <code>minLength</code> stages that includes 
	 * <code>destinationName</code> destination.
	 * @param minLength Minimum number of stages in route
	 * @param destinationName Name of required destination
	 * @return List of found routes.
	 */
	public List<Route> findRoutes(int minLength, String destinationName) {
		List<Route> routes = new ArrayList<>();
		LinkedList<Stage> stages = findStages();
		for(int length = minLength; length <= stages.size(); length++) {
			ListIterator<Stage> listIterator = stages.listIterator();
			LinkedList<Stage> routeStages = new LinkedList<>();
			while (listIterator.hasNext()) {
				Stage stage = listIterator.next();
				routeStages.addLast(stage);
				if(routeStages.size() == length) {
					if(includesDestination(routeStages, destinationName)) {
						Route route = new Route();
						route.addStages(routeStages);
						if(!routes.contains(route)) {
							routes.add(route);
						}
					}	
					routeStages.removeFirst();
				}
			}
		}
		return routes;
	}

	/**
	 * Tells if any of the stages includes destination with a name <code>destination</code>.
	 * @param stageList List of stages where we look up for destination 
	 * @param destinationName Name of the destination that we look up for
	 * @return If any of the stages includes destination with the name <code>destination</code>.
	 */
	public boolean includesDestination(LinkedList<Stage> stageList, String destinationName) {
		for(Stage stage : stageList) {
			if(stage.getStartDestination().getName().contains(destinationName) ||
					stage.getEndDestination().getName().contains(destinationName)) {
				return true;
			}
		}
		return false;
	}
	
	public Tourist getTourist() {
		return tourist;
	}
	public void setTourist(Tourist tourist) {
		this.tourist = tourist;
	}
	public LinkedList<Destination> getDestinations() {
		return destinations;
	}
	public void setDestinations(LinkedList<Destination> destinations) {
		this.destinations = destinations;
	}

	public void addDestination(Destination destination) {
		destinations.add(destination);
	}

}
