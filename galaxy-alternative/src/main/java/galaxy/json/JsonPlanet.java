package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import galaxy.Coordinates;
import galaxy.CoordinatesOf;
import galaxy.Planet;
import galaxy.decorators.CapitalOf;
import galaxy.planet.ColonistsOf;
import galaxy.planet.Capital;
import galaxy.planet.Colonists;

import java.util.Optional;

public class JsonPlanet implements Planet {
	private final JsonNode src;

	public JsonPlanet(JsonNode src) {
		this.src = src;
	}

	@Override
	public String id() {
		return src.get("id").asText();
	}

	@Override
	public Coordinates coordinates() {
		return new CoordinatesOf(
				src.get("x").asDouble(),
				src.get("y").asDouble()
		);
	}

	@Override
	public double size() {
		return src.get("size").asDouble();
	}

	@Override
	public double resources() {
		return src.get("resources").asDouble();
	}

	@Override
	public double industry() {
		return src.path("industry").asDouble();
	}

	@Override
	public double population() {
		return src.path("population").asDouble();
	}

	@Override
	public double materials() {
		return src.path("materials").asDouble();
	}

	@Override
	public String name() {
		return src.get("name").asText();
	}

	@Override
	public Optional<String> owner() {
		return Optional.ofNullable(src.get("owner")).map(JsonNode::asText);
	}

	@Override
	public Planet withPopulation(double population) {
		ObjectNode copy = src.deepCopy();
		copy.put("population", population);
		return new JsonPlanet(copy);
	}

	@Override
	public Planet withOwnerId(String owner) {
		ObjectNode copy = src.deepCopy();
		copy.put("owner", owner);
		return new JsonPlanet(copy);
	}

	@Override
	public Capital capital() {
		return new CapitalOf(this);
	}

}
