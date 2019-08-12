package com.techelevator.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Survey {
	private int surveyId;
	@NotBlank(message = "Park is required")
	private String parkCode;
	@NotBlank(message = "Email is required")
	@Email(message = "Please enter a valid email address")
	private String email;
	@NotBlank(message = "State is required")
	private String state;
	@NotBlank(message = "Activity Level is required")
	private String activityLevel;

	public Survey(int surveyId, String parkCode, String email, String state, String activityLevel) {
		this.surveyId = surveyId;
		this.parkCode = parkCode;
		this.email = email;
		this.state = state;
		this.activityLevel = activityLevel;
	}

	public Survey() {
	}

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

	@Override
	public String toString() {
		return "Survey [surveyId=" + surveyId + ", parkCode=" + parkCode + ", email=" + email + ", state=" + state
				+ ", activityLevel=" + activityLevel + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + surveyId;
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
		Survey other = (Survey) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (surveyId != other.surveyId)
			return false;
		return true;
	}
}
