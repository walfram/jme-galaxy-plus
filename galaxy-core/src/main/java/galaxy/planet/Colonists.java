package galaxy.planet;

import galaxy.Transportable;

public interface Colonists extends Transportable {

	double value();

	Population toPopulation();
}
