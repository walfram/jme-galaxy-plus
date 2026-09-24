package galaxy.context;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Race;
import galaxy.race.RaceId;

import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class Races {

	private final Map<RaceId, Race> races;

	public Races(Map<RaceId, Race> races) {
		this.races = races;
	}

	public Races(List<Race> races) {
		this(
				races.stream()
						.collect(
								Collectors.toUnmodifiableMap(
										Race::raceId,
										Function.identity()
								)
						)
		);
	}

	public Races(final JsonNode src) {
		this(
				fields(src).map(key -> new Race(key, src.required(key))).toList()
		);
	}

	public int size() {
		return races.size();
	}

	private static Stream<String> fields(final JsonNode source) {
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(source.fieldNames(), Spliterator.ORDERED), false);
	}

	public Race raceById(RaceId raceId) {
		return races.get(raceId);
	}

	public Race raceById(String raceId) {
		return races.get(new RaceId(raceId));
	}
}
