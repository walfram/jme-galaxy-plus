package galaxy.planet;

import galaxy.Planet;
import galaxy.Scalar;

public class PopulationReduced implements Scalar<Double>  {
	private final Planet source;
	private final Colonists colonists;

	public PopulationReduced(Planet source, Colonists colonists) {
		this.source = source;
		this.colonists = colonists;
	}

	@Override
	public Double value() {
		return source.population() - colonists.value() * 8.0;
	}
}
