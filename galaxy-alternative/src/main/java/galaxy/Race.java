package galaxy;

public record Race(Id id, String name, TechLevels techLevels, ShipTypes shipTypes) {
	public Race(String name) {
		this(
				new Id(name),
				name,
				new TechLevels(),
				new ShipTypes()
		);
	}

	public Race withShipType(ShipType shipType) {
		return new Race(id, name, techLevels, shipTypes.with(shipType));
	}
}
