package galaxy.planet;

public record CapitalOf(double quantity) implements Capital {
	@Override
	public Industry toIndustry() {
		return new Industry(quantity);
	}
}
