package fr.formation.ligne14;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Creation de la ligne
		Line line = buildLine("Ligne 14");
		// Insertion des stations de la ligne
		List<Station> stations = buildStations(line, "Saint-Lazare", "Madeleine", "Pyramides", "Chatelet",
				"Gare de Lyon", "Bercy", "Cour Saint-Emilion", "Bibliotheque François-Mitterrand", "Olympiades");
		// Initialisation de la durée entre les stations
		List<Journey> journeys = buildJourneys(5, stations);
		String saisiDepart = "Bercy";
		String saisiArrivee = "Madeleine";
		int durationFinal = showDuration(saisiDepart, saisiArrivee, journeys, stations);
		System.out.println("La durée du trajet entre la station "+ saisiDepart +" et la station "+ saisiArrivee + " est de " + durationFinal + " mins");
	}

	public static Line buildLine(String name) {
		Line line = new Line();
		line.setName(name);
		return line;

	}

	public static List<Station> buildStations(Line line, String... names) {
		List<Station> list = new ArrayList<Station>();
		for (String name : names) {
			Station station = new Station();
			station.setLine(line);
			station.setName(name);
			list.add(station);
		}
		return list;
	}

	public static List<Journey> buildJourneys(Integer duration, List<Station> ls) {

		List<Journey> journeyList = new ArrayList<Journey>();

		for (int i = 0; i < ls.size(); i++) {
			if ((i + 1) < ls.size()) {
				Journey journey = new Journey();
				journey.setDuration(duration);
				journey.setDeparture(ls.get(i));
				journey.setArrival(ls.get(i + 1));
				journeyList.add(journey);
			}
		}

		return journeyList;
	}

	public static int showDuration(String stationOrigin, String stationArrival, List<Journey> list,
			List<Station> stations) {
		boolean include = false;
		int durationFinal = 0;
		Station beginStation = null;
		Station endStation = null;
		List<Journey> listOfJourneys = list;
		List<Station> listOfStations = stations;

		for (Station station : listOfStations) {
			if (station.getName() == stationOrigin) {
				beginStation = station;
			}
			if (station.getName() == stationArrival) {
				endStation = station;
			}
		}

		int posDeparture = stations.indexOf(beginStation);
		int posArrival = stations.indexOf(endStation);
		boolean reverse = false;

		//System.out.println("first : " + posDeparture + " second : " + posArrival);

		if (posDeparture == -1 || posArrival == -1) {
			return durationFinal = 0;
		} else if (posDeparture > posArrival) {

			reverse = true;
			Collections.reverse(listOfJourneys);

		}

		for (Journey journey : listOfJourneys) {
			if (reverse) {
				if (journey.getArrival().equals(beginStation) || include) {
					include = true;
				}
				if ((include) && (!journey.getArrival().equals(endStation))) {
					durationFinal = durationFinal + journey.getDuration();
				} else {
					include = false;
				}

			} else {
				if (journey.getDeparture().equals(beginStation)) {
					include = true;
				}
				if ((include) && (!journey.getDeparture().equals(endStation))) {
					durationFinal = durationFinal + journey.getDuration();
				} else {
					include = false;
				}
			}
		}

		//System.out.println(listOfJourneys);
		return durationFinal;
	}

}
