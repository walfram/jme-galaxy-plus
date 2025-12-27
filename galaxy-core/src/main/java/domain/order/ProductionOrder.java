package domain.order;

import domain.Component;
import domain.production.Production;

public record ProductionOrder(Production production) implements Component {
}
