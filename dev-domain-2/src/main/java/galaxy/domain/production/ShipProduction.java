package galaxy.domain.production;

import java.util.List;

public class ShipProduction implements Production {
	@Override
	public void advance(double tpf) {

	}

	@Override
	public boolean isComplete() {
		return false;
	}

	@Override
	public List<ProductionResult> complete(ProductionContext context) {
		return List.of();
	}
}
