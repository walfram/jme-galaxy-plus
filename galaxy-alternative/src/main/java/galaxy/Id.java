package galaxy;

import java.util.UUID;

public record Id(String value)  implements Scalar<String> {
	public Id {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("ID cannot be empty");
		}
	}

	public Id(UUID uuid) {
		this(uuid.toString());
	}

}
