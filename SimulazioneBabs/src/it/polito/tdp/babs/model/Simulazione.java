package it.polito.tdp.babs.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Simulazione {
	
	private LocalDate date;
	private double k;
	private PriorityQueue<Event> pq;
	private Model model;
	private int PICKmiss=0;
	private int DROPmiss=0;
	private Map<Station,Integer> stationCount;
	
	private enum EventType{
	//struttura che associa ad ogni parola chiave un intero crescente
		PICK,DROP;
	}
	
	public Simulazione (LocalDate date, double k, Model model) {
		this.date=date;
		this.k=k;
		this.model=model;
		pq = new PriorityQueue<Event>();
		stationCount  = new HashMap<Station,Integer>();
	}
	
	//coda ordinata per priorità : susseguirsi di eventi che devono essere organizzati
	
	public SimulationResult run() {
		//si occupa anche dell'inizializzazione della coda
		//inseriamo solo gli eventi di pick
		List<Trip> trips = model.getTripsByDate(date);
		
		//Aggiungere gli eventi all pq
		//Gli eventi sono tutti i trip che si verificano nella data specificata
		for(Trip t : trips) {
			pq.add(new Event(EventType.PICK, t.getStartDate(), t));
		}
		//Inizializzo il numero di biciclette per ogni stazione
		for(Station s : model.getStations()) {
			stationCount.put(s, (int)(s.getDockCount()*k));
		}
		
		//Processare gli eventi : iterare sulla coda finchè non diventa vuota
		while(!pq.isEmpty()) {
			Event e = pq.poll();

			switch(e.type) {
			case PICK:
				Station station = model.stationIdMap.get(e.trip.getStartStationID());
				int count = stationCount.get(station);
				if(count>0) {
					//devo controllare che ci sia almeno una bici disponibile
					count--;
					stationCount.put(station, count);
					pq.add(new Event(EventType.DROP, e.trip.getEndDate(),e.trip));
				}
				else {
					//l'utente non è riuscito a prendere la bicicletta
					PICKmiss++;
				}
				break;
				
			case DROP:
				station = model.stationIdMap.get(e.trip.getEndStationID());
				count = stationCount.get(station);
				//posso lasciare una bicicletta se count<dockcount
				if(station.getDockCount()>count) {
					//ci sono ancora dei posti disponibili
					count++;
					stationCount.put(station, count);
				}
				else {
					DROPmiss++;
				}
				break;
			}
		}
		
		//Ritornare il numero di DROPmiss e PICKmiss
			return new SimulationResult(PICKmiss, DROPmiss);
	}
	
	private class Event implements Comparable<Event>{
		EventType type;
		LocalDateTime date;
		Trip trip;
		
		public Event(EventType type, LocalDateTime date, Trip trip) {
			this.type=type;
			this.date=date;
			this.trip=trip;
		}
		
		
		//sempre necessario quando si usa una priority queue
		@Override
		public int compareTo(Event other) {
			return date.compareTo(other.date);
		}
	}
	
}
