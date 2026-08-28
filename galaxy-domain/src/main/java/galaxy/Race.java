package galaxy;

import java.util.Objects;

public class Race {

	private final Id id;
	private final TechLevels techLevels = new TechLevels();

	public Race(Id id) {
		this.id = id;
	}

	public Race(String id) {
		this(new Id(id));
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;

		if (!Race.class.isAssignableFrom(other.getClass()))
			return false;

		Race that = (Race) other;

		return this.id.equals(that.id);
	}

	public Id id() {
		return id;
	}

	public TechLevels techLevels() {
		return techLevels;
	}
}
