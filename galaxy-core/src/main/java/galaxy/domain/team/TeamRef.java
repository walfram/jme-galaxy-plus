package galaxy.domain.team;

import galaxy.domain.Component;
import galaxy.domain.Entity;

public record TeamRef(String value) implements Component {

	public TeamRef(TeamRef other) {
		this(other.value);
	}

	public boolean isHostileTo(TeamRef other) {
		return true;
	}

}
