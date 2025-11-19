package galaxy.proto.widgets;

import com.jme3.scene.Node;
import com.simsilica.lemur.*;
import com.simsilica.lemur.component.SpringGridLayout;
import com.simsilica.lemur.style.ElementId;

public class Window extends Container {

	private final Container titleContainer;
	private final Container contentContainer;

	public Window(String title) {
		super("glass");

		titleContainer = super.addChild(new Container(new SpringGridLayout(Axis.Y, Axis.X, FillMode.Even, FillMode.First)));

		titleContainer.addChild(new Label(title, new ElementId("title")));
		titleContainer.addChild(new Button("x"), 1).addClickCommands(b -> GuiGlobals.getInstance().getPopupState().closePopup(Window.this));

		contentContainer = super.addChild(new Container());
	}

	@Override
	public <T extends Node> T addChild(T child, Object... constraints) {
		return contentContainer.addChild(child, constraints);
	}
}
