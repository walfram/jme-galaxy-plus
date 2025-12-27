package galaxy.core.team;

import galaxy.core.Component;

public record TeamRef(String value) implements Component {

	public TeamRef(TeamRef other) {
		this(other.value);
	}

	public boolean isHostileTo(TeamRef other) {
		return true;
	}

}
