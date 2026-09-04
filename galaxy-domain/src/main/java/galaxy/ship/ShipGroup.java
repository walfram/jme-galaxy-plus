package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ShipGroup {

	private final Id id;

	private final Owner owner;
	private final ShipType shipType;
	private final TechLevels techLevels;
	private final int size;

	private CargoType cargoType;
	private double cargoWeight;

	public ShipGroup(Race owner, ShipType shipType, int size) {
		this(
				new Id(UUID.randomUUID()),
				new Owner(owner),
				owner.techLevels(),
				shipType,
				size
		);
	}

	public ShipGroup(Id id, Owner owner, TechLevels techLevels, ShipType shipType, int size) {
		this.id = id;
		this.owner = owner;
		this.shipType = shipType;
		this.techLevels = new TechLevels(techLevels);
		this.size = size;
	}

	public ShipGroup(JsonNode src, List<ShipType> shipTypes) {
		this(
				new Id(src.path("id").asText()),
				new Owner(src.path("owner").asText()),
				new TechLevels(src.path("tech")),
				filteredShipType(shipTypes, src.path("type").asText()),
				src.path("size").asInt()
		);
	}

	private static ShipType filteredShipType(List<ShipType> shipTypes, String name) {
		return shipTypes.stream()
				.filter(type -> Objects.equals(type.name(), name))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Unknown ShipType %s".formatted(name)));
	}

	public Owner owner() {
		return owner;
	}

	public void upgradeTechLevels(TechLevels techLevels) {

	}

	public ShipType shipType() {
		return shipType;
	}

	public TechLevels techLevels() {
		return techLevels;
	}

	public int size() {
		return size;
	}

	public Id id() {
		return id;
	}

	public double weight() {
		return size * shipType.mass();
	}

	public void loadCargo(CargoType cargoType, double weight) {
		double maxWeight = size * shipType.effectiveCargoWeight();
		if (weight > maxWeight) {
			throw new IllegalArgumentException("Cargo weight exceeds max weight");
		}

		this.cargoType = cargoType;
		this.cargoWeight = weight;
	}

	public double cargoWeight() {
		return cargoWeight;
	}

	public double speed() {
		return shipType.speed(techLevels.engines(), cargoWeight / size);
	}

	public double maxCargoWeight() {
		return shipType.effectiveCargoWeight();
	}

	public double maxFlightDistance() {
		return 40.0 * techLevels().engines();
	}
}
