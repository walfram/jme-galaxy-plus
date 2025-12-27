package galaxy.domain.order;

import galaxy.domain.Component;
import galaxy.domain.production.Production;

public record ProductionOrder(Production production) implements Component {
}
