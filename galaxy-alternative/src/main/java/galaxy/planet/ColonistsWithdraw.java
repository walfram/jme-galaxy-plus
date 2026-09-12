package galaxy.planet;

import galaxy.Planet;

public class ColonistsWithdraw {
	private final Planet source;
	private final Colonists requested;

	public ColonistsWithdraw(Planet source, Colonists requested) {
		this.source = source;
		this.requested = requested;
	}

	public Planet planet() {
		return new PlanetWithReducedColonists(source, requested);
	}

	public Colonists colonists() {
		return new DetachedColonists(requested);
	}
}
