package galaxy;

import java.util.UUID;

// Race identity
public final class Id {
	private final String value;

	public Id(String value) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("Race ID cannot be empty");
		}
		this.value = value;
	}

	public Id(UUID uuid) {
		this(uuid.toString());
	}

	public String value() {
		return this.value;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Id other && this.value.equals(other.value);
	}

	@Override
	public int hashCode() {
		return this.value.hashCode();
	}
}
