package galaxy.domain.ship;

import galaxy.domain.Component;

public record Weapons(int guns, double caliber) implements Component {
}
