package galaxy.dev.apps.generator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import galaxy.core.GameState;
import galaxy.core.Planet;
import galaxy.core.Race;
import galaxy.core.state.ClassicGalaxy;
import galaxy.generator.ClassicGeneratedPlanets;
import org.slf4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.slf4j.LoggerFactory.getLogger;

public class GalaxyGeneratorApp {

	private static final Logger logger = getLogger(GalaxyGeneratorApp.class);

	private final String configPath;

	public GalaxyGeneratorApp(String[] args) {
		Properties props = new Properties();
		try {
			props.load(new StringReader(args[0]));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		this.configPath = props.getProperty("config");
	}

	// expected usage GalaxyGeneratorApp config=/path/to/gameStateConfig.json
	public static void main(String[] args) {
		logger.info("GalaxyGeneratorApp, args = {}", Arrays.asList(args));

		if (args.length == 0) {
			throw new IllegalArgumentException("Config path is required, usage: GalaxyGeneratorApp config=/path/to/gameStateConfig.json");
		}

		GalaxyGeneratorApp app = new GalaxyGeneratorApp(args);
		try {
			app.run();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		logger.info("GalaxyGeneratorApp, done");
	}

	private void run() throws IOException {
		logger.info("generating galaxy");

		ObjectMapper mapper = new ObjectMapper();

		JsonNode root = mapper.readTree(new FileReader(this.configPath));

		List<Race> races = races(root.path("races"));
		int planetRatio = root.path("planetRatio").asInt();

		GameState generated = new ClassicGeneratedGalaxy(races, planetRatio).generate();

		for (Race race: generated.races()) {
			logger.info("race = {}", race);
		}

		for (Planet planet: generated.planets()) {
			logger.info("planet = {}", planet);
		}
	}

	private List<Race> races(JsonNode races) {
		List<Race> result = new ArrayList<>(32);

		races.forEach(raceNode -> {
			String name = raceNode.path("name").asText();
			result.add(new Race(name));
		});

		return result;
	}

}
