package com.techelevator.models;

public class Park {
	
	//Declaration of data members
	private String parkCode;
	private String parkName;
	private String state;
	private int acreage;
	private int elevationFt;
	private double miles;
	private int noOfCampsites;
	private String climate;
	private int yearFounded;
	private int annualVisitors;
	private String quote;
	private String quoteSource;
	private String description;
	private int entryFee;
	private int animalSpecies;

	public Park(String parkCode, String parkName, String state, int acreage, int elevationFt, double miles,
			int noOfCampsites, String climate, int yearFounded, int annualVisitors, String quote, String quoteSource,
			String description, int entryFee, int animalSpecies) {
		//Instantiation used to differentiate(this.) the data member from the parameter when they are the same name
		this.parkCode = parkCode;
		this.parkName = parkName;
		this.state = state;
		this.acreage = acreage;
		this.elevationFt = elevationFt;
		this.miles = miles;
		this.noOfCampsites = noOfCampsites;
		this.climate = climate;
		this.yearFounded = yearFounded;
		this.annualVisitors = annualVisitors;
		this.quote = quote;
		this.quoteSource = quoteSource;
		this.description = description;
		this.entryFee = entryFee;
		this.animalSpecies = animalSpecies;
	}

	public Park() {
	}

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getAcreage() {
		return acreage;
	}

	public void setAcreage(int acreage) {
		this.acreage = acreage;
	}

	public int getElevationFt() {
		return elevationFt;
	}

	public void setElevationFt(int elevationFt) {
		this.elevationFt = elevationFt;
	}

	public double getMiles() {
		return miles;
	}

	public void setMiles(double miles) {
		this.miles = miles;
	}

	public int getNoOfCampsites() {
		return noOfCampsites;
	}

	public void setNoOfCampsites(int noOfCampsites) {
		this.noOfCampsites = noOfCampsites;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public int getYearFounded() {
		return yearFounded;
	}

	public void setYearFounded(int yearFounded) {
		this.yearFounded = yearFounded;
	}

	public int getAnnualVisitors() {
		return annualVisitors;
	}

	public void setAnnualVisitors(int annualVisitors) {
		this.annualVisitors = annualVisitors;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getQuoteSource() {
		return quoteSource;
	}

	public void setQuoteSource(String quoteSource) {
		this.quoteSource = quoteSource;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEntryFee() {
		return entryFee;
	}

	public void setEntryFee(int entryFee) {
		this.entryFee = entryFee;
	}

	public int getAnimalSpecies() {
		return animalSpecies;
	}

	public void setAnimalSpecies(int animalSpecies) {
		this.animalSpecies = animalSpecies;
	}

	@Override
	public String toString() {
		return "Park [parkCode=" + parkCode + ", parkName=" + parkName + ", state=" + state + ", acreage=" + acreage
				+ ", elevationFt=" + elevationFt + ", miles=" + miles + ", noOfCampsites=" + noOfCampsites
				+ ", climate=" + climate + ", yearFounded=" + yearFounded + ", annualVisitors=" + annualVisitors
				+ ", quote=" + quote + ", quoteSource=" + quoteSource + ", description=" + description + ", entryFee="
				+ entryFee + ", animalSpecies=" + animalSpecies + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parkCode == null) ? 0 : parkCode.hashCode());
		result = prime * result + ((parkName == null) ? 0 : parkName.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		Park other = (Park) obj;
		if (parkCode == null) {
			if (other.parkCode != null)
				return false;
		} else if (!parkCode.equals(other.parkCode))
			return false;
		if (parkName == null) {
			if (other.parkName != null)
				return false;
		} else if (!parkName.equals(other.parkName))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
}
