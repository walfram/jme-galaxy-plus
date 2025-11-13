package galaxy.jme.generator;

import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.ValueEditors;
import com.simsilica.lemur.style.ElementId;
import com.simsilica.lemur.value.TextFieldValueEditor;
import galaxy.generator.SeedSource;

public class SphericalSeedSourceConfiguration implements SeedSourceConfiguration {
	@Override
	public void initControls(Container container) {
		container.addChild(new Label("Spherical seed source configuration", new ElementId("title")));

		container.addChild(new Label("seed count"));
		TextFieldValueEditor<Integer> seedCountEditor = new TextFieldValueEditor<>(new ObjectToString(), new StringToInteger());
		container.addChild(seedCountEditor.startEditing(16384));

		container.addChild(new Label("radius (scale)"));
		TextFieldValueEditor<Double> radiusEditor = ValueEditors.doubleEditor("%.02f");
		radiusEditor.startEditing(256.0);
		container.addChild(radiusEditor.getEditor());

		container.addChild(new Label("seed"));
		TextFieldValueEditor<Long> seedEditor = new TextFieldValueEditor<>(new ObjectToString(), new StringToLong());
		seedEditor.startEditing(42L);
		container.addChild(seedEditor.getEditor());
	}

	@Override
	public SeedSource seedSource() {
		return null;
	}
}
