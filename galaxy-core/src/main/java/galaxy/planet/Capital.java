package galaxy.planet;

import galaxy.Transportable;

public interface Capital extends Transportable {

	double value();

	Industry toIndustry();
}
