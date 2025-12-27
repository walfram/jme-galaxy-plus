package galaxy.shared;

import domain.planet.properties.Coordinates;

public class FormattedCoordinates {
	private final Coordinates coordinates;

	public FormattedCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public String value() {
		return "%.02f %.02f %.02f".formatted(coordinates.x(), coordinates.y(), coordinates.z());
	}
}
