package alt.doman.commands;

import alt.doman.Command;
import alt.doman.Galaxy;
import alt.doman.Race;
import alt.doman.planet.Planet;
import alt.doman.production.MaterialsProduction;

public class ProduceMaterials implements Command {
	private final Race race;
	private final Planet planet;

	public ProduceMaterials(Race race, Planet planet) {
		this.race = race;
		this.planet = planet;
	}

	@Override
	public void invoke(Galaxy galaxy) {
		galaxy.addProduction(new MaterialsProduction(race, planet));
	}
}
