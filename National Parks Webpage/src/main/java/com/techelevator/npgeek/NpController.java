package com.techelevator.npgeek;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.models.Park;
import com.techelevator.models.ParkDao;
import com.techelevator.models.Survey;
import com.techelevator.models.SurveyDao;
import com.techelevator.models.SurveysPerPark;
import com.techelevator.models.WeatherReport;
import com.techelevator.models.WeatherReportDao;
/**
 * Provides an overview of the National Parks. Allows the user to click on specific National Park for specific details including 5-day weather forecast. 
 * Polls users on their favorite park and displays a page of survey results.
 * @author Dwane Mitchell, Michelle Brown, Robert Pressprich
 * @version 1.0
 * @see ParkDao 
 * @see SurveyDao
 * @see WeatherReportDao
 */
@Controller
public class NpController {
	/*
	 * Automatically instantiates objects from classes that implement these interfaces 
	 * (jdbcParkDao, jdbcSurveyDao, jdbcWeatherReportDao)
	 */
	@Autowired
	private ParkDao parkDao;
	@Autowired
	private SurveyDao surveyDao;
	@Autowired
	private WeatherReportDao weatherReportDao;
	
	private final String[] ACTIVITY_LEVELS = {"Inactive", "Sedentary", "Active", "Extremely Active"};

	private final String[] STATES = {"Alabama", "Arkansas", "Arizona", "Alaska", "California", "Colorado",
			"Connecticut", "Delaware", "District of Columbia", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
			"Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
			"Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey",
			"New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon",
			"Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah",
			"Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
	/**
	 * Displays all National Parks in the database and allows the user select one for further details and weather information
	 * @param request HttpServletRequest allows us to set variables and pass them to the jsp
	 * @return returns type String, the name of the home page jsp
	 */
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String displayHome(HttpServletRequest request) {
		request.setAttribute("parks", parkDao.getParks());
		/*
		 * Park codes are needed in lowercase in order to reference images
		 */
		List<String> parkCodes = new ArrayList<String>();
		for (Park park : parkDao.getParks()) {
			parkCodes.add(park.getParkCode().toLowerCase());
		}
		request.setAttribute("parkCodes", parkCodes);
		request.setAttribute("pageTitle", "National Parks Home");
		return "home";
	}
	/**
	 * Displays all details contained in the park table and the next five-day forecast
	 * @param parkCode is used to reference a specific park and its weather information
	 * @param request HttpServletRequest allows us to set variables and pass them to the jsp
	 * @param session HttpSession allows to set site-wide variables and have them remembered for entire session. 
	 * @return returns type String, the name of the park details jsp 
	 */
	@RequestMapping(path = "/parkInfo", method = RequestMethod.GET)
	public String displayParkDetails(@RequestParam String parkCode, HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("temperature") == null) { //if the session does not have variable temperature, create it 
			session.setAttribute("temperature", "F");
			session.setAttribute("unusedTemperature", "C");
		}
		request.setAttribute("park", parkDao.getParkByCode(parkCode)); //gets parkCode from parameter and provides park details to the jsp
		request.setAttribute("parkCode", parkCode.toLowerCase()); //gets parkCode in lowercase to reference img names and declares and instantiates it in the jsp
		request.setAttribute("weatherReport", weatherReportDao.getWeatherReportsByParkCode(parkCode));

		List<String> dayNames = new ArrayList<String>(); // Creates a list for the jsp to use on the five day forecast to display the names of the days
		dayNames.add("Today");
		dayNames.add("Tomorrow");
		dayNames.add(LocalDate.now().plusDays(2).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US)); //if today is Thursday, sets the 3rd day name, which references 2 days from today,
																											//as "Saturday"
		dayNames.add(LocalDate.now().plusDays(3).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US)); //This would be "Sunday"
		dayNames.add(LocalDate.now().plusDays(4).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US)); //This would be "Monday"
		request.setAttribute("dayNames", dayNames);

		List<String> forecastMessages = new ArrayList<String>(); //Creates a list for the jsp in order to warn the user of possible risks, and what they should to prepare
		for (WeatherReport report : weatherReportDao.getWeatherReportsByParkCode(parkCode)) {
			String forecast = report.getForecast();
			String forecastMessage = forecast.equals("snow") ? "Pack snowshoes. " //If certain weather conditions are present, set the forecastMessage, otherwise set it to an empty string
					: forecast.equals("rain") ? "Pack rain gear, wear waterproof shoes. "
							: forecast.equals("thunderstorms") ? "Seek shelter, avoid exposed ridges! "
									: forecast.equals("sun") ? "Pack sunblock. " : "";
			if (report.getHigh() > 75) { //if the weather is too hot, add a heat advisory message
				forecastMessage += "Bring an extra gallon of water. ";
			}
			if (report.getHigh() - report.getLow() > 20) { //if the weather fluctuates, add a message
				forecastMessage += "Wear breathable layers. ";
			}
			if (report.getLow() < 20) {  //if the weather is too cold, add a cold advisory message
				forecastMessage += "Danger! Frigid temperatures! ";
			}
			forecastMessages.add(forecastMessage);
		}
		request.setAttribute("forecastMessages", forecastMessages);
		request.setAttribute("pageTitle", parkDao.getParkByCode(parkCode).getParkName() + " Detail");
		return "detailPage";
	}
	/**
	 * Displays a survey form page, allows the user to select their favorite park, and enter personal details
	 * @param request HttpServletRequest allows us to set variables and pass them to the jsp
	 * @return returns type String, the name of the survey form jsp 
	 */
	@RequestMapping(path = "/survey", method = RequestMethod.GET)
	public String displaySurveyForm(HttpServletRequest request, ModelMap modelHolder) {
	  if (!modelHolder.containsAttribute("survey")) {
	      modelHolder.put("survey", new Survey()); 
	    } 
		request.setAttribute("parks", parkDao.getParks());
		request.setAttribute("activityLevels", ACTIVITY_LEVELS); //Allows the jsp to make a drop-down list of activity levels
		request.setAttribute("states", STATES); //Allows the jsp to make a drop-down list of states
		request.setAttribute("pageTitle", "National Parks Survey");
		System.out.println("Starting form"); 
		return "surveyForm";
	}
	/**
	 * Inputs one new survey into database npgeek and redirects the user to /surveyResults 
	 * @param parkCode
	 * @param email
	 * @param state
	 * @param activityLevel
	 * @return String redirect statement to /surveyResults
	 */
	@RequestMapping(path = "/survey", method = RequestMethod.POST)
	public String saveSurvey(@Valid @ModelAttribute Survey survey, @RequestParam String parkCode,
			@RequestParam String email, @RequestParam String state, @RequestParam String activityLevel) {
		surveyDao.createSurvey(new Survey(0, parkCode, email, state, activityLevel)); // surveyDao handles providing a survey id. 0 is a placeholder.
		return "redirect:/surveyResults";
	}
	/**
	 * Displays the most popular park, and how many votes each park code received 
	 * @param request HttpServletRequest allows us to set variables and pass them to the jsp
	 * @return returns type String, the name of the survey results jsp
	 */
	@RequestMapping(path = "/surveyResults", method = RequestMethod.GET)
	public String displaySurveyResults(HttpServletRequest request) {
		List<SurveysPerPark> surveysPerPark = surveyDao.getNumOfSurveysForEachPark();
		request.setAttribute("surveysPerPark", surveysPerPark);
		request.setAttribute("park ", parkDao.getParkByCode(surveysPerPark.get(0).getParkCode()));//Passes the park with the most votes to the jsp and places at the 0 (or top) position 
		request.setAttribute("pageTitle", "National Parks Survey Results");
		return "surveyResult";
	}
	/**
	 * This method allows users to change from fahrenheit to celsius and vice versa
	 * @param session HttpSession allows to set site-wide variables and have them remembered for entire session. 
	 * @param request HttpServletRequest allows us to set variables and pass them to the jsp
	 * @param parkCode is used to redirect them back to the same page 
	 * @return String redirects them back to /parkInfo 
	 */
	@RequestMapping(path = "/changeTemperature", method = RequestMethod.POST)
	public String changeTemp(HttpSession session, HttpServletRequest request, @RequestParam String parkCode) {
		session.setAttribute("temperature", session.getAttribute("temperature").toString().equals("F") ? "C" : "F"); //ternary operator 
		session.setAttribute("unusedTemperature",
				session.getAttribute("temperature").toString().equals("F") ? "C" : "F");
		return "redirect:/parkInfo?parkCode=" + parkCode;
	}
}
