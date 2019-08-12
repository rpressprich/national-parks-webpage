package com.techelevator.models;

import java.util.List;

/*
 * Interface that allows us to interact with the database npgeek from table 'survey_result' and pass data to the controller
 */
public interface SurveyDao {
	/*
	 * Creates a new survey in database npgeek
	 */
	public void createSurvey(Survey survey);
	/*
	 * Retrieves every survey in database npgeek
	 */
	public List<Survey> getSurveys();
	/*
	 * Gets the number of surveys for each* park from database npgeek
	 */
	public List<SurveysPerPark> getNumOfSurveysForEachPark();
	/*
	 * Updates an existing survey entry in database npgeek
	 */
	public void updateSurvey(Survey survey);
	/*
	 * Deletes a survey entry  in database npgeek
	 */
	public void deleteSurvey(long surveyId);
	/*
	 * Deletes all surveys within specific park (parkCode) in database npgeek 
	 */
	public void deleteSurveysByPark(String parkCode);
}