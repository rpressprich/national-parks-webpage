package com.techelevator.models;
/* 
 * Class to allow us to pass specific data from a SurveyDao to the controller 
 */
public class SurveysPerPark {
	private int surveys;
	private String parkCode;
	
	public SurveysPerPark(int surveys, String parkCode) {
		this.surveys = surveys;
		this.parkCode = parkCode;
	}
	
	public SurveysPerPark() {
	}

	public int getSurveys() {
		return surveys;
	}

	public void setSurveys(int surveys) {
		this.surveys = surveys;
	}

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	@Override
	public String toString() {
		return "SurveysPerPark [surveys=" + surveys + ", parkCode=" + parkCode + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parkCode == null) ? 0 : parkCode.hashCode());
		result = prime * result + surveys;
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
		SurveysPerPark other = (SurveysPerPark) obj;
		if (parkCode == null) {
			if (other.parkCode != null)
				return false;
		} else if (!parkCode.equals(other.parkCode))
			return false;
		if (surveys != other.surveys)
			return false;
		return true;
	}
}
