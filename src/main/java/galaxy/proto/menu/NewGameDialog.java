package galaxy.proto.menu;

import com.simsilica.lemur.*;
import com.simsilica.lemur.component.SpringGridLayout;
import com.simsilica.lemur.style.ElementId;
import com.simsilica.lemur.text.DocumentModelFilter;
import com.simsilica.lemur.text.TextFilters;
import com.simsilica.lemur.value.TextFieldValueEditor;

import java.util.stream.IntStream;

public class NewGameDialog extends Container {

	private final Spinner<Integer> raceCount;

	private final Spinner<Integer> planetsPerRace;

	public NewGameDialog(Button accept, Button cancel) {
		super();

		Label header = addChild(new Label("Configure game", new ElementId("header")));
		header.setMaxWidth(256f);

		Container form = addChild(new Container());

		form.addChild(new Label("Race count spinner"));
		raceCount = form.addChild(new Spinner<>(new SequenceModels.ListSequence<>(IntStream.range(2, 128).boxed().toList(), 24)), 1);

		form.addChild(new Label("Planets per race"));
//		planetsPerRace = form.addChild(new TextField("10"), 1);
		// planetsPerRace = form.addChild(new TextField(new DocumentModelFilter(TextFilters.numeric(), TextFilters.upperCaseTransform())), 1);
		planetsPerRace = form.addChild(new Spinner<>(new SequenceModels.ListSequence<>(IntStream.range(3, 128).boxed().toList(), 10)), 1);

		Container actions = addChild(new Container(new SpringGridLayout(Axis.X, Axis.Y)));

		accept.addClickCommands(b -> GuiGlobals.getInstance().getPopupState().closePopup(NewGameDialog.this));
		cancel.addClickCommands(b -> GuiGlobals.getInstance().getPopupState().closePopup(NewGameDialog.this));

		actions.addChild(accept);
		actions.addChild(cancel);
	}

	public GameConfig config() {
		return new GameConfig(
				raceCount.getValue(),
				planetsPerRace.getValue()
		);
	}
}
