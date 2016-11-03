package model;

/**
 * Models a tourist.
 * 
 * @author Milan Sovic
 */

public class Tourist {

	/** Tourist's name */
	private String name;
	/** Tourist's country of origin */
	private String countryOfOrigin;
	
	public Tourist() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	
}
