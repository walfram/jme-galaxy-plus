package galaxy.proto.widgets;

import com.jme3.scene.Geometry;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.style.ElementId;
import galaxy.proto.controls.PlanetRefControl;
import galaxy.shared.FormattedDistance;

public class DistanceWidget extends Container {

	public static final String NAME = "distance-widget";

	public DistanceWidget(Geometry from, Geometry to) {
		super();

		addChild(new Label("distance", new ElementId("title")));

		Container panel = addChild(new Container());
		panel.addChild(new Label("from"));
		panel.addChild(new Label(from.getControl(PlanetRefControl.class).planet().planetRef().toString()), 1);

		panel.addChild(new Label("to"));
		panel.addChild(new Label(to.getControl(PlanetRefControl.class).planet().planetRef().toString()), 1);

		addChild(new Label(new FormattedDistance(from.getWorldTranslation(), to.getWorldTranslation()).value()));
	}
}
