package alt.doman.commands;

import alt.doman.Command;
import alt.doman.Galaxy;
import alt.doman.Race;
import alt.doman.Technology;
import alt.doman.planet.Planet;
import alt.doman.production.TechnologyProduction;

public record ResearchTechnology(Race race, Planet planet, Technology technology) implements Command {

	@Override
	public void invoke(Galaxy galaxy) {
		galaxy.addProduction(new TechnologyProduction(race, planet, technology));
	}

}
