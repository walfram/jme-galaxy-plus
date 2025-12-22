package galaxy.domain.team;

import galaxy.domain.Component;

public record Team(String name) implements Component {
	public TeamRef teamRef() {
		return new TeamRef(name);
	}
}
