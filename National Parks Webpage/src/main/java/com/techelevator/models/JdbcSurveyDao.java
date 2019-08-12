package com.techelevator.models;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcSurveyDao implements SurveyDao {
  
	private JdbcTemplate jdbcTemplate;
	/*
	 * Datasource allows us to connect to the database npgeek with jdbcSurveyDao 
	 */
	public JdbcSurveyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void createSurvey(Survey survey) {
		String sqlCreateSurvey = "INSERT INTO survey_result(surveyId,parkCode,emailAddress,state, activityLevel) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sqlCreateSurvey, getNextId(), survey.getParkCode(), survey.getEmail(), survey.getState(),
				survey.getActivityLevel());
	}
	/*
	 * Interacts with the database and gets the next available survey id
	 */
	private long getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_surveyId')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		Long id = null;
		if (results.next()) {
			id = results.getLong(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next forum post id from sequence");
		}
		return id;
	}

	@Override
	public List<Survey> getSurveys() {
		List<Survey> surveys = new ArrayList<Survey>();
		String sqlGetSurveys = "SELECT * FROM survey_result";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetSurveys);
		while (results.next()) {
			Survey thisSurvey = new Survey();
			thisSurvey.setSurveyId(results.getInt("surveyId"));
			thisSurvey.setParkCode(results.getString("parkCode"));
			thisSurvey.setEmail(results.getString("emailAddress"));
			thisSurvey.setState(results.getString("state"));
			thisSurvey.setActivityLevel(results.getString("activityLevel"));

      surveys.add(thisSurvey);
    }
    return surveys;
  }

  @Override
  public List<SurveysPerPark> getNumOfSurveysForEachPark() {
    List<SurveysPerPark> surveysPerEachPark = new ArrayList<SurveysPerPark>();
    String sqlGetNumOfSurveysForEachPark = "select parkCode, COUNT(surveyId) AS surveys from survey_result GROUP BY parkCode ORDER BY surveys DESC";
    SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetNumOfSurveysForEachPark);
    while (results.next()) {
      surveysPerEachPark.add(new SurveysPerPark(results.getInt("surveys"), results.getString("parkCode")));
    }
    return surveysPerEachPark;
  }

  @Override
  public void updateSurvey(Survey survey) {
    String sqlUpdateSurvey = "UPDATE survey_result SET parkCode = ?, emailAddress = ?, state = ?, activityLevel = ? WHERE surveyId = ?";
    jdbcTemplate.update(sqlUpdateSurvey, survey.getParkCode(), survey.getEmail(), survey.getState(),
        survey.getActivityLevel(), survey.getSurveyId());
  }

	@Override
	public void deleteSurvey(long surveyId) {
		String sqlDeletePark = "DELETE FROM survey_result WHERE surveyId = ?";
		jdbcTemplate.update(sqlDeletePark, surveyId);
	}

	@Override
	public void deleteSurveysByPark(String parkCode) {
		String sqlDeletePark = "DELETE FROM survey_result WHERE parkCode = ?";
		jdbcTemplate.update(sqlDeletePark, parkCode);
	}
}
