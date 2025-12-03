package alt.doman.commands;

import alt.doman.Command;
import alt.doman.Galaxy;
import alt.doman.Race;
import alt.doman.planet.Planet;

public class AssignPlanet implements Command {
	private final Race race;
	private final Planet planet;

	public AssignPlanet(Race race, Planet planet) {
		this.race = race;
		this.planet = planet;
	}

	@Override
	public void invoke(Galaxy galaxy) {
		race.claim(planet);
	}
}
