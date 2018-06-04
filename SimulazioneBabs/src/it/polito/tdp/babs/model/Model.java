package it.polito.tdp.babs.model;

import java.time.LocalDate;
import java.util.*;

import it.polito.tdp.babs.db.BabsDAO;

public class Model {
	
	private BabsDAO bdao;
	private List<Station> stations;
	StationIdMap stationIdMap;
	
	public Model() {
		bdao = new BabsDAO();
		stationIdMap = new StationIdMap();
		stations = bdao.getAllStations(stationIdMap);
	}
	
	public List<Trip> getTripsByDate(LocalDate date){
		return bdao.getAllTrips(date);
	}

	public List<CountResult> getTripCounts(LocalDate date) {
		if(getTripsByDate(date).size() == 0) {
			//non ci sono trip per la data selezionata
			return null;
		}
		List<CountResult> result = new ArrayList<CountResult>();
		for(Station station: stations) {
			CountResult cc = new CountResult(station,bdao.getArrivals(station,date),bdao.getDepartures(station,date));
			result.add(cc);
		}
		Collections.sort(result);
		return result;
	}
	
	public List<Station> getStations(){
		return stations;
	}

	public SimulationResult simula(LocalDate date, Double k) {
		//creo una classe separata per la simulazione
		Simulazione sim = new Simulazione(date,k,this);
		SimulationResult res = sim.run();
		return res;
	}

}
