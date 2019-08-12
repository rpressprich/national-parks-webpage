package com.techelevator.models;

import java.util.List;

/*
 * Interface that allows us to interact with the database npgeek from table 'park' and pass data to the controller
 */
public interface ParkDao {
	/*
	 * Creates a new park in database npgeek
	 */
	public void createPark(Park park);

	/*
	 * Retrieves every park in database npgeek
	 */
	public List<Park> getParks();
	/*
	 * Gets all park information for each park (parkCode) from park table in database npgeek
	 */
	public Park getParkByCode(String parkCode);
	/*
	 * Updates park information for each park (parkCode) from park table in database npgeek
	 */

	public void updatePark(Park park);

	/*
	 * Deletes a park entry in database npgeek
	 */
	public void deletePark(String parkCode);
}
