package galaxy.domain;

public record Team(String name) implements Component {
	public TeamRef teamRef() {
		return new TeamRef(name);
	}
}
