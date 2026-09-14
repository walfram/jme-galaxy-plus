package galaxy.ship;

public record WeaponsOf(Integer guns, Double caliber) implements Weapons {

	@Override
	public Double mass() {
		return new WeaponsMass(this).value();
	}
}
