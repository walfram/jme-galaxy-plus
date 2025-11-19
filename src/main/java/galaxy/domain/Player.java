package galaxy.domain;

import galaxy.domain.planet.Planet;

public interface Player {
	void exec(Command command, Planet planet);
}
