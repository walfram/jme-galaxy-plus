package galaxy.domain.production;

import galaxy.domain.Context;
import galaxy.domain.Entity;

import java.util.List;

public interface Production {
	void advance(double tpf);

	boolean isComplete();

	List<ProductionResult> complete(ProductionContext context);
}
