package galaxy.jme.generator;

import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.ValueEditors;
import com.simsilica.lemur.style.ElementId;
import com.simsilica.lemur.value.TextFieldValueEditor;
import galaxy.generator.SeedSource;
import galaxy.generator.SpiralSeedSource;

import java.util.Objects;

public class SpiralSeedSourceConfiguration implements SeedSourceConfiguration {

	private TextFieldValueEditor<Integer> armsEditor;
	private TextFieldValueEditor<Integer> seedCountEditor;
	private TextFieldValueEditor<Double> radiusEditor;
	private TextFieldValueEditor<Long> seedEditor;

	@Override
	public void initControls(Container container) {
		container.addChild(new Label("Spiral seed source configuration", new ElementId("title")));

		container.addChild(new Label("arms (races)"));
		armsEditor = new TextFieldValueEditor<>(
				o -> Objects.requireNonNull(o).toString(),
				s -> s != null ? Integer.parseInt(s) : 0
		);
		container.addChild(armsEditor.startEditing(8));

		container.addChild(new Label("seed count"));
		seedCountEditor = new TextFieldValueEditor<>(new ObjectToString(), new StringToInteger());
		container.addChild(seedCountEditor.startEditing(16384));

		container.addChild(new Label("radius (scale)"));
		radiusEditor = ValueEditors.doubleEditor("%.02f");
		radiusEditor.startEditing(256.0);
		container.addChild(radiusEditor.getEditor());

		container.addChild(new Label("seed"));
		seedEditor = new TextFieldValueEditor<>(
				o -> Objects.requireNonNull(o).toString(),
				s -> s != null ? Long.parseLong(s) : 0
		);
		seedEditor.startEditing(42L);
	}

	@Override
	public SeedSource seedSource() {
		return new SpiralSeedSource(armsEditor.getObject(), seedCountEditor.getObject(), radiusEditor.getObject(), seedEditor.getObject());
	}
}
