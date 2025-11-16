package galaxy.generator.configuration.impl;

import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.ValueEditors;
import com.simsilica.lemur.style.ElementId;
import com.simsilica.lemur.value.TextFieldValueEditor;
import galaxy.generator.SeedSource;
import galaxy.generator.configuration.SeedSourceConfiguration;
import galaxy.generator.sources.SphericalSeedSource;
import galaxy.shared.functions.ObjectToString;
import galaxy.shared.functions.StringToInteger;
import galaxy.shared.functions.StringToLong;

public class SphericalSeedSourceConfiguration implements SeedSourceConfiguration {

	private TextFieldValueEditor<Integer> seedCountEditor;
	private TextFieldValueEditor<Double> radiusEditor;
	private TextFieldValueEditor<Long> seedEditor;

	@Override
	public void initControls(Container container) {
		container.addChild(new Label("Spherical seed source configuration", new ElementId("title")));

		container.addChild(new Label("seed count"));
		seedCountEditor = new TextFieldValueEditor<>(new ObjectToString(), new StringToInteger());
		container.addChild(seedCountEditor.startEditing(16384));

		container.addChild(new Label("radius (scale)"));
		radiusEditor = ValueEditors.doubleEditor("%.02f");
		radiusEditor.startEditing(128.0);
		container.addChild(radiusEditor.getEditor());

		container.addChild(new Label("seed"));
		seedEditor = new TextFieldValueEditor<>(new ObjectToString(), new StringToLong());
		seedEditor.startEditing(42L);
		container.addChild(seedEditor.getEditor());
	}

	@Override
	public SeedSource seedSource() {
		return new SphericalSeedSource(seedCountEditor.getObject(), radiusEditor.getObject(), seedEditor.getObject());
	}
}
