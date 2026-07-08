package domain.planet;

import domain.planet.properties.*;
import domain.production.CapitalProduction;

public class ClassicDaughterWorld extends Planet {
	public ClassicDaughterWorld(Long id, Coordinates coordinates) {
		super(id, coordinates, new Size(500), new Resources(10), new Population(500), new Industry(500), new Materials(0));
		startProduction(new CapitalProduction(this));
	}
}
