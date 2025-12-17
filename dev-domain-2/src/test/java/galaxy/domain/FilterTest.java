package galaxy.domain;

import galaxy.domain.planet.PlanetRef;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class FilterTest {

	@Test
	void test_return_planets_for_race_with_ships_on_planets() {
		Entity p0 = new Entity(new PlanetRef("foo"), new TeamRef("foo"));
		Entity p1 = new Entity(new PlanetRef("bar"), new TeamRef("bar"));
		Entity p2 = new Entity(new PlanetRef("baz"), new TeamRef("baz"));

		Entity p3 = new Entity(new PlanetRef("p3"));
		Entity p4 = new Entity(new PlanetRef("p4"));

		Context galaxy = new ClassicGalaxy(p0, p1, p2, p3, p4);

		List<Entity> view = galaxy.query(List.of(PlanetRef.class));

		assertEquals(5, view.size());

		assertTrue(view.contains(p0));
		assertFalse(view.contains(p1));
		assertFalse(view.contains(p2));
		assertFalse(view.contains(p3));
		assertFalse(view.contains(p4));

		boolean hasFoo = view.stream().anyMatch(e -> Objects.equals("foo", e.prop(PlanetRef.class).value()));
		assertTrue(hasFoo);
	}

	@Test
	void test_return_planets_for_race_initial() {

	}

}
