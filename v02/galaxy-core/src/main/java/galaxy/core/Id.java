package galaxy.core;

import java.util.UUID;

public record Id(String value) {

	public Id(UUID value) {
		this(value.toString());
	}

}
