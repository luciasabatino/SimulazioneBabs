package it.polito.tdp.babs.model;

public class Station {

	private int stationID;
	private String name;
	private double lat;
	private double lon;
	private int dockCount;

	public Station(int stationID, String name, double lat, double lon, int dockCount) {
		this.stationID = stationID;
		this.name = name;
		this.lat = lat;
		this.lon = lon;
		this.dockCount = dockCount;
	}

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public int getDockCount() {
		return dockCount;
	}

	public void setDockCount(int dockCount) {
		this.dockCount = dockCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + stationID;
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
		Station other = (Station) obj;
		if (stationID != other.stationID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	
}
