package alt.doman;

import alt.doman.planet.Planet;

public class Ship {
	private final ShipTemplate shipTemplate;
	private final TechnologyLevel technologyLevel;

	private Race owner;

	private Planet location;
	private Planet destination;

	public Ship(ShipTemplate shipTemplate, TechnologyLevel technologyLevel, Race owner, Planet location) {
		this.shipTemplate = shipTemplate;
		this.technologyLevel = new TechnologyLevel(technologyLevel);
		this.owner = owner;
		this.location = location;
		location.addShips(this);
	}

	public TechnologyLevel technologyLevel() {
		return technologyLevel;
	}

	public Planet location() {
		return location;
	}

	public void updateDestination(Planet destination) {
		this.location.removeShips(this);
		this.location = null;
		this.destination = destination;
	}

	public Planet destination() {
		return destination;
	}

}
