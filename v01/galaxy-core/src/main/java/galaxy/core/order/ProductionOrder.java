package galaxy.core.order;

import galaxy.core.Component;
import galaxy.core.production.Production;

public record ProductionOrder(Production production) implements Component {
}
