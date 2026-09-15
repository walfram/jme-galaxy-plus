package galaxy;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.ship.ShipType;
import org.jspecify.annotations.Nullable;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class ShipTypes {

	private final Map<String, ShipType> shipTypes;

	public ShipTypes(Map<String, ShipType> shipTypes) {
		this.shipTypes = new HashMap<>(shipTypes);
	}

	public ShipTypes(JsonNode src) {
		this(
				fields(src).map(key -> new ShipType(key, src.get(key))).toList()
		);
	}

	public ShipTypes(List<ShipType> shipTypes) {
		this(
				shipTypes.stream()
						.collect(
								Collectors.toUnmodifiableMap(
										ShipType::name,
										Function.identity()
								)
						)
		);
	}

	public ShipTypes() {
		this(new HashMap<>());
	}

	private static Stream<String> fields(final JsonNode source) {
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(source.fieldNames(), Spliterator.ORDERED), false);
	}

	public ShipType findByName(String shipTypeName) {
		return shipTypes.get(shipTypeName);
	}

	public int size() {
		return shipTypes.size();
	}

	public void add(ShipType shipType) {
		shipTypes.put(shipType.name(), shipType);
	}
}
