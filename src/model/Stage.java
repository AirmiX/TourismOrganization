package model;

/**
 * Models a stage. The stage consists of two consecutive destinations.
 * 
 * @author Milan Sovic
 */
public class Stage {

	/** Stage start destination */
	private Destination startDestination;
	/** Stage end destination */
	private Destination endDestination;
	
	public Stage() {}
	
	public Stage(Destination startDestination, Destination endDestination) {
		super();
		this.startDestination = startDestination;
		this.endDestination = endDestination;
	}

	public Destination getStartDestination() {
		return startDestination;
	}
	public void setStartDestination(Destination startDestination) {
		this.startDestination = startDestination;
	}
	public Destination getEndDestination() {
		return endDestination;
	}
	public void setEndDestination(Destination endDestination) {
		this.endDestination = endDestination;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDestination == null) ? 0 : endDestination.hashCode());
		result = prime * result + ((startDestination == null) ? 0 : startDestination.hashCode());
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
		Stage other = (Stage) obj;
		if (endDestination == null) {
			if (other.endDestination != null)
				return false;
		} else if (!endDestination.equals(other.endDestination))
			return false;
		if (startDestination == null) {
			if (other.startDestination != null)
				return false;
		} else if (!startDestination.equals(other.startDestination))
			return false;
		return true;
	}
	
}
