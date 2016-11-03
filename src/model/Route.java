package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Models a route. The route consists of two or more consecutive stages 
 * (or three or more consecutive destinations).
 * 
 * @author Milan Sovic
 */

public class Route {

	/** Route stages */
	private LinkedList<Stage> stages;

	public Route() {
		stages = new LinkedList<>();
	}
	
	public LinkedList<Stage> getStages() {
		return stages;
	}
	public void setStages(LinkedList<Stage> stages) {
		this.stages = stages;
	}
	
	public void addStage(Stage stage) {
		stages.addLast(stage);
	}
	
	public void addStages(LinkedList<Stage> stages) {
		this.stages.addAll(stages);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stages == null) ? 0 : stages.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		if (stages == null) {
			if (other.stages != null)
				return false;
		} else if (!stages.equals(other.stages))
			return false;
		return true;
	}	
	
	
}
