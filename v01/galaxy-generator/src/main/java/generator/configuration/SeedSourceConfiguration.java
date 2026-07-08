package generator.configuration;

import com.simsilica.lemur.Container;
import generator.SeedSource;

public interface SeedSourceConfiguration {
	void initControls(Container container);

	SeedSource seedSource();
}
