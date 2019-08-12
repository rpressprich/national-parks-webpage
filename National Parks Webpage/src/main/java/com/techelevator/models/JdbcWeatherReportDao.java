package com.techelevator.models;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcWeatherReportDao implements WeatherReportDao {
	private JdbcTemplate jdbcTemplate;
	/*
	 * Datasource allows us to connect to the database npgeek with jdbcWeatherReportDao 
	 */
	public JdbcWeatherReportDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void createWeatherReport(WeatherReport weatherReport) {
		String sqlCreateWeatherReport = "insert into weather(parkCode,fivedayforecastvalue,low,high,forecast) values (?,?,?,?,?)";
		jdbcTemplate.update(sqlCreateWeatherReport, weatherReport.getParkCode(), weatherReport.getDayForecast(),
				weatherReport.getLow(), weatherReport.getHigh(), weatherReport.getForecast());
	}

	@Override
	public List<WeatherReport> getWeatherReports() {
		List<WeatherReport> weatherReports = new ArrayList<WeatherReport>();
		String sqlGetWeatherReport = "select * from weather";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetWeatherReport);
		while (results.next()) {
			WeatherReport thisWeatherReport = new WeatherReport();
			thisWeatherReport.setParkCode(results.getString("parkCode"));
			thisWeatherReport.setDayForecast(results.getInt("fivedayforecastvalue"));
			thisWeatherReport.setLow(results.getInt("low"));
			thisWeatherReport.setHigh(results.getInt("high"));
			thisWeatherReport.setForecast(results.getString("forecast"));

			weatherReports.add(thisWeatherReport);
		}
		return weatherReports;
	}

	@Override
	public List<WeatherReport> getWeatherReportsByParkCode(String parkCode) {
		List<WeatherReport> weatherReports = new ArrayList<WeatherReport>();
		String sqlGetWeatherReport = "select * from weather where parkCode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetWeatherReport, parkCode);
		while (results.next()) {
			WeatherReport thisWeatherReport = new WeatherReport();
			thisWeatherReport.setParkCode(results.getString("parkCode"));
			thisWeatherReport.setDayForecast(results.getInt("fivedayforecastvalue"));
			thisWeatherReport.setLow(results.getInt("low"));
			thisWeatherReport.setHigh(results.getInt("high"));
			thisWeatherReport.setForecast(results.getString("forecast"));

			weatherReports.add(thisWeatherReport);
		}
		return weatherReports;
	}

	@Override
	public void updateWeatherReport(WeatherReport weatherReport) {
		String sqlUpdateWeatherReport = "UPDATE weather SET low = ?, high = ?, forecast = ? WHERE parkCode = ? AND fivedayforecastvalue = ?";
		jdbcTemplate.update(sqlUpdateWeatherReport, weatherReport.getLow(), weatherReport.getHigh(),
				weatherReport.getForecast(), weatherReport.getParkCode(), weatherReport.getDayForecast());
	}

	@Override
	public void deleteWeatherReport(String parkCode) {
		String sqlDeleteWeatherReport = "DELETE FROM weather WHERE parkCode = ?";
		jdbcTemplate.update(sqlDeleteWeatherReport, parkCode);
	}
}
