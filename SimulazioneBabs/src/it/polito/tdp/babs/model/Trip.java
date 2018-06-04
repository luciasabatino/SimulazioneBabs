package it.polito.tdp.babs.model;

import java.time.LocalDateTime;

public class Trip {

	private int tripID;
	private int duration;
	private LocalDateTime startDate;
	private int startStationID;

	private LocalDateTime endDate;
	private int endStationID;

	public Trip(int tripID, int duration, LocalDateTime startDate, int startStationID, LocalDateTime endDate,
			int endStationID) {
		this.tripID = tripID;
		this.duration = duration;
		this.startDate = startDate;
		this.startStationID = startStationID;
		this.endDate = endDate;
		this.endStationID = endStationID;
	}

	public int getTripID() {
		return tripID;
	}

	public void setTripID(int tripID) {
		this.tripID = tripID;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public int getStartStationID() {
		return startStationID;
	}

	public void setStartStationID(int startStationID) {
		this.startStationID = startStationID;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public int getEndStationID() {
		return endStationID;
	}

	public void setEndStationID(int endStationID) {
		this.endStationID = endStationID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tripID;
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
		Trip other = (Trip) obj;
		if (tripID != other.tripID)
			return false;
		return true;
	}	
	
}
