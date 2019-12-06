package fr.formation.ligne14;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Line line = buildLine("Ligne 14");
		List<Station> stations = buildStations(line, "S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9");
		List<Journey> journeys = buildJourneys(5, stations);
		System.out.println(stations.toString());
		System.out.println(journeys.toString());
		Integer durationFinal = showDuration("S9", "S1", journeys);
		System.out.println("------DURATION FINAL------" + durationFinal.toString() + " mins");
	}

	public static Line buildLine(String name) {
		Line line = new Line();
		line.setName(name);
		return line;

	}

	public static List<Station> buildStations(Line line, String... names) {
		List<Station> L = new ArrayList<Station>();
		for (String name : names) {
			Station station = new Station();
			station.setLine(line);
			station.setName(name);
			L.add(station);
		}
		return L;
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

	public static Integer showDuration(String stationOrigin, String stationArrival, List<Journey> list) {
		Boolean rec = false;
		Integer durationFinal = 0;
		String sOrigin = stationOrigin;
		String sArriv = stationArrival;
		List<Journey> ls = list;

		int posDeparture = -1;
		int posArrival = -1;

		for (int i = 0; i < ls.size(); i++) {

			if (ls.get(i).getDeparture().getName() == sOrigin) {
				posDeparture = i;
			}
			if (ls.get(i).getDeparture().getName() == sArriv) {
				posArrival = i;
			}

		}
		if (posDeparture == -1 && (ls.get(ls.size() - 1).getArrival().getName() == sOrigin)) {
			posDeparture = ls.size() - 1;
		}
		if (posArrival == -1 && (ls.get(ls.size() - 1).getArrival().getName() == sArriv)) {
			posArrival = ls.size() - 1;
		}

		System.out.println("first : " + posDeparture + " second : " + posArrival);

		if (posDeparture == -1 || posArrival == -1) {
			durationFinal = 0;
		} else if (posDeparture > posArrival) {
			sOrigin = stationArrival;
			sArriv = stationOrigin;
			Collections.reverse(ls);
			for (int i = 0; i < ls.size(); i++) {
				if (ls.get(i).getArrival().getName() == stationOrigin) {
					rec = true;
				}
				if ((rec) && (ls.get(i).getArrival().getName() != stationArrival)) {
					durationFinal = durationFinal + ls.get(i).getDuration();
				} else {
					rec = false;
				}
			}
		} else {
			for (int i = 0; i < ls.size(); i++) {
				if (ls.get(i).getDeparture().getName() == stationOrigin) {
					rec = true;
				}
				if ((rec) && (ls.get(i).getDeparture().getName() != stationArrival)) {
					durationFinal = durationFinal + ls.get(i).getDuration();
				} else {
					rec = false;
				}
			}
		}
		System.out.println(ls);
		return durationFinal;
	}

}
