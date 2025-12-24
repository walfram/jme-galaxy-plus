package galaxy.domain.phase;

import galaxy.domain.Context;
import galaxy.domain.Entity;
import galaxy.domain.Phase;

import java.util.Collection;

public class VisibilityChangePhase implements Phase {
	private final Context galaxy;

	public VisibilityChangePhase(Context galaxy) {
		this.galaxy = galaxy;
	}

	@Override
	public void execute(double tpf) {
		Collection<Entity> teams = galaxy.teams().values();
		Collection<Entity> planets = galaxy.planets().values();

		for(Entity team : teams) {
			for (Entity planet: planets) {
				// TODO check bombing?
				// TODO check this team's ships at this planet if not owned by this team
			}
		}
	}

}
