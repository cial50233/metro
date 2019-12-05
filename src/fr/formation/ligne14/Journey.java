package fr.formation.ligne14;

public class Journey {
	private Integer duration;
	private Station departure;
	private Station arrival;
	
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Station getDeparture() {
		return departure;
	}
	public void setDeparture(Station departure) {
		this.departure = departure;
	}
	public Station getArrival() {
		return arrival;
	}
	public void setArrival(Station arrival) {
		this.arrival = arrival;
	}
	@Override
	public String toString() {
		return "Journey [duration=" + duration + ", departure=" + departure + ", arrival=" + arrival + "]-----";
	}
	


}
