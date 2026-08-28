package galaxy.ship;

public record ShipType(Engines engines, Weapons weapons, Shields shields, CargoHold cargoHold, String name) {
}
