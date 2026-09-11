package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Planet;

import java.util.List;
import java.util.Objects;

record Location(State state, Planet origin, Planet destination, double distanceRemaining) {
	
//	private  {
//		this.state = state;
//		this.origin = origin;
//		this.destination = destination;
//		this.distanceRemaining = distanceRemaining;
//	}
	
	public Location(Planet origin, Planet destination) {
		this(State.HYPERSPACE, origin, destination, origin.distanceTo(destination));
	}

	public Location(Planet origin) {
		this(State.ORBIT, origin, null, 0.0);
	}

	public Location(JsonNode src, List<Planet> planets) {
		this(
				State.valueOf(src.get("state").asText()), 
				planets.stream().filter(p -> Objects.equals(src.get("originId").asText(), p.id().value())).findFirst().orElseThrow(),
				planets.stream().filter(p -> Objects.equals(src.path("destId").asText(), p.id().value())).findFirst().orElse(null),
				src.path("distanceRemaining").asDouble()
		);
	}

	public boolean inOrbit() {
		return state == State.ORBIT;
	}

	public Location sendTo(Planet destination) {
		return new Location(origin, destination);
	}

	public boolean inHyperspace() {
		return state == State.HYPERSPACE;
	}
}
