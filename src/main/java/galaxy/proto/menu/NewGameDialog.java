package galaxy.proto.menu;

import com.simsilica.lemur.*;
import com.simsilica.lemur.component.SpringGridLayout;
import com.simsilica.lemur.style.ElementId;

import java.util.stream.IntStream;

public class NewGameDialog extends Container {

	private final Spinner<Integer> raceCount;
	private final TextField planetsPerRace;

	public NewGameDialog(Button accept, Button cancel) {
		super();

		Label header = addChild(new Label("Configure game", new ElementId("header")));
		header.setMaxWidth(256f);

		Container form = addChild(new Container());

		form.addChild(new Label("Race count spinner"));
		raceCount = form.addChild(new Spinner<>(new SequenceModels.ListSequence<>(IntStream.range(2, 128).boxed().toList(), 24)), 1);

		form.addChild(new Label("Planets per race"));
		planetsPerRace = form.addChild(new TextField("10"), 1);

		Container actions = addChild(new Container(new SpringGridLayout(Axis.X, Axis.Y)));

		accept.addClickCommands(b -> GuiGlobals.getInstance().getPopupState().closePopup(NewGameDialog.this));
		cancel.addClickCommands(b -> GuiGlobals.getInstance().getPopupState().closePopup(NewGameDialog.this));

		actions.addChild(accept);
		actions.addChild(cancel);

	}

	public GameConfig config() {
		return new GameConfig(
				raceCount.getValue(),
				Integer.parseInt(planetsPerRace.getText())
		);
	}
}
