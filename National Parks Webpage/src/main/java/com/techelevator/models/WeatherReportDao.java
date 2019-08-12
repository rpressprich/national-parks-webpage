package com.techelevator.models;

import java.util.List;
/*
 * Interface that allows us to interact with the database npgeek from table 'weather' and pass data to the controller
 */
public interface WeatherReportDao {
	/*
	 * Creates weather report in database npgeek
	 */
	public void createWeatherReport(WeatherReport weatherReport);
	/*
	 * Reads all weather reports from database npgeek
	 */
	public List<WeatherReport> getWeatherReports();
	/*
	 * Reads all weather reports for a specific park in database npgeek
	 */
	public List<WeatherReport> getWeatherReportsByParkCode(String parkCode);
	/*
	 * Updates a specific weather report for a park in database npgeek
	 */
	public void updateWeatherReport(WeatherReport weatherReport);
	/*
	 * Deletes all weather reports for a parkCode in database npgeek
	 */
	public void deleteWeatherReport(String parkCode);
}