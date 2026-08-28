package galaxy;

import java.util.UUID;

public record Id(String value) {

	public Id(UUID uuid) {
		this(uuid.toString());
	}

}
