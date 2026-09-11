package galaxy.planet;

import galaxy.Planet;

public class ColonistsTransfer {
	private final Planet source;
	private final Colonists requested;

	public ColonistsTransfer(Planet source, Colonists requested) {
		this.source = source;
		this.requested = requested;
	}

	public Planet planet() {
		return new ReducedColonists(source, requested);
	}

	public Colonists colonists() {
		return new DetachedColonists(requested);
	}
}
