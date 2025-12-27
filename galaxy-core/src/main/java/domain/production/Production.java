package domain.production;

import java.util.List;

public interface Production {
	void advance(double tpf);

	boolean isComplete();

	List<ProductionResult> complete(ProductionContext context);
}
