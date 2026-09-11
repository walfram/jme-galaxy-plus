package galaxy.planet;

public class DetachedColonists implements Colonists {
	private final Colonists source;

	public DetachedColonists(Colonists source) {
		this.source = source;
	}

	@Override
	public Double value() {
		return source.value();
	}
}
