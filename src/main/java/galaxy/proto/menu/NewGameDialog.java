package galaxy.proto.menu;

import com.simsilica.lemur.*;
import com.simsilica.lemur.component.SpringGridLayout;
import com.simsilica.lemur.core.VersionedList;
import com.simsilica.lemur.core.VersionedReference;
import com.simsilica.lemur.style.ElementId;
import galaxy.ai.Personality;
import jme3utilities.SimpleControl;

import java.util.List;
import java.util.stream.IntStream;

public class NewGameDialog extends Container {

	private final Spinner<Integer> raceCount;

	private final Spinner<Integer> planetsPerRace;
	private final VersionedReference<Integer> raceCountRef;
	private final Container raceContainer;

	public NewGameDialog(Button accept, Button cancel) {
		super();

		Label header = addChild(new Label("Configure game", new ElementId("title")));
		header.setMaxWidth(256f);

		Container form = addChild(new Container());

		form.addChild(new Label("Race count spinner"));
		raceCount = form.addChild(new Spinner<>(new SequenceModels.ListSequence<>(IntStream.range(2, 128).boxed().toList(), 8)), 1);
		raceCountRef = raceCount.getModel().createReference();

		form.addChild(new Label("Planets per race"));
		planetsPerRace = form.addChild(new Spinner<>(new SequenceModels.ListSequence<>(IntStream.range(3, 128).boxed().toList(), 10)), 1);

		raceContainer = addChild(new Container());
		raceContainer.addControl(new SimpleControl() {
			@Override
			protected void controlUpdate(float updateInterval) {
				if (raceCountRef.update()) {
					updateRaces();
				}
			}
		});

		updateRaces();

		Container actions = addChild(new Container(new SpringGridLayout(Axis.X, Axis.Y)));

		accept.addClickCommands(b -> GuiGlobals.getInstance().getPopupState().closePopup(NewGameDialog.this));
		cancel.addClickCommands(b -> GuiGlobals.getInstance().getPopupState().closePopup(NewGameDialog.this));

		actions.addChild(accept);
		actions.addChild(cancel);
	}

	private void updateRaces() {
		raceContainer.detachAllChildren();

		for (int i = 0; i < raceCountRef.get(); i++) {
			raceContainer.addChild(new Label("Race " + (i + 1)));
			Selector<Personality> selector = raceContainer.addChild(new Selector<>(VersionedList.wrap(List.of(Personality.values()))), 1);
			selector.setSelectedItem(Personality.NEUTRAL);
		}
	}

	public GameConfig config() {
		return new GameConfig(
				raceCount.getValue(),
				planetsPerRace.getValue()
		);
	}
}
