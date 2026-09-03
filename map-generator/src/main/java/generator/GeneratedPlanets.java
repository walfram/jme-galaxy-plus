package generator;

import galaxy.Planet;

import java.util.List;

public interface GeneratedPlanets {
	List<Planet> allPlanets();

	List<List<Planet>> homeworlds();
}
