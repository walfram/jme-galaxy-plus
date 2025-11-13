package galaxy.jme.generator;

import com.simsilica.lemur.Container;
import galaxy.generator.SeedSource;

public interface SeedSourceConfiguration {
	void initControls(Container container);

	SeedSource seedSource();
}
