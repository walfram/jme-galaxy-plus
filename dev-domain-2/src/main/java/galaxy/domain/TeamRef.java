package galaxy.domain;

public record TeamRef(String value) implements Component {
	public boolean isHostileTo(TeamRef other) {
		return true;
	}
}
