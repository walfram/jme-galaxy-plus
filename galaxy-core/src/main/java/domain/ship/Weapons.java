package domain.ship;

import domain.Component;

public record Weapons(int guns, double caliber) implements Component {
}
