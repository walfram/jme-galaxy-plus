package galaxy.planet;

public record State(Owner owner, Production production) {
	public State() {
		this(null, null);
	}
}
