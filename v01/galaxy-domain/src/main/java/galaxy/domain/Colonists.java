package galaxy.domain;

public record Colonists(float value) {
	public Colonists add(float delta) {
		return new Colonists(value + delta);
	}
}
