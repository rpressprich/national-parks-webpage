package com.techelevator.models;

public class WeatherReport {
	private String parkCode;
	private int dayForecast;
	private int low;
	private int high;
	private String forecast;

	public WeatherReport(String parkCode, int dayForecast, int low, int high, String forecast) {
		this.parkCode = parkCode;
		this.dayForecast = dayForecast;
		this.low = low;
		this.high = high;
		this.forecast = forecast;
	}

	public WeatherReport() {
	}

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public int getDayForecast() {
		return dayForecast;
	}

	public void setDayForecast(int dayForecast) {
		this.dayForecast = dayForecast;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	@Override
	public String toString() {
		return "WeatherReport [parkCode=" + parkCode + ", dayForecast=" + dayForecast + ", low=" + low + ", high="
				+ high + ", forecast=" + forecast + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dayForecast;
		result = prime * result + ((parkCode == null) ? 0 : parkCode.hashCode());
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
		WeatherReport other = (WeatherReport) obj;
		if (dayForecast != other.dayForecast)
			return false;
		if (parkCode == null) {
			if (other.parkCode != null)
				return false;
		} else if (!parkCode.equals(other.parkCode))
			return false;
		return true;
	}
}
