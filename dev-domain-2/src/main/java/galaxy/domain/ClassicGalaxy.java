package galaxy.domain;

import galaxy.domain.planet.PlanetId;
import galaxy.domain.ship.ShipId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ClassicGalaxy implements Context {

	private final List<Entity> entities = new ArrayList<>();

	public ClassicGalaxy(Entity... entities) {
		this.entities.addAll(List.of(entities));
	}

	@Override
	public long shipCount(String team) {
		return entities.stream()
				.filter(e -> e.has(ShipId.class))
				.filter(e -> Objects.equals(new TeamRef(team), e.prop(TeamRef.class)))
				.count();
	}

	@Override
	public long planetCount(String team) {
		return entities.stream()
				.filter(e -> e.has(PlanetId.class))
				.filter(e -> Objects.equals(new TeamRef(team), e.prop(TeamRef.class)))
				.count();
	}

	@Override
	public List<Entity> query(Collection<Class<? extends Component>> components) {
		return entities.stream().filter(e -> components.stream().allMatch(e::has)).toList();
	}

	@Override
	public void remove(Collection<Entity> discarded) {
		entities.removeAll(discarded);
	}

}
