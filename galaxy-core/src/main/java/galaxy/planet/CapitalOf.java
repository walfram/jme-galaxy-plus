package galaxy.planet;

public record CapitalOf(double value) implements Capital {
	@Override
	public Industry toIndustry() {
		return new Industry(value);
	}
}
