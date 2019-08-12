package com.techelevator.models;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcParkDao implements ParkDao {

  private JdbcTemplate jdbcTemplate;
  private WeatherReportDao weatherReportDao;
  private SurveyDao surveyDao;
  
  	/*
	 * Datasource allows us to connect to the npgeek database with jdbcParkDao 
	 */
  public JdbcParkDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    weatherReportDao = new JdbcWeatherReportDao(dataSource);
    surveyDao = new JdbcSurveyDao(dataSource);
  }

  @Override
  public void createPark(Park park) {
    String sqlCreatePark = "insert into park (parkCode, parkName, state, acreage, elevationFt, miles, noOfCampsites, climate, yearFounded, annualVisitors, quote, quoteSource, description, entryFee, animalSpecies) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    jdbcTemplate.update(sqlCreatePark, park.getParkCode(), park.getParkName(), park.getState(), park.getAcreage(),
        park.getElevationFt(), park.getMiles(), park.getNoOfCampsites(), park.getClimate(), park.getYearFounded(),
        park.getAnnualVisitors(), park.getQuote(), park.getQuoteSource(), park.getDescription(), park.getEntryFee(),
        park.getAnimalSpecies());
  }

  @Override
  public List<Park> getParks() {
    List<Park> parks = new ArrayList<Park>();
    String sqlGetParks = "select * from park";
    SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParks);
    while (results.next()) {
      Park thisPark = new Park();
      thisPark.setParkCode(results.getString("parkCode"));
      thisPark.setParkName(results.getString("parkName"));
      thisPark.setState(results.getString("state"));
      thisPark.setAcreage(results.getInt("acreage"));
      thisPark.setElevationFt(results.getInt("elevationInFeet"));
      thisPark.setMiles(results.getDouble("milesOfTrail"));
      thisPark.setNoOfCampsites(results.getInt("numberOfCampsites"));
      thisPark.setClimate(results.getString("climate"));
      thisPark.setYearFounded(results.getInt("yearFounded"));
      thisPark.setAnnualVisitors(results.getInt("annualVisitorCount"));
      thisPark.setQuote(results.getString("inspirationalQuote"));
      thisPark.setQuoteSource(results.getString("inspirationalQuoteSource"));
      thisPark.setDescription(results.getString("parkDescription"));
      thisPark.setEntryFee(results.getInt("entryFee"));
      thisPark.setAnimalSpecies(results.getInt("numberOfAnimalSpecies"));

      parks.add(thisPark);
    }
    return parks;
  }

  @Override
  public Park getParkByCode(String parkCode) {
    Park park = new Park();
    String sqlGetParks = "select * from park where parkCode = ?";
    SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParks, parkCode);
    if (results.next()) {
      Park thisPark = new Park();
      thisPark.setParkCode(results.getString("parkCode"));
      thisPark.setParkName(results.getString("parkName"));
      thisPark.setState(results.getString("state"));
      thisPark.setAcreage(results.getInt("acreage"));
      thisPark.setElevationFt(results.getInt("elevationInFeet"));
      thisPark.setMiles(results.getDouble("milesOfTrail"));
      thisPark.setNoOfCampsites(results.getInt("numberOfCampsites"));
      thisPark.setClimate(results.getString("climate"));
      thisPark.setYearFounded(results.getInt("yearFounded"));
      thisPark.setAnnualVisitors(results.getInt("annualVisitorCount"));
      thisPark.setQuote(results.getString("inspirationalQuote"));
      thisPark.setQuoteSource(results.getString("inspirationalQuoteSource"));
      thisPark.setDescription(results.getString("parkDescription"));
      thisPark.setEntryFee(results.getInt("entryFee"));
      thisPark.setAnimalSpecies(results.getInt("numberOfAnimalSpecies"));
      return thisPark;
    }
    return park;
  }

  @Override
  public void updatePark(Park park) {
    String sqlUpdatePark = "UPDATE park SET parkName = ?, state = ?, acreage = ?, elevationFt = ?, miles = ?, noOfCampsites = ?, climate = ?, yearFounded = ?, annualVisitors = ?, quote = ?, quoteSource = ?, description = ?, entryFee = ?, animalSpecies = ? WHERE parkCode = ?";
    jdbcTemplate.update(sqlUpdatePark, park.getParkName(), park.getState(), park.getAcreage(), park.getElevationFt(),
        park.getMiles(), park.getNoOfCampsites(), park.getClimate(), park.getYearFounded(), park.getAnnualVisitors(),
        park.getQuote(), park.getQuoteSource(), park.getDescription(), park.getEntryFee(), park.getAnimalSpecies(),
        park.getParkCode());
  }

	@Override
	public void deletePark(String parkCode) {
		weatherReportDao.deleteWeatherReport(parkCode);
		surveyDao.deleteSurveysByPark(parkCode);
		String sqlDeletePark = "DELETE FROM park WHERE parkCode = ?";
		jdbcTemplate.update(sqlDeletePark);
	}
}
